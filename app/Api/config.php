<?php
$host="localhost";
$user="root";
$password="";
$db = "db_lingkungan";
 
$con = mysqli_connect($host,$user,$password,$db);
 
// Check connection
if (mysqli_connect_errno())
  {
  echo "Gagal Koneksi: " . mysqli_connect_error();
  }else{  
    echo "Connect"; 
  
   
   }
 
?>