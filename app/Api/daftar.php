<?php
require_once("koneksi.php");

	class daftar {
		
		private $db;
        private $conexion;
		
		function __construct() {
			$this -> db = new Koneksi();
			$this -> conexion = $this->db->conexion();
        }
        
		public function does_user_exist($nik,$password,$notelp)
		{
            $query = "SELECT * FROM tb_aktivasi WHERE nik='$nik' AND aktif='Y'";
            $result = $this -> conexion->prepare($query);
            $result->execute();
            
			if($result->rowCount() == 1){
				$json['error'] = 'Akun Anda Telah Aktif '.$nik;
				echo json_encode($json);
			}else{
                $query = "UPDATE tb_aktivasi SET passsword='$password', 
				         notlp='$notelp', aktif='Y' WHERE nik='$nik'";
				$inserted = $this->conexion->prepare($query);
				//$inserted->bindParam(1, $nik, PDO::PARAM_STR);
				//$inserted->bindParam(2, $password, PDO::PARAM_STR);
				//$inserted->bindParam(3, $notelp, PDO::PARAM_STR);
				if($inserted->execute()){
					$json['success'] = 'Akun anda berhasil aktif';
					$query = "SELECT * FROM data_akun WHERE nik = ?";
					try {
						$comando = $this->conexion->prepare($query);
						$comando->execute(array($nik));
						$row = $comando->fetch(PDO::FETCH_ASSOC);
						$json['pengguna'][]=$row;
					
					} catch (PDOException $e) {
						
						return -1;
					}

				}else{
					$json['error'] = 'Gagal Aktivasi akun';
				}
				echo json_encode($json);
			}    
			
		}
		
	}
	
	$signupUser = new daftar();
	if(isset($_POST['nik'],$_POST['password'],$_POST['notlp'])) {
		$nik          = $_POST['nik'];
		$password     = $_POST['password'];
		$notelp       = $_POST['notlp'];
		        
		//if(!empty($nik) && !empty($password) && !empty($notelp)){
			//$encrypted_password = md5($password);
			$signupUser-> does_user_exist($nik,$password,$notelp);
		//}else{
		//	echo json_encode("Galat database");
		//}
		
	}
?>
