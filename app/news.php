<?php
	
	require_once("config.php");
	sleep(2);
	$offset = isset($_GET['offset']) && $_GET['offset'] != '' ? $_GET['offset'] : 0;
	$all = mysqli_query($con, "SELECT * FROM tb_berita ORDER BY id_berita DESC");
	$count_all = mysqli_num_rows($all);
	$query = mysqli_query($con, "SELECT * FROM tb_berita ORDER BY id_berita DESC LIMIT $offset,10");
	$count = mysqli_num_rows($query);
	$json_kosong = 0;
	if($count<10){
		if($count==0){
			$json_kosong = 1;
		}else{
			$query = mysqli_query($con, "SELECT * FROM tb_berita ORDER BY id_berita DESC LIMIT $offset,$count");
			$count = mysqli_num_rows($query);
			if(empty($count)){
				$query = mysqli_query($con, "SELECT * FROM tb_berita ORDER BY id_berita DESC LIMIT 0,10");
				$num = 0;
			}else{
				$num = $offset;
			}
		}
	} else{
		$num = $offset;
	}
	$json = '[';
	while ($row = mysqli_fetch_array($query)){
		$num++;
		$char ='"';
		$tgl	= date("d M Y", strtotime($row['tanggal']));
		$string = substr(strip_tags($row['isi_berita']), 0, 200);
		$json .= '{
			"no": '.$num.',
			"id": "'.str_replace($char,'`',strip_tags($row['id_berita'])).'", 
			"judul": "'.str_replace($char,'`',strip_tags($row['judul_berita'])).'",
			"tgl": "'.str_replace($char,'`',strip_tags($tgl)).'", 
			"isi": "'.str_replace($char,'`', $string." ...").'",
			"gambar": "'.str_replace($char,'`',strip_tags($row['gambar_berita'])).'"},';
	}
	$json = substr($json,0,strlen($json)-1);
	if($json_kosong==1){
		$json = '[{ "no": "", "id": "", "judul": "", "tgl": "", "isi": "", "gambar": ""}]';
	}else{
		$json .= ']';
	}
	echo $json;
	mysqli_close($con);

?>