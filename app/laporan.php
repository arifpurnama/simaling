<?php
require_once("koneksi.php");

	class daftar {
		
		private $db;
        private $conexion;
		
		function __construct() {
			$this -> db = new Koneksi();
			$this -> conexion = $this->db->conexion();
        }
        
		public function does_user_exist($nik,$tgl,$jenis,$pesan)
		{
            
            
			
                $query = "INSERT tb_laporan VALUES(?,?,?,?,?,?,?)"; 
	
				$inserted = $this->conexion->prepare($query);
				$inserted->bindParam(1, $nik, PDO::PARAM_STR);
				$inserted->bindParam(2, $tgl, PDO::PARAM_STR);
                $inserted->bindParam(3, $jenis, PDO::PARAM_STR);
                $inserted->bindParam(3, $pesan, PDO::PARAM_STR);
				if($inserted->execute()){
					$json['success'] = 'Data tersimpan';

				}else{
					$json['error'] = 'Gagal Simpan';
				}
				echo json_encode($json);
			   
			
		}
		
	}
	
	$signupUser = new daftar();
	if(isset($_POST['nik'],$_POST['tgl'],$_POST['jenis'],$_POST['pesan'])) {
		$nik          = $_POST['nik'];
		$tgl         = $_POST['tgl'];
        $jenis       = $_POST['jenis'];
        $pesan       = $_POST['pesan'];
		        
		//if(!empty($nik) && !empty($password) && !empty($notelp)){
			//$encrypted_password = md5($password);
			$signupUser-> does_user_exist($nik,$tgl,$jenis,$pesan);
		//}else{
		//	echo json_encode("Galat database");
		//}
		
	}
?>
