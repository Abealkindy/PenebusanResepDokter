<?php 
/**
 * 
 */

class Resep extends CI_Controller
{
	function __construct() {
		parent::__construct();
		$this->load->model('Resep_model');
		$this->load->helper('url');
	}

	public function getUserData()
	{
		$data['hasil'] = $this->Resep_model->getDataUser();
		echo json_encode($data);
	}

	public function getPembayaran()
	{
		$data['hasil'] = $this->Resep_model->getPembayaran();
		echo json_encode($data);
	}

	public function getPoliklinikData()
	{
		$dataPolis['hasil'] = $this->Resep_model->getDataPoliklinik();
		
		echo json_encode($dataPolis);
	}

	public function getResepAll()
	{
		$data['hasil'] = $this->Resep_model->getResepAll();
		echo json_encode($data);
	}

	public function getDetail()
	{
		$getData = $this->Resep_model->getDetail();

		$data['hasil'] = array();
		
		foreach ($getData as $key) {
			$coba = array(
				'detail_id' => $key->detail_id, 
				'resep_id' => $key->resep_id, 
				'total_harga' => $key->total_harga, 
				'detail_obat' => $this->Resep_model->getDetailObat($key->detail_id)
			);

			array_push($data['hasil'],$coba);

		}

		echo json_encode($data);

	}

	public function getDokterData()
	{
		$data['hasil'] = $this->Resep_model->getDataDokter();
		echo json_encode($data);
	}

	public function getAntrian()
	{
		$data['hasil'] = $this->Resep_model->getAntrian();
		echo json_encode($data);
	}

	public function getActiveResep()
	{
		$data['hasil'] = $this->Resep_model->getActiveResep();
		echo json_encode($data);
	}

	public function getResep()
	{
		$data['hasil'] = $this->Resep_model->getResep();
		echo json_encode($data);
	}

	public function getPasienData()
	{
		$data['hasil'] = $this->Resep_model->getDataPasien();
		echo json_encode($data);
	}

	public function getObatData()
	{
		$data['hasil'] = $this->Resep_model->getDataObat();
		echo json_encode($data);
	}

	public function getPasienById()
	{
		$iduser = $this->input->post('id_user');
		$getDataCheck = $this->Resep_model->getPasienCompleteCheck($iduser)->num_rows();

		if ($getDataCheck > 0) {
			$data['hasil'] = $this->Resep_model->getPasienComplete($iduser);	
			$data['response'] = true;
			echo json_encode($data);
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}

	}


	public function getDokterById()
	{
		$iduser = $this->input->post('id_user');
		$getDataCheck = $this->Resep_model->getDokterCompleteCheck($iduser)->num_rows();

		if ($getDataCheck > 0) {
			$data['hasil'] = $this->Resep_model->getDokterComplete($iduser);	
			$data['response'] = true;
			echo json_encode($data);
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}

	}

	public function getDokterByPoliklinikId()
	{
		$poliklinikID = $this->input->post('poliklinik_id');
		$data['hasil'] = $this->Resep_model->getDokterByPoliklinikID($poliklinikID);	
		$data['response'] = true;
		echo json_encode($data);
	}

	public function getAntrianByDokterID()
	{
		$dokterID = $this->input->post('dokter_id');
		$data['hasil'] = $this->Resep_model->getAntrianByDokterID($dokterID);	
		$data['response'] = true;
		echo json_encode($data);
	}

	public function loginUser()
	{
		$username = $this->input->post('username');
		$userpassword = $this->input->post('user_password');
		$where = array('username' =>$username);
		$loginCheck = $this->Resep_model->loginCheck($username, $userpassword)->num_rows();
		if ($loginCheck > 0) {
			$data['hasil'] = $this->Resep_model->loginUser($username, $userpassword);
			$data['response'] = true;
			echo json_encode($data);
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}

	}

	public function registerUser()
	{
		$username = $this->input->post('username');
		$userpassword = $this->input->post('user_password');
		$userrole = $this->input->post('user_role');
		// $userstatus = $this->input->post('user_status');

		$userValue = array(
			'username' => $username, 
			'user_password' => $userpassword, 
			'user_role' => $userrole
		);

		$registerUser = $this->Resep_model->registerUser($userValue);

		if ($registerUser === TRUE) {
			if ($userrole === "Dokter") {
				$this->Resep_model->registerDokter($username, 3);
				$data['response'] = true;
				echo json_encode($data);
			} elseif ($userrole === "Pasien") {
				$this->Resep_model->registerPasien($username);
				$data['response'] = true;
				echo json_encode($data);
			}
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}

	}

