package com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminaddobatpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminobatlistpage.AdminObatListActivity;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminAddObatActivity extends AppCompatActivity implements AdminAddObatInterface {

    @BindView(R.id.edit_text_nama_obat)
    EditText editTextNamaObat;
    @BindView(R.id.edit_text_harga_obat)
    EditText editTextHargaObat;
    @BindView(R.id.button_add_obat)
    Button buttonAddObat;
    @BindView(R.id.cardregister)
    CardView cardregister;
    private AdminAddObatPresenter addObatData = new AdminAddObatPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_obat);
        ButterKnife.bind(this);
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
            addObatData.addObatData(editTextNamaObat.getText().toString(), editTextHargaObat.getText().toString());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAddObatActivity.this, AdminObatListActivity.class));
        finish();
    }

    @Override
    public void onInsertObatDataSuccess(RegisterResponse registerResponse) {
        if (!Objects.requireNonNull(registerResponse).isResponse()) {
            Toast.makeText(AdminAddObatActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdminAddObatActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminAddObatActivity.this, AdminObatListActivity.class));
            finish();
        }
    }

    @Override
    public void onInsertObatDataFailed(String errorMessage) {
        Toast.makeText(AdminAddObatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
