package com.rosinante.penebusanresepdokter.activities.dokterpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.pasienpages.MainPasienActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.PasienAddAntrianActivity;
import com.rosinante.penebusanresepdokter.fragments.DatePickerFragment;
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

public class DokterAddResepActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener {

    @BindView(R.id.edit_text_tanggal_resep)
    EditText editTextTanggalResep;
    @BindView(R.id.edit_text_keterangan_resep)
    EditText editTextKeteranganResep;
    @BindView(R.id.button_add_resep)
    Button buttonAddResep;
    @BindView(R.id.cardregister)
    CardView cardregister;

    String tanggalResep, keteranganResep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_add_resep);
        ButterKnife.bind(this);
    }


    private void insertResepData(int antrianID, int dokterID, int pasienID, String tanggalResep, String keteranganResep) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> resepResponseCall = apiService.addResep(
                antrianID,
                dokterID,
                pasienID,
                tanggalResep,
                keteranganResep
        );
        resepResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(DokterAddResepActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DokterAddResepActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DokterAddResepActivity.this, MainDokterActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(DokterAddResepActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DokterAddResepActivity.this, MainDokterActivity.class));
        finish();
    }

    @Override
    public void onFinishDialog(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
        String getDate = simpleDateFormat.format(date);
        editTextTanggalResep.setText(getDate);
    }

    @OnClick({R.id.edit_text_tanggal_resep, R.id.button_add_resep})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_text_tanggal_resep:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "DokterAddResepActivity.DateDialog");
                break;
            case R.id.button_add_resep:
                int antrianID = getIntent().getIntExtra("antrian_id", 0);
                int dokterID = getIntent().getIntExtra("dokter_id", 0);
                int pasienID = getIntent().getIntExtra("pasien_id", 0);
                keteranganResep = editTextKeteranganResep.getText().toString();
                tanggalResep = editTextTanggalResep.getText().toString();
                if (keteranganResep.isEmpty()) {
                    editTextKeteranganResep.setError("Isi keterangan terleboh dahulu!");
                    editTextKeteranganResep.requestFocus();
                } else if (tanggalResep.isEmpty()) {
                    editTextTanggalResep.setError("Pilih umur terlebih dahulu!");
                } else {
                    insertResepData(antrianID, dokterID, pasienID, tanggalResep, keteranganResep);
                }
                break;
        }
    }

}