	public function addPoliklinik()
	{
		$poliklinik_name = $this->input->post('poliklinik_name');

		$addPoliklinikValue = array(
			'poliklinik_name' => $poliklinik_name
		);

		$addPoliklinik = $this->Resep_model->addPoliklinik($addPoliklinikValue);

		if ($addPoliklinik === TRUE) {
			$data['response'] = true;
			echo json_encode($data);
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}
	}

	public function addObat()
	{
		$obat_name = $this->input->post('nama_obat');	
		$obat_harga = $this->input->post('harga_obat');

		$addObatValue = array(
			'nama_obat' => $obat_name,
			'harga_obat' => $obat_harga
		);

		$addObat = $this->Resep_model->addObat($addObatValue);

		if ($addObat === TRUE) {
			$data['response'] = true;
			echo json_encode($data);
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}
	}

	public function addAntrian()
	{
		$tanggalAntrian = $this->input->post('tanggal_antrian');
		$keteranganAntrian = $this->input->post('keterangan');
		$dokterID = $this->input->post('dokter_id');
		$pasienID = $this->input->post('pasien_id');
		$poliklinikID = $this->input->post('poliklinik_id');

		$queueValue = array(
			'tanggal_antrian' => $tanggalAntrian, 
			'keterangan' => $keteranganAntrian, 
			'dokter_id' => $dokterID, 
			'pasien_id' => $pasienID, 
			'poliklinik_id' => $poliklinikID, 
			'status_antrian' => 0
		);

		$addAntrian = $this->Resep_model->addAntrian($queueValue);

		if ($addAntrian === TRUE) {
			$data['response'] = true;
			echo json_encode($data);
			$updateData = array('status_antrian' => 1);
			$this->Resep_model->updateStatusAntrianPasien($where, $updateData);

		} else {
			$data['response'] = false;
			echo json_encode($data);
		}
	}

	public function addResep()
	{
		$antrianID = $this->input->post('antrian_id');
		$dokterID = $this->input->post('dokter_id');
		$pasienID = $this->input->post('pasien_id');
		$resepDate = $this->input->post('resep_date');
		$resepText = $this->input->post('resep_text');


		$queueValue = array(
			'antrian_id' => $antrianID,
			'dokter_id' => $dokterID, 
			'pasien_id' => $pasienID, 
			'resep_date' => $resepDate, 
			'resep_text' => $resepText, 
			'resep_status' => 0
		);

		$addResep = $this->Resep_model->addResep($queueValue);

		$where = array('antrian_id' => $antrianID);
		$updateData = array('status_antrian' => 1);
		$this->Resep_model->updateStatusAntrian($where, $updateData);

		if ($addResep === TRUE) {
			$data['response'] = true;
			echo json_encode($data);
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}
	}

	public function addDetail()
	{
		$resepID = $this->input->post('resep_id');
		$IDObat = $this->input->post('id_obat');
		$hargaObat = $this->input->post('harga_obat');
		$dosisObat = $this->input->post('dosis_obat');
		$totalHarga = $this->input->post('total_harga');


		$inputDetailValue = array(
			'resep_id' => $resepID,
			'total_harga' => $totalHarga
		);

		$addDetail = $this->Resep_model->addDetail($inputDetailValue);

		if ($addDetail === TRUE) {
			$addDetailObat = $this->Resep_model->addDetailObat($IDObat, $resepID, $dosisObat, $hargaObat);
			if ($addDetailObat === TRUE) {
				$where = array('resep_id' => $resepID);
				$updateData = array('resep_status' => 1);
				$this->Resep_model->updateStatusResep($where, $updateData);
				$data['response'] = true;
				echo json_encode($data);
			} else {
				$data['response'] = false;
				echo json_encode($data);
			}
		} else {
			$data['response'] = false;
			echo json_encode($data);
		}
	}
	
	public function addPembayaran()
	{
		$pasienID = $this->input->post('pasien_id');
		$antrianID = $this->input->post('antrian_id');
		$resepID = $this->input->post('resep_id');
		$pembayaranDate = $this->input->post('pembayaran_date');
		$uangPembayaran = $this->input->post('uang_pembayaran');
		$kembalianPembayaran = $this->input->post('kembalian_pembayaran');

		$pembayaranValues = array(
			'pasien_id' => $pasienID, 
			'pembayaran_date' => $pembayaranDate, 
			'uang_pembayaran' => $uangPembayaran, 
			'kembalian_pembayaran' => $kembalianPembayaran
		);

		$addPembayaran = $this->Resep_model->addPembayaran($pembayaranValues);

		if ($addPembayaran === TRUE) {
			$data['response'] = true;
			echo json_encode($data);
			
			$whereResep = array('resep_id' => $resepID);
			$updateDataResep = array('resep_status' => 2);
			$this->Resep_model->updateStatusResep($whereResep, $updateDataResep);

			$whereAntrian = array('antrian_id' => $antrianID);			
			$updateDataAntrian = array('status_antrian' => 2);
			$this->Resep_model->updateStatusAntrian($whereAntrian, $updateDataAntrian);
			
			$wherePasien = array('pasien_id' => $pasienID);
			$updateDataPasien = array('status_antrian' => 0);
			$this->Resep_model->updateStatusAntrianPasien($wherePasien, $updateDataPasien);

		} else {
			$data['response'] = false;
			echo json_encode($data);
		}
	}

