<?php 
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//Mendapatkan Nilai Dari Variable 
		$id = $_POST['id'];
		$nama_depan = $_POST['nama_depan'];
		$nama_belakang = $_POST['nama_belakang'];
		$alamat = $_POST['alamat'];
        $no_rekening = $_POST['no_rekening'];
		$status = $_POST['status'];
		$cif = $_POST['cif'];
		
		//import file koneksi database 
		require_once('koneksi.php');
		
		//Membuat SQL Query
		$sql = "UPDATE tb_account SET nama_depan = '$nama_depan', nama_belakang = '$nama_belakang', alamat = '$alamat', no_rekening = '$no_rekening', status = '$status', cif = '$cif' WHERE id = $id;";
		
		//Meng-update Database 
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Account';
		}else{
			echo 'Gagal Update Data Account';
		}
		
		mysqli_close($con);
	}
?>