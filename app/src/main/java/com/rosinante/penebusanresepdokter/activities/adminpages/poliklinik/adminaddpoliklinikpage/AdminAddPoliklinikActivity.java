package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminaddpoliklinikpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminpolikliniklistpage.AdminPoliklinikListActivity;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminAddPoliklinikActivity extends AppCompatActivity implements AdminAddPoliklinikInterface {

    @BindView(R.id.edit_text_nama_poliklinik)
    EditText editTextNamaPoliklinik;
    @BindView(R.id.button_add_poliklinik)
    Button buttonAddPoliklinik;
    @BindView(R.id.cardregister)
    CardView cardregister;
    private AdminAddPoliklinikPresenter addPoliklinik = new AdminAddPoliklinikPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_poliklinik);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button_add_poliklinik)
    public void onViewClicked() {
        String namaPoliklinik = editTextNamaPoliklinik.getText().toString();
        if (namaPoliklinik.isEmpty()) {
            editTextNamaPoliklinik.setError("Nama poliklinik ridak boleh kosong!");
            editTextNamaPoliklinik.requestFocus();
        } else {
            addPoliklinik.addPoliklinik(namaPoliklinik);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAddPoliklinikActivity.this, AdminPoliklinikListActivity.class));
        finish();
    }

    @Override
    public void onInsertPoliklinikDataSuccess(RegisterResponse registerResponse) {
        if (!Objects.requireNonNull(registerResponse).isResponse()) {
            Toast.makeText(AdminAddPoliklinikActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdminAddPoliklinikActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminAddPoliklinikActivity.this, AdminPoliklinikListActivity.class));
            finish();
        }
    }

    @Override
    public void onInsertPoliklinikDataFailed(String errorMessage) {
        Toast.makeText(AdminAddPoliklinikActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
