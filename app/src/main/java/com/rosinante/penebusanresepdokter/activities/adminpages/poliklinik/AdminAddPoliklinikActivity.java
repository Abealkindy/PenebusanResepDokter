package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddPoliklinikActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_nama_poliklinik)
    EditText editTextNamaPoliklinik;
    @BindView(R.id.button_add_poliklinik)
    Button buttonAddPoliklinik;
    @BindView(R.id.cardregister)
    CardView cardregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_poliklinik);
        ButterKnife.bind(this);
    }


    private void addPoliklinik(String namaPoliklinik) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> poliklinikResponse = apiService.add_poliklinik(namaPoliklinik);
        poliklinikResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(AdminAddPoliklinikActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminAddPoliklinikActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminAddPoliklinikActivity.this, AdminPoliklinikListActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(AdminAddPoliklinikActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.button_add_poliklinik)
    public void onViewClicked() {
        String namaPoliklinik = editTextNamaPoliklinik.getText().toString();
        if (namaPoliklinik.isEmpty()) {
            editTextNamaPoliklinik.setError("Nama poliklinik ridak boleh kosong!");
            editTextNamaPoliklinik.requestFocus();
        } else {
            addPoliklinik(namaPoliklinik);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAddPoliklinikActivity.this, AdminPoliklinikListActivity.class));
        finish();
    }
}
