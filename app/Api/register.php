<?php
 
   if($_SERVER['REQUEST_METHOD']=='POST'){
  
       include_once("config.php");
       
        $name     = $_POST['name'];
 	$username = $_POST['username'];
 	$password = $_POST['password'];
 	$hobby    = $_POST['hobby'];
  
	 if($name == '' || $username == '' || $password == '' || $hobby == ''){
	        echo json_encode(array( "status" => "false","message" => "Parameter Tidak Ditemukan!") );
	 }else{
			 
	        $query= "SELECT * FROM tb_aktivasi WHERE nik='$username'";
	        $result= mysqli_query($con, $query);
		 
	        if(mysqli_num_rows($result) > 0){  
	           echo json_encode(array( "status" => "false","message" => "NIK Ditemukan.. Selamat akun anda telah aktif!") );
	        }else{ 
		 	 $query = "INSERT INTO registerDemo (name,hobby,username,password) VALUES ('$name','$hobby','$username','$password')";
			 if(mysqli_query($con,$query)){
			    
			     $query= "SELECT * FROM registerDemo WHERE username='$username'";
	                     $result= mysqli_query($con, $query);
		             $emparray = array();
	                     if(mysqli_num_rows($result) > 0){  
	                     while ($row = mysqli_fetch_assoc($result)) {
                                     $emparray[] = $row;
                                   }
	                     }
			       echo json_encode(array( "status" => "true","message" => "Successfully registered!" , "data" => $emparray) );
		 	 }else{
		 		 echo json_encode(array( "status" => "false","message" => "Error occured, please try again!") );
		 	}
	    }
	            mysqli_close($con);
	 }
     } else{
			echo json_encode(array( "status" => "false","message" => "Error occured, please try again!") );
	}
 
 ?>