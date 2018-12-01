package com.rosinante.penebusanresepdokter.activities.kasirpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class KasirAddPembayaranActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_uang_bayar)
    EditText editTextUangBayar;
    @BindView(R.id.text_kembalian)
    TextView textKembalian;
    @BindView(R.id.button_add_pembayaran)
    Button buttonAddPembayaran;
    @BindView(R.id.cardregister)
    CardView cardregister;

    int pasienID, antrianID, resepID;
    double totalHarga, uangBayar, kembalian;
    String resepDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir_add_pembayaran);
        ButterKnife.bind(this);
        pasienID = getIntent().getIntExtra("pasienID", 0);
        antrianID = getIntent().getIntExtra("antrianID", 0);
        resepID = getIntent().getIntExtra("resepID", 0);
        totalHarga = getIntent().getDoubleExtra("totalHarga", 0);
        resepDate = getIntent().getStringExtra("resepDate");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(KasirAddPembayaranActivity.this, MainKasirActivity.class));
        finish();
    }

    @OnClick(R.id.button_add_pembayaran)
    public void onViewClicked() {
        String uangBayarString = editTextUangBayar.getText().toString();
        if (uangBayarString.isEmpty()) {
            uangBayar = 0.0;
            kembalian = uangBayar - totalHarga;
            textKembalian.setText(String.valueOf(kembalian));
        } else {
            uangBayar = Double.parseDouble(uangBayarString);
            kembalian = uangBayar - totalHarga;
            textKembalian.setText(String.valueOf(kembalian));

            addPembayaran(pasienID, antrianID, resepID, resepDate, uangBayar, kembalian);
        }
    }

    private void addPembayaran(int pasienID, int antrianID, int resepID, String resepDate, double uangBayar, double kembalian) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> addPembayaranResponse = apiService.addPembayaran(
                pasienID,
                antrianID,
                resepID,
                resepDate,
                uangBayar,
                kembalian
        );
        addPembayaranResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(KasirAddPembayaranActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(KasirAddPembayaranActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(KasirAddPembayaranActivity.this, MainKasirActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(KasirAddPembayaranActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
