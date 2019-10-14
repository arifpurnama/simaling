<?php
require_once("koneksi.php");
	class LoginUser {
		
		private $db;
		private $conexion;
		
		function __construct() {
			$this -> db = new Koneksi();
			$this -> conexion = $this->db->conexion();
		}
		
		public function does_user_exist($nik,$password)
		{
			$query = "SELECT * FROM data_akun WHERE nik='$nik' AND pass= '$password' AND aktif='Y'";
			$result = $this -> conexion->prepare($query);
			$result->execute();
			
			if($result->rowCount() == 1){
				$json['success'] = ' Berhasil Login '.$nik;
				
				$query = "SELECT * FROM data_akun WHERE nik = ?";
				
				try {
					
					$comando = $this->conexion->prepare($query);
					
					$comando->execute(array($nik));
					
					$row = $comando->fetch(PDO::FETCH_ASSOC);
					$json['pengguna'][]=$row;
									
				} catch (PDOException $e) {
					$json['error'] = 'exception';
				
					return -1;
				}

				echo json_encode($json);
			}else{
				$json['error'] = 'Nik dan password salah / Akun anda belum aktif';
				echo json_encode($json);
			}
			
		}
		
	}
	
	$loginUser = new LoginUser();
	if(isset($_POST['nik'],$_POST['password'])) {
		$nik = $_POST['nik'];
		$password = $_POST['password'];
		
		if(!empty($nik) && !empty($password)){
			
			$encrypted_password = md5($password);
			$loginUser-> does_user_exist($nik,$password);
			
		}else{
			echo json_encode("Galat database");
		}
		
	}
?>