	public function updateUser()
	{
		$id_user = $this->input->post('id_user');
		$username = $this->input->post('username');
		$user_password = $this->input->post('user_password');

		$userUpdateValue = array(
			'username' => $username, 
			'user_password' => $user_password
		);

		$where = array('id_user' => $id_user);

		$this->Resep_model->updateUser($where, $userUpdateValue);

		$data['response'] = true;
		echo json_encode($data);

	}

	public function updateDokter()
	{
		$id_user = $this->input->post('id_user');
		$username = $this->input->post('username');
		$user_password = $this->input->post('user_password');

		$dokter_id = $this->input->post('dokter_id');
		$dokter_name = $this->input->post('dokter_name');
		$dokter_gender = $this->input->post('dokter_gender');
		$dokter_address = $this->input->post('dokter_address');
		$dokter_specialist = $this->input->post('dokter_specialist');
		$dokter_email = $this->input->post('dokter_email');
		$dokter_phone = $this->input->post('dokter_phone');
		$dokter_tarif = $this->input->post('dokter_tarif');
		$poliklinik_id = $this->input->post('poliklinik_id');

		$userUpdateValue = array(
			'username' => $username, 
			'user_password' => $user_password
		);

		$dokterUpdateValue = array(
			'dokter_name' => $dokter_name, 
			'dokter_gender' => $dokter_gender,
			'dokter_address' => $dokter_address,
			'dokter_specialist' => $dokter_specialist,
			'dokter_email' => $dokter_email,
			'dokter_phone' => $dokter_phone,
			'dokter_tarif' => $dokter_tarif,
			'poliklinik_id' => $poliklinik_id
		);

		$wheredokter = array('dokter_id' => $dokter_id);
		$where = array('id_user' => $id_user);

		$this->Resep_model->updateUser($where, $userUpdateValue);
		$this->Resep_model->updateDokter($wheredokter, $dokterUpdateValue);

		$data['response'] = true;
		echo json_encode($data);

	}

	public function updatePasien()
	{
		$id_user = $this->input->post('id_user');
		$username = $this->input->post('username');
		$user_password = $this->input->post('user_password');

		$pasien_id = $this->input->post('pasien_id');
		$pasien_name = $this->input->post('pasien_name');
		$pasien_gender = $this->input->post('pasien_gender');
		$pasien_address = $this->input->post('pasien_address');
		$pasien_age = $this->input->post('pasien_age');
		$pasien_email = $this->input->post('pasien_email');
		$pasien_phone = $this->input->post('pasien_phone');

		$userUpdateValue = array(
			'username' => $username, 
			'user_password' => $user_password
		);

		$pasienUpdateValue = array(
			'pasien_name' => $pasien_name, 
			'pasien_gender' => $pasien_gender,
			'pasien_address' => $pasien_address,
			'pasien_age' => $pasien_age,
			'pasien_email' => $pasien_email,
			'pasien_phone' => $pasien_phone
		);

		$wherepasien = array('pasien_id' => $pasien_id);
		$where = array('id_user' => $id_user);

		$this->Resep_model->updateUser($where, $userUpdateValue);
		$this->Resep_model->updatePasien($wherepasien, $pasienUpdateValue);

		$data['response'] = true;
		echo json_encode($data);

	}

	public function deleteUser()
	{
		$iduser = $this->input->post('id_user');
		$userrole = $this->input->post('user_role');
		$this->Resep_model->deleteUser($iduser);
		if ($userrole === "Dokter") {
			$this->Resep_model->deleteDokter($iduser);
			$data['response'] = true;
			echo json_encode($data);
		} elseif ($userrole === "Pasien") {
			$this->Resep_model->deletePasien($iduser);
			$data['response'] = true;
			echo json_encode($data);
		}
	}

	public function deleteDokter()
	{
		$iduser = $this->input->post('id_user');
		$this->Resep_model->deleteUser($iduser);
		$this->Resep_model->deleteDokter($iduser);
		$data['response'] = true;
		echo json_encode($data);
	}

	public function deletePasien()
	{
		$iduser = $this->input->post('id_user');
		$this->Resep_model->deleteUser($iduser);
		$this->Resep_model->deletePasien($iduser);
		$data['response'] = true;
		echo json_encode($data);
	}


}

?>