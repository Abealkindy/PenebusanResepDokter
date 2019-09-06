package com.rosinante.penebusanresepdokter.activities.farmasipages.adddetailfarmasipage;

import android.annotation.SuppressLint;
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
import com.rosinante.penebusanresepdokter.activities.farmasipages.mainfarmasipage.MainFarmasiActivity;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerObatAdapter;
import com.rosinante.penebusanresepdokter.models.ObatModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FarmasiAddDetailActivity extends AppCompatActivity implements FarmasiAddDetailInterface {

    @BindView(R.id.text_spinner_obat)
    TextView textSpinnerObat;
    @BindView(R.id.spinner_obat)
    Spinner spinnerObat;
    @BindView(R.id.button_increment)
    Button buttonIncrement;
    @BindView(R.id.edit_text_quantity_obat)
    EditText editTextQuantityObat;
    @BindView(R.id.button_decrement)
    Button buttonDecrement;
    @BindView(R.id.button_add_detail)
    Button buttonAddDetail;
    @BindView(R.id.cardregister)
    CardView cardregister;
    @BindView(R.id.text_total)
    TextView textTotal;

    int quantityObat = 0;
    int obatID;
    double hargaObat, totalHarga;
    private FarmasiAddDetailPresenter farmasiAddDetailPresenter = new FarmasiAddDetailPresenter(this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmasi_add_detail);
        ButterKnife.bind(this);
        farmasiAddDetailPresenter.getObatData();
        editTextQuantityObat.setText(String.valueOf(quantityObat));
        textSpinnerObat.setText("Pilih Obat");
    }

    private void incrementQuantity() {
        if (quantityObat == 100) {
            Toast.makeText(this, "Terlalu banyak ", Toast.LENGTH_SHORT).show();
            return;
        }
        quantityObat = quantityObat + 1;
        showQuantity(quantityObat);
    }

    private void decrementQuantity() {
        if (quantityObat == 0) {
            Toast.makeText(this, "Terlalu Sedikit!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantityObat = quantityObat - 1;
        showQuantity(quantityObat);
    }


    private void showQuantity(int quantityObat) {
        editTextQuantityObat.setText(String.valueOf(quantityObat));
        totalHarga = quantityObat * hargaObat;
        showTotalHarga(totalHarga);
    }

    @SuppressLint("SetTextI18n")
    private void showTotalHarga(double quantityObat) {
        textTotal.setText("Total Harga : Rp." + String.valueOf(quantityObat));
    }

    @OnClick({R.id.text_spinner_obat, R.id.button_increment, R.id.button_decrement, R.id.button_add_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_spinner_obat:
                textSpinnerObat.setVisibility(View.GONE);
                spinnerObat.setVisibility(View.VISIBLE);
                break;
            case R.id.button_increment:
                incrementQuantity();
                break;
            case R.id.button_decrement:
                decrementQuantity();
                break;
            case R.id.button_add_detail:
                int resepID = getIntent().getIntExtra("resepID", 0);
                farmasiAddDetailPresenter.addDetail(obatID, resepID, hargaObat, totalHarga, editTextQuantityObat.getText().toString() + " gr");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FarmasiAddDetailActivity.this, MainFarmasiActivity.class));
        finish();
    }


    @Override
    public void onGetObatDataSuccess(ObatModel obatModel) {
        spinnerObat.setAdapter(new SpinnerObatAdapter(FarmasiAddDetailActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(obatModel).getHasil()));
        spinnerObat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerObat.setVisibility(View.GONE);
                textSpinnerObat.setVisibility(View.VISIBLE);
                obatID = obatModel.getHasil().get(position).getId_obat();
                hargaObat = obatModel.getHasil().get(position).getHarga_obat();
                totalHarga = Double.parseDouble(editTextQuantityObat.getText().toString()) * hargaObat;
                textSpinnerObat.setText(String.valueOf(obatModel.getHasil().get(position).getNama_obat()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSpinnerObat.setText("");
            }
        });
    }

    @Override
    public void onGetObatDataFailed(String errorMessage) {
        Toast.makeText(FarmasiAddDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddDetailDataSuccess(RegisterResponse registerResponse) {
        if (!registerResponse.isResponse()) {
            Toast.makeText(FarmasiAddDetailActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(FarmasiAddDetailActivity.this, MainFarmasiActivity.class));
            finish();
            Toast.makeText(FarmasiAddDetailActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAddDetailDataFailed(String errorMessage) {
        Toast.makeText(FarmasiAddDetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
