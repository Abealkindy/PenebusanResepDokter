package com.rosinante.penebusanresepdokter.activities.dokterpages.dokteraddreseppage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage.MainDokterActivity;
import com.rosinante.penebusanresepdokter.fragments.DatePickerFragment;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DokterAddResepActivity extends AppCompatActivity implements DatePickerFragment.DateDialogListener, DokterAddResepInterface {

    @BindView(R.id.edit_text_tanggal_resep)
    EditText editTextTanggalResep;
    @BindView(R.id.edit_text_keterangan_resep)
    EditText editTextKeteranganResep;
    @BindView(R.id.button_add_resep)
    Button buttonAddResep;
    @BindView(R.id.cardregister)
    CardView cardregister;

    String tanggalResep, keteranganResep;
    private DokterAddResepPresenter dokterAddResepPresenter = new DokterAddResepPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_add_resep);
        ButterKnife.bind(this);
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
                    editTextKeteranganResep.setError("Isi keterangan terlebih dahulu!");
                    editTextKeteranganResep.requestFocus();
                } else if (tanggalResep.isEmpty()) {
                    editTextTanggalResep.setError("Pilih umur terlebih dahulu!");
                } else {
                    dokterAddResepPresenter.insertResepData(antrianID, dokterID, pasienID, tanggalResep, keteranganResep);
                }
                break;
        }
    }

    @Override
    public void onInsertResepDataSuccess(RegisterResponse registerResponse) {
        if (!registerResponse.isResponse()) {
            Toast.makeText(DokterAddResepActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DokterAddResepActivity.this, "Berhasil!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DokterAddResepActivity.this, MainDokterActivity.class));
            finish();
        }
    }

    @Override
    public void onInsertResepDataFailed(String errorMessage) {
        Toast.makeText(DokterAddResepActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
