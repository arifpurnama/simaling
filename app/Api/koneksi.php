<?php

class Koneksi{
	public static function conexion(){
		
		try{
			$conexion = new PDO('mysql:host=localhost; dbname=db_lingkungan','root','');
			$conexion -> setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$conexion -> exec("SET CHARACTER SET UTF8");
			
	}catch(Exception $e){
		
		die("Error " . $e->getMessage());
		echo "Error " . $e->getLine();
	}
	
	return $conexion;
	}
}
?>
