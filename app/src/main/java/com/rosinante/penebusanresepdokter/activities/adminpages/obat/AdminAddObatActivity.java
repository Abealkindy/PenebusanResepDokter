package com.rosinante.penebusanresepdokter.activities.adminpages.obat;

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

public class AdminAddObatActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_nama_obat)
    EditText editTextNamaObat;
    @BindView(R.id.edit_text_harga_obat)
    EditText editTextHargaObat;
    @BindView(R.id.button_add_obat)
    Button buttonAddObat;
    @BindView(R.id.cardregister)
    CardView cardregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_obat);
        ButterKnife.bind(this);
    }


    private void addObatData(String namaObat, String hargaObat) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> addObatCall = apiService.add_obat(
                namaObat,
                Double.parseDouble(hargaObat)
        );
        addObatCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(AdminAddObatActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminAddObatActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AdminAddObatActivity.this, AdminObatListActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(AdminAddObatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.button_add_obat)
    public void onViewClicked() {
        if (editTextNamaObat.getText().toString().isEmpty()) {
            editTextNamaObat.setError("Nama obat tidak boleh kosong!");
            editTextNamaObat.requestFocus();
        } else if (editTextHargaObat.getText().toString().isEmpty()) {
            editTextHargaObat.setError("Harga obat tidak boleh kosong!");
            editTextHargaObat.requestFocus();
        } else {
            addObatData(editTextNamaObat.getText().toString(), editTextHargaObat.getText().toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAddObatActivity.this, AdminObatListActivity.class));
        finish();
    }
}
