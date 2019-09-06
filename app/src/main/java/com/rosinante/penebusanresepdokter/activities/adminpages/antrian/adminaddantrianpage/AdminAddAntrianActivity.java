package com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminaddantrianpage;

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
import com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminantrianlistpage.AdminAntrianListActivity;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerDokterAdapter;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerPasienAdapter;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerPoliklinikAdapter;
import com.rosinante.penebusanresepdokter.fragments.DatePickerFragment;
import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PasienModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminAddAntrianActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener, AdminAddAntrianInterface {

    @BindView(R.id.edit_text_tanggal_pendaftaran)
    EditText editTextTanggalPedaftaran;
    @BindView(R.id.edit_text_keterangan)
    EditText editTextKeterangan;
    @BindView(R.id.text_spinner_dokter)
    TextView textSpinnerDokter;
    @BindView(R.id.spinner_dokter)
    Spinner spinnerDokter;
    @BindView(R.id.text_spinner_pasien)
    TextView textSpinnerPasien;
    @BindView(R.id.spinner_pasien)
    Spinner spinnerPasien;
    @BindView(R.id.text_spinner_poliklinik)
    TextView textSpinnerPoliklinik;
    @BindView(R.id.spinner_poliklinik)
    Spinner spinnerPoliklinik;
    @BindView(R.id.button_sign_up)
    Button buttonSignUp;
    @BindView(R.id.cardregister)
    CardView cardregister;

    String tanggalPendaftaran, keteranganAntrian;
    int poliklinikID, dokterID, pasienID;
    private AdminAddAntrianPresenter adminAddAntrianPresenter = new AdminAddAntrianPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_antrian);
        ButterKnife.bind(this);
        adminAddAntrianPresenter.getPoliklinikData();
        adminAddAntrianPresenter.getPasienData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAddAntrianActivity.this, AdminAntrianListActivity.class));
        finish();
    }

    @Override
    public void onFinishDialog(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
        String getDate = simpleDateFormat.format(date);
        editTextTanggalPedaftaran.setText(getDate);
    }

    @OnClick({R.id.edit_text_tanggal_pendaftaran, R.id.text_spinner_dokter, R.id.text_spinner_pasien, R.id.text_spinner_poliklinik, R.id.button_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_text_tanggal_pendaftaran:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "AdminAddAntrianActivity.DateDialog");
                break;
            case R.id.text_spinner_dokter:
                textSpinnerDokter.setVisibility(View.GONE);
                spinnerDokter.setVisibility(View.VISIBLE);
                break;
            case R.id.text_spinner_pasien:
                textSpinnerPasien.setVisibility(View.GONE);
                spinnerPasien.setVisibility(View.VISIBLE);
                break;
            case R.id.text_spinner_poliklinik:
                textSpinnerPoliklinik.setVisibility(View.GONE);
                spinnerPoliklinik.setVisibility(View.VISIBLE);
                break;
            case R.id.button_sign_up:
                keteranganAntrian = editTextKeterangan.getText().toString();
                tanggalPendaftaran = editTextTanggalPedaftaran.getText().toString();
                if (keteranganAntrian.isEmpty()) {
                    editTextKeterangan.setError("Isi keterangan terleboh dahulu!");
                    editTextKeterangan.requestFocus();
                } else if (tanggalPendaftaran.isEmpty()) {
                    editTextTanggalPedaftaran.setError("Pilih umur terlebih dahulu!");
                } else {
                    adminAddAntrianPresenter.insertAntrianData(tanggalPendaftaran, dokterID, pasienID, poliklinikID, keteranganAntrian);
                }
                break;
        }
    }

    @Override
    public void onGetPasienDataSuccess(PasienModel pasienModel) {
        spinnerPasien.setAdapter(new SpinnerPasienAdapter(AdminAddAntrianActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(pasienModel).getHasil()));
        spinnerPasien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPasien.setVisibility(View.GONE);
                textSpinnerPasien.setVisibility(View.VISIBLE);
                pasienID = pasienModel.getHasil().get(position).getId_user();
                textSpinnerPasien.setText(String.valueOf(pasienModel.getHasil().get(position).getPasien_name()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSpinnerPasien.setText("");
            }
        });
    }

    @Override
    public void onGetPasienDataFailed(String errorMessage) {
        Toast.makeText(AdminAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPoliklinikDataSuccess(PoliklinikModel poliklinikModel) {
        spinnerPoliklinik.setAdapter(new SpinnerPoliklinikAdapter(AdminAddAntrianActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(poliklinikModel).getHasil()));
        spinnerPoliklinik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPoliklinik.setVisibility(View.GONE);
                textSpinnerPoliklinik.setVisibility(View.VISIBLE);
                poliklinikID = poliklinikModel.getHasil().get(position).getPoliklinik_id();
                adminAddAntrianPresenter.getDokterDataByPoliklinikID(poliklinikID);
                textSpinnerPoliklinik.setText(String.valueOf(poliklinikModel.getHasil().get(position).getPoliklinik_name()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSpinnerPoliklinik.setText("");
            }
        });
    }

    @Override
    public void onGetPoliklinikDataFailed(String errorMessage) {
        Toast.makeText(AdminAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDokterDataByPoliIDSuccess(DokterDetailModel dokterDetailModel) {
        spinnerDokter.setAdapter(new SpinnerDokterAdapter(AdminAddAntrianActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(dokterDetailModel).getHasil()));
        spinnerDokter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerDokter.setVisibility(View.GONE);
                textSpinnerDokter.setVisibility(View.VISIBLE);
                dokterID = dokterDetailModel.getHasil().get(position).getDokter_id();
                textSpinnerDokter.setText(String.valueOf(dokterDetailModel.getHasil().get(position).getDokter_name()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSpinnerDokter.setText("");
            }
        });
    }

    @Override
    public void onGetDokterDataByPoliIDFailed(String errorMessage) {
        Toast.makeText(AdminAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertAntrianDataSuccess(RegisterResponse registerResponse) {
        if (!Objects.requireNonNull(registerResponse).isResponse()) {
            Toast.makeText(AdminAddAntrianActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdminAddAntrianActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInsertAntrianDataFailed(String errorMessage) {
        Toast.makeText(AdminAddAntrianActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
