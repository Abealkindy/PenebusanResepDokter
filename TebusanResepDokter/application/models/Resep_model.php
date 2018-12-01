<?php
/**
 * 
 */
class Resep_model extends CI_Model {
	
	function getDataUser() {
		return $this->db->get('tb_user')->result();
	}
	
	function getDataPoliklinik() {
		return $this->db->get('tb_poliklinik')->result();
	}
	
	function getDetail() {
		return $this->db->get('tb_detail')->result();
	}
	
	function getPembayaran() {
		return $this->db->select('
			tb_pembayaran.pembayaran_id,
			tb_pasien.pasien_name,
			tb_pembayaran.pembayaran_date, 
			tb_pembayaran.uang_pembayaran,
			tb_pembayaran.kembalian_pembayaran
			')
		->from('tb_pembayaran')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_pembayaran.pasien_id')
		->get()
		->result();
	}
	
	function getDetailObat($detail_id) {
		$where = array('tb_detail_obat.detail_id' => $detail_id);
		return $this->db->select('*')
		->from('tb_detail_obat')
		->join('tb_detail', 'tb_detail_obat.detail_id = tb_detail.detail_id')
		->where($where)
		->get()
		->result();
	}

	public function getResepAll()
	{
		$where = array(
			'resep_status' => 1
		);
		return $this->db->select('
			tb_resep.resep_id,
			tb_resep.antrian_id,
			tb_resep.pasien_id,
			tb_resep.resep_date,
			tb_dokter.dokter_name,
			tb_pasien.pasien_name,
			tb_obat.nama_obat,
			tb_detail_obat.harga_obat,
			tb_detail_obat.dosis_obat,
			tb_detail.total_harga
			')
		->from('tb_resep')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_resep.pasien_id')
		->join('tb_dokter', 'tb_dokter.dokter_id = tb_resep.dokter_id')
		->join('tb_detail_obat', 'tb_resep.resep_id = tb_detail_obat.resep_id')
		->join('tb_obat', 'tb_obat.id_obat = tb_detail_obat.id_obat')
		->join('tb_detail', 'tb_detail.resep_id = tb_resep.resep_id')
		->where($where)
		->get()
		->result();
	}

	function getDataObat() {
		return $this->db->get('tb_obat')->result();
	}

	function getDataDokter() {
		return $this->db->select('
			tb_user.id_user,
			tb_user.username,
			tb_user.user_password,
			tb_dokter.dokter_name,
			tb_dokter.dokter_gender,
			tb_dokter.dokter_address, 
			tb_dokter.dokter_specialist, 
			tb_dokter.dokter_email, 
			tb_dokter.dokter_phone, 
			tb_dokter.dokter_tarif, 
			tb_dokter.poliklinik_id, 
			tb_poliklinik.poliklinik_name
			')
		->from('tb_user')
		->join('tb_dokter', 'tb_dokter.dokter_id = tb_user.id_user')
		->join('tb_poliklinik', 'tb_poliklinik.poliklinik_id = tb_dokter.poliklinik_id')
		->get()
		->result();
	}
	
	function getDokterComplete($dokter_id) {
		$where = array('id_user' => $dokter_id);
		return $this->db->select('
			tb_user.id_user,
			tb_user.username,
			tb_user.user_password,
			tb_dokter.dokter_name,
			tb_dokter.dokter_gender,
			tb_dokter.dokter_address,
			tb_dokter.dokter_specialist,
			tb_dokter.dokter_email,
			tb_dokter.dokter_phone,
			tb_dokter.dokter_tarif,
			tb_poliklinik.poliklinik_name
			')
		->from('tb_user')
		->join('tb_dokter', 'tb_dokter.dokter_id = tb_user.id_user')
		->join('tb_poliklinik', 'tb_poliklinik.poliklinik_id = tb_dokter.poliklinik_id')
		->where($where)
		->get()
		->result();
	}
	
	function getAntrian()
	{
		$where = array('tb_antrian.status_antrian' => 0);
		return $this->db->select('
			tb_antrian.antrian_id, 
			tb_antrian.tanggal_antrian,
			tb_dokter.dokter_name,
			tb_pasien.pasien_name,
			tb_poliklinik.poliklinik_name,
			tb_antrian.keterangan,
			tb_antrian.status_antrian
			')
		->from('tb_antrian')
		->join('tb_dokter', 'tb_dokter.dokter_id = tb_antrian.dokter_id')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_antrian.pasien_id')
		->join('tb_poliklinik', 'tb_poliklinik.poliklinik_id = tb_antrian.poliklinik_id')
		->where($where)
		->order_by('tb_antrian.antrian_id', 'ASC')
		->get()
		->result();
	}

	function getActiveResep()
	{
		$where = array('tb_resep.resep_status' => 0);
		return $this->db->select('
			tb_resep.resep_id, 
			tb_dokter.dokter_name,
			tb_pasien.pasien_name,
			tb_resep.resep_text,
			tb_resep.resep_date,
			tb_resep.resep_status
			')
		->from('tb_resep')
		->join('tb_dokter', 'tb_dokter.dokter_id = tb_resep.dokter_id')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_resep.pasien_id')
		->where($where)
		->get()
		->result();
	}

	function getResep()
	{
		return $this->db->select('
			tb_resep.resep_id, 
			tb_dokter.dokter_name,
			tb_pasien.pasien_name,
			tb_resep.resep_text,
			tb_resep.resep_date,
			tb_resep.resep_status
			')
		->from('tb_resep')
		->join('tb_dokter', 'tb_dokter.dokter_id = tb_resep.dokter_id')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_resep.pasien_id')
		->get()
		->result();
	}

	function getDataPasien() {
		return $this->db->select('
			tb_user.id_user, 
			tb_user.username, 
			tb_user.user_password, 
			tb_pasien.pasien_name, 
			tb_pasien.pasien_gender,
			tb_pasien.pasien_address, 
			tb_pasien.pasien_age, 
			tb_pasien.pasien_email, 
			tb_pasien.pasien_phone,
			tb_pasien.status_antrian 
			')
		->from('tb_user')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_user.id_user')
		->get()
		->result();
	}

	function getPasienComplete($pasien_id) {
		$where = array('id_user' => $pasien_id);
		return $this->db->select('
			tb_user.id_user, 
			tb_user.username, 
			tb_user.user_password, 
			tb_pasien.pasien_name, 
			tb_pasien.pasien_gender,
			tb_pasien.pasien_address, 
			tb_pasien.pasien_age, 
			tb_pasien.pasien_email, 
			tb_pasien.pasien_phone,
			tb_pasien.status_antrian
			')
		->from('tb_user')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_user.id_user')
		->where($where)
		->get()
		->result();
	}
	
	function getPasienCompleteCheck($pasien_id) {
		$where = array('id_user' => $pasien_id);
		return $this->db->select('
			tb_user.id_user, 
			tb_user.username, 
			tb_user.user_password, 
			tb_pasien.pasien_name, 
			tb_pasien.pasien_gender,
			tb_pasien.pasien_address, 
			tb_pasien.pasien_age, 
			tb_pasien.pasien_email, 
			tb_pasien.pasien_phone,
			tb_pasien.status_antrian
			')
		->from('tb_user')
		->join('tb_pasien', 'tb_pasien.pasien_id = tb_user.id_user')
		->where($where)
		->get();
	}
	
	function getDokterByPoliklinikID($poliklinik_id) {
		$where = array('tb_dokter.poliklinik_id' => $poliklinik_id);
		return $this->db->select('
			tb_dokter.dokter_id, 
			tb_dokter.dokter_name 
			')
		->from('tb_dokter')
		->join('tb_poliklinik','tb_poliklinik.poliklinik_id = tb_dokter.poliklinik_id')
		->where($where)
		->get()
		->result();
	}
	
	function getDokterCompleteCheck($dokter_id) {
		return $this->db->query("SELECT tb_user.id_user, 
			tb_user.username, 
			tb_user.user_password, 
			tb_dokter.dokter_name, 
			tb_dokter.dokter_gender,
			tb_dokter.dokter_address, 
			tb_dokter.dokter_specialist, 
			tb_dokter.dokter_email, 
			tb_dokter.dokter_phone, 
			tb_dokter.dokter_tarif, 
			tb_poliklinik.poliklinik_name FROM tb_user 
			JOIN tb_dokter ON tb_dokter.dokter_id = tb_user.id_user 
			JOIN tb_poliklinik ON tb_poliklinik.poliklinik_id = tb_dokter.poliklinik_id 
			WHERE id_user = '$dokter_id'");
	}
	
	
	function getAntrianByDokterID($dokter_id) {
		return $this->db->query(
			"SELECT tb_antrian.antrian_id,
			tb_antrian.tanggal_antrian,
			tb_antrian.dokter_id,
			tb_dokter.dokter_name,
			tb_antrian.pasien_id,
			tb_pasien.pasien_name,
			tb_poliklinik.poliklinik_name,
			tb_antrian.keterangan,
			tb_antrian.status_antrian
			FROM tb_antrian
			JOIN tb_dokter ON tb_dokter.dokter_id = tb_antrian.dokter_id
			JOIN tb_pasien ON tb_pasien.pasien_id = tb_antrian.pasien_id
			JOIN tb_poliklinik ON tb_poliklinik.poliklinik_id = tb_antrian.poliklinik_id
			WHERE tb_antrian.status_antrian = '0' 
			AND tb_antrian.dokter_id = '$dokter_id'
			"
		)->result();
	}
	
	function loginUser($username, $userpassword) {
		$where = array(
			'username' => $username,
			'user_password' => $userpassword
		);
		return $this->db->select('*')
		->from('tb_user')
		->where($where)
		->get()
		->result();
	}
	
	function loginCheck($username, $userpassword) {
		$where = array(
			'username' => $username,
			'user_password' => $userpassword
		);
		return $this->db->select('*')
		->from('tb_user')
		->where($where)
		->get();
	}

	function registerUser($userValue) {
		return $this->db->insert('tb_user', $userValue);
	}

	function registerDokter($username, $poliklinik_id) {
		return $this->db->query("INSERT INTO tb_dokter(dokter_id, dokter_name, poliklinik_id)  
			SELECT id_user, username, 3 FROM tb_user WHERE username = '$username' ");
	}

	function registerPasien($username) {
		return $this->db->query("INSERT INTO tb_pasien(pasien_id, pasien_name) SELECT id_user, username FROM tb_user WHERE username = '$username' ");
	}

	function addPoliklinik($poliklinikValue)
	{
		return $this->db->insert('tb_poliklinik', $poliklinikValue);
	}

	function addObat($obatValue)
	{
		return $this->db->insert('tb_obat', $obatValue);
	}

	function addDetail($detailValue)
	{
		return $this->db->insert('tb_detail', $detailValue);
	}

	function addPembayaran($pembayaranValue)
	{
		return $this->db->insert('tb_pembayaran', $pembayaranValue);
	}

	function addDetailObat($id_obat, $resep_id, $dosisObat, $hargaObat)
	{
		return $this->db->query("INSERT INTO tb_detail_obat(id_obat, detail_id, resep_id, dosis_obat, harga_obat)  
			SELECT $id_obat, tb_detail.detail_id, $resep_id, '$dosisObat', $hargaObat FROM tb_detail WHERE tb_detail.resep_id = $resep_id");
	}

	function addResep($resepValue)
	{
		return $this->db->insert('tb_resep', $resepValue);
	}

	function updateUser($where, $updateUserValue)
	{
		$this->db->where($where);
		$this->db->update('tb_user', $updateUserValue);
	}

	function updateDokter($where, $updateDokterValue)
	{
		$this->db->where($where);
		$this->db->update('tb_dokter', $updateDokterValue);
	}

	function updatePasien($where, $updatePasienValue)
	{
		$this->db->where($where);
		$this->db->update('tb_pasien', $updatePasienValue);
	}

	function updateStatusAntrianPasien($where,$data) {
		$this->db->where($where);
		$this->db->update('tb_pasien',$data);
	}

	function updateStatusAntrian($where,$data) {
		$this->db->where($where);
		$this->db->update('tb_antrian',$data);
	}
	
	function updateStatusResep($where,$data) {
		$this->db->where($where);
		$this->db->update('tb_resep',$data);
	}

	function deleteUser($id_user) {
		$this->db->where('id_user', $id_user);
		$this->db->delete('tb_user');
	}

	function deleteDokter($id_dokter) {
		$this->db->where('dokter_id', $id_dokter);
		$this->db->delete('tb_dokter');
	}
	
	function deletePasien($id_pasien) {
		$this->db->where('pasien_id', $id_pasien);
		$this->db->delete('tb_pasien');
	}

	function addAntrian($antrianValue) {
		return $this->db->insert('tb_antrian', $antrianValue);
	}

	function edit_data($where,$table) {		
		return $this->db->get_where($table,$where);
	}


}
?>