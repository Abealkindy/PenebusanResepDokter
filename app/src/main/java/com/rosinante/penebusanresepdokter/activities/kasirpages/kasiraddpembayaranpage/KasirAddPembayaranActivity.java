package com.rosinante.penebusanresepdokter.activities.kasirpages.kasiraddpembayaranpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage.MainKasirActivity;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KasirAddPembayaranActivity extends AppCompatActivity implements KasirAddPembayaranInterface {

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
    private KasirAddPembayaranPresenter kasirAddPembayaranPresenter = new KasirAddPembayaranPresenter(this);

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

            kasirAddPembayaranPresenter.addPembayaran(pasienID, antrianID, resepID, resepDate, uangBayar, kembalian);
        }
    }


    @Override
    public void onAddPembayaranSuccess(RegisterResponse registerResponse) {
        if (!registerResponse.isResponse()) {
            Toast.makeText(KasirAddPembayaranActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(KasirAddPembayaranActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(KasirAddPembayaranActivity.this, MainKasirActivity.class));
            finish();
        }
    }

    @Override
    public void onAddPembayaranFailed(String errorMessage) {
        Toast.makeText(KasirAddPembayaranActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
