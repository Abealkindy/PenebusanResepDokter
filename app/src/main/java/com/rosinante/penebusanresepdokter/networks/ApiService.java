package com.rosinante.penebusanresepdokter.networks;

import com.rosinante.penebusanresepdokter.models.AntrianDokterModel;
import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.models.AntrianPasienModel;
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;
import com.rosinante.penebusanresepdokter.models.DetailModel;
import com.rosinante.penebusanresepdokter.models.DetailStrukModel;
import com.rosinante.penebusanresepdokter.models.DokterByIDModel;
import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.DokterModel;
import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.models.ObatModel;
import com.rosinante.penebusanresepdokter.models.PasienDetailModel;
import com.rosinante.penebusanresepdokter.models.PasienModel;
import com.rosinante.penebusanresepdokter.models.PembayaranModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.models.ResepAllModel;
import com.rosinante.penebusanresepdokter.models.ResepDokterModel;
import com.rosinante.penebusanresepdokter.models.ResepModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @FormUrlEncoded
    @POST("registerUser")
    Call<RegisterResponse> request_register(
            @Field("username") String username,
            @Field("user_password") String user_password,
            @Field("user_role") String user_role
    );

    @FormUrlEncoded
    @POST("addAntrian")
    Call<RegisterResponse> add_antrian(
            @Field("tanggal_antrian") String tanggal_antrian,
            @Field("keterangan") String keterangan,
            @Field("dokter_id") int dokter_id,
            @Field("pasien_id") int pasien_id,
            @Field("poliklinik_id") int poliklinik_id
    );

    @FormUrlEncoded
    @POST("addObat")
    Call<RegisterResponse> add_obat(
            @Field("nama_obat") String nama_obat,
            @Field("harga_obat") double harga_obat
    );

    @FormUrlEncoded
    @POST("addPoliklinik")
    Call<RegisterResponse> add_poliklinik(
            @Field("poliklinik_name") String poliklinik_name
    );

    @FormUrlEncoded
    @POST("addResep")
    Call<RegisterResponse> addResep(
            @Field("antrian_id") int antian_id,
            @Field("dokter_id") int dokter_id,
            @Field("pasien_id") int pasien_id,
            @Field("resep_date") String resep_date,
            @Field("resep_text") String resep_text
    );

    @FormUrlEncoded
    @POST("addDetail")
    Call<RegisterResponse> addDetail(
            @Field("id_obat") int id_obat,
            @Field("resep_id") int resep_id,
            @Field("harga_obat") double harga_obat,
            @Field("total_harga") double total_harga,
            @Field("dosis_obat") String dosis_obat
    );

    @FormUrlEncoded
    @POST("addPembayaran")
    Call<RegisterResponse> addPembayaran(
            @Field("pasien_id") int pasien_id,
            @Field("antrian_id") int antrian_id,
            @Field("resep_id") int resep_id,
            @Field("pembayaran_date") String pembayaran_date,
            @Field("uang_pembayaran") double uang_pembayaran,
            @Field("kembalian_pembayaran") double kembalian_pembayaran
    );

    @FormUrlEncoded
    @POST("loginUser")
    Call<LoginModel> request_login(
            @Field("username") String username,
            @Field("user_password") String user_password
    );


    @FormUrlEncoded
    @POST("deleteDokter")
    Call<RegisterResponse> delete_dokter(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("deletePasien")
    Call<RegisterResponse> delete_pasien(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("updateDokter")
    Call<RegisterResponse> request_update_dokter(
            @Field("id_user") int id_user,
            @Field("dokter_id") int dokter_id,
            @Field("username") String username,
            @Field("user_password") String user_password,
            @Field("dokter_name") String dokter_name,
            @Field("dokter_gender") String dokter_gender,
            @Field("dokter_address") String dokter_address,
            @Field("dokter_specialist") String dokter_specialist,
            @Field("dokter_email") String dokter_email,
            @Field("dokter_phone") String dokter_phone,
            @Field("dokter_tarif") String dokter_tarif,
            @Field("poliklinik_id") int poliklinik_id
    );

    @FormUrlEncoded
    @POST("updatePasien")
    Call<RegisterResponse> request_update_pasien(
            @Field("id_user") int id_user,
            @Field("pasien_id") int pasien_id,
            @Field("username") String username,
            @Field("user_password") String user_password,
            @Field("pasien_name") String pasien_name,
            @Field("pasien_gender") String pasien_gender,
            @Field("pasien_address") String pasien_address,
            @Field("pasien_age") String pasien_age,
            @Field("pasien_email") String pasien_email,
            @Field("pasien_phone") String pasien_phone
    );

    @FormUrlEncoded
    @POST("getDokterByPoliklinikId")
    Call<DokterDetailModel> getDokterByPoliklinikID(
            @Field("poliklinik_id") int poliklinik_id
    );

    @FormUrlEncoded
    @POST("getPasienById")
    Call<PasienDetailModel> getPasienByID(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("getAntrianByPasienID")
    Call<AntrianPasienModel> getAntrianByPasienID(
            @Field("pasien_id") int pasien_id
    );

    @FormUrlEncoded
    @POST("getDokterById")
    Call<DokterByIDModel> getDokterById(
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("getAntrianByDokterID")
    Call<AntrianDokterModel> getAntrianByDokterID(
            @Field("dokter_id") int dokter_id
    );

    @FormUrlEncoded
    @POST("getResepByDokterID")
    Call<ResepDokterModel> getResepByDokterID(
            @Field("dokter_id") int dokter_id
    );

    @GET("getPasienData")
    Call<PasienModel> getPasien();

    @GET("getPoliklinikData")
    Call<PoliklinikModel> getPoliklinik();

    @GET("getDokterData")
    Call<DokterModel> getDokter();

    @GET("getUserData")
    Call<LoginModel> getUser();

    @GET("getAntrian")
    Call<AntrianModel> getAntrian();

    @GET("getObatData")
    Call<ObatModel> getObat();

    @GET("getActiveResep")
    Call<ResepModel> getActiveResep();

    @GET("getResep")
    Call<ResepModel> getResep();

    @GET("getResepAll")
    Call<ResepAllModel> getResepAll();

    @GET("getDetail")
    Call<DetailModel> getDetail();

    @GET("getDetailForPharmacy")
    Call<DetailFarmasiModel> getDetailForPharmacy();

    @GET("getStruk")
    Call<DetailStrukModel> getDataStruk();

    @GET("getPembayaran")
    Call<PembayaranModel> getPembayaran();
}
