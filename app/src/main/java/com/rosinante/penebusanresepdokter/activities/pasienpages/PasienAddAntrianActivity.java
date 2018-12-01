package com.rosinante.penebusanresepdokter.activities.pasienpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerDokterAdapter;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerPoliklinikAdapter;
import com.rosinante.penebusanresepdokter.fragments.DatePickerFragment;
import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasienAddAntrianActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener {

    @BindView(R.id.edit_text_tanggal_pendaftaran_pasien)
    EditText editTextTanggalPendaftaranPasien;
    @BindView(R.id.edit_text_keterangan_pasien)
    EditText editTextKeteranganPasien;
    @BindView(R.id.text_spinner_poliklinik_pasien)
    TextView textSpinnerPoliklinikPasien;
    @BindView(R.id.spinner_poliklinik_pasien)
    Spinner spinnerPoliklinikPasien;
    @BindView(R.id.text_spinner_dokter_pasien)
    TextView textSpinnerDokterPasien;
    @BindView(R.id.spinner_dokter_pasien)
    Spinner spinnerDokterPasien;
    @BindView(R.id.button_daftar_antrian)
    Button buttonDaftarAntrian;
    @BindView(R.id.cardregister)
    CardView cardregister;


    String tanggalPendaftaran, keteranganAntrian;
    int poliklinikID, dokterID, pasienID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_add_antrian);
        ButterKnife.bind(this);
        getPoliklinikData();
    }

    void getPoliklinikData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<PoliklinikModel> poliklinikModelCall = apiService.getPoliklinik();
        poliklinikModelCall.enqueue(new Callback<PoliklinikModel>() {
            @Override
            public void onResponse(@NonNull Call<PoliklinikModel> call, @NonNull Response<PoliklinikModel> response) {
                spinnerPoliklinikPasien.setAdapter(new SpinnerPoliklinikAdapter(PasienAddAntrianActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(response.body()).getHasil()));
                spinnerPoliklinikPasien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinnerPoliklinikPasien.setVisibility(View.GONE);
                        textSpinnerPoliklinikPasien.setVisibility(View.VISIBLE);
                        poliklinikID = response.body().getHasil().get(position).getPoliklinik_id();
                        getDokterDataByPoliklinikID(poliklinikID);
                        textSpinnerPoliklinikPasien.setText(String.valueOf(response.body().getHasil().get(position).getPoliklinik_name()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        textSpinnerPoliklinikPasien.setText("");
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<PoliklinikModel> call, @NonNull Throwable t) {
                Toast.makeText(PasienAddAntrianActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDokterDataByPoliklinikID(int poliklinikID) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<DokterDetailModel> dokterDetailModelCall = apiService.getDokterByPoliklinikID(poliklinikID);
        dokterDetailModelCall.enqueue(new Callback<DokterDetailModel>() {
            @Override
            public void onResponse(@NonNull Call<DokterDetailModel> call, @NonNull Response<DokterDetailModel> response) {
                spinnerDokterPasien.setAdapter(new SpinnerDokterAdapter(PasienAddAntrianActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(response.body()).getHasil()));
                spinnerDokterPasien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinnerDokterPasien.setVisibility(View.GONE);
                        textSpinnerDokterPasien.setVisibility(View.VISIBLE);
                        dokterID = response.body().getHasil().get(position).getDokter_id();
                        textSpinnerDokterPasien.setText(String.valueOf(response.body().getHasil().get(position).getDokter_name()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        textSpinnerDokterPasien.setText("");
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<DokterDetailModel> call, @NonNull Throwable t) {
                Toast.makeText(PasienAddAntrianActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertAntrianData(String tanggalPendaftaran, int dokterID, int pasienID, int poliklinikID, String keteranganAntrian) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> antrianCall = apiService.add_antrian(
                tanggalPendaftaran,
                keteranganAntrian,
                dokterID,
                pasienID,
                poliklinikID
        );
        antrianCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(PasienAddAntrianActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PasienAddAntrianActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PasienAddAntrianActivity.this, MainPasienActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(PasienAddAntrianActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PasienAddAntrianActivity.this, MainPasienActivity.class));
        finish();
    }

    @Override
    public void onFinishDialog(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
        String getDate = simpleDateFormat.format(date);
        editTextTanggalPendaftaranPasien.setText(getDate);
    }

    @OnClick({R.id.edit_text_tanggal_pendaftaran_pasien, R.id.text_spinner_poliklinik_pasien, R.id.text_spinner_dokter_pasien, R.id.button_daftar_antrian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_text_tanggal_pendaftaran_pasien:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "PasienAddAntrianActivity.DateDialog");
                break;
            case R.id.text_spinner_poliklinik_pasien:
                textSpinnerPoliklinikPasien.setVisibility(View.GONE);
                spinnerPoliklinikPasien.setVisibility(View.VISIBLE);
                break;
            case R.id.text_spinner_dokter_pasien:
                textSpinnerDokterPasien.setVisibility(View.GONE);
                spinnerDokterPasien.setVisibility(View.VISIBLE);
                break;
            case R.id.button_daftar_antrian:
                keteranganAntrian = editTextKeteranganPasien.getText().toString();
                tanggalPendaftaran = editTextTanggalPendaftaranPasien.getText().toString();
                UserSession userSession = new UserSession(PasienAddAntrianActivity.this);
                pasienID = Integer.parseInt(userSession.getIdUser());
                if (keteranganAntrian.isEmpty()) {
                    editTextKeteranganPasien.setError("Isi keterangan terlebih dahulu!");
                    editTextKeteranganPasien.requestFocus();
                } else if (tanggalPendaftaran.isEmpty()) {
                    editTextTanggalPendaftaranPasien.setError("Pilih umur terlebih dahulu!");
                } else {
                    insertAntrianData(tanggalPendaftaran, dokterID, pasienID, poliklinikID, keteranganAntrian);
                }
                break;
        }
    }
}
