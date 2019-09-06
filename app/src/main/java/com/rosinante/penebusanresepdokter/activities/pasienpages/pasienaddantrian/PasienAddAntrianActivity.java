package com.rosinante.penebusanresepdokter.activities.pasienpages.pasienaddantrian;

import android.content.Intent;
import android.os.Bundle;
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
import com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien.MainPasienActivity;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerDokterAdapter;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerPoliklinikAdapter;
import com.rosinante.penebusanresepdokter.fragments.DatePickerFragment;
import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasienAddAntrianActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener, PasienAddAntrianInterface {

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
    private PasienAddAntrianPresenter pasienAddAntrianPresenter = new PasienAddAntrianPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_add_antrian);
        ButterKnife.bind(this);
        pasienAddAntrianPresenter.getPoliklinikData();
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
                    pasienAddAntrianPresenter.insertAntrianData(tanggalPendaftaran, dokterID, pasienID, poliklinikID, keteranganAntrian);
                }
                break;
        }
    }

    @Override
    public void onGetPoliklinikDataSuccess(PoliklinikModel poliklinikModel) {
        spinnerPoliklinikPasien.setAdapter(new SpinnerPoliklinikAdapter(PasienAddAntrianActivity.this, android.R.layout.simple_spinner_item, poliklinikModel.getHasil()));
        spinnerPoliklinikPasien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPoliklinikPasien.setVisibility(View.GONE);
                textSpinnerPoliklinikPasien.setVisibility(View.VISIBLE);
                poliklinikID = poliklinikModel.getHasil().get(position).getPoliklinik_id();
                pasienAddAntrianPresenter.getDokterDataByPoliklinikID(poliklinikID);
                textSpinnerPoliklinikPasien.setText(String.valueOf(poliklinikModel.getHasil().get(position).getPoliklinik_name()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSpinnerPoliklinikPasien.setText("");
            }
        });
    }

    @Override
    public void onGetPoliklinikDataFailed(String errorMessage) {
        Toast.makeText(PasienAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDokterDataByPoliklinikIDSuccess(DokterDetailModel dokterDetailModel) {
        spinnerDokterPasien.setAdapter(new SpinnerDokterAdapter(PasienAddAntrianActivity.this, android.R.layout.simple_spinner_item, dokterDetailModel.getHasil()));
        spinnerDokterPasien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerDokterPasien.setVisibility(View.GONE);
                textSpinnerDokterPasien.setVisibility(View.VISIBLE);
                dokterID = dokterDetailModel.getHasil().get(position).getDokter_id();
                textSpinnerDokterPasien.setText(String.valueOf(dokterDetailModel.getHasil().get(position).getDokter_name()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSpinnerDokterPasien.setText("");
            }
        });
    }

    @Override
    public void onGetDokterDataByPoliklinikIDFailed(String errorMessage) {
        Toast.makeText(PasienAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertPasienAntrianDataSuccess(RegisterResponse registerResponse) {
        if (!registerResponse.isResponse()) {
            Toast.makeText(PasienAddAntrianActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PasienAddAntrianActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PasienAddAntrianActivity.this, MainPasienActivity.class));
            finish();
        }
    }

    @Override
    public void onInsertPasienAntrianDataFailed(String errorMessage) {
        Toast.makeText(PasienAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
