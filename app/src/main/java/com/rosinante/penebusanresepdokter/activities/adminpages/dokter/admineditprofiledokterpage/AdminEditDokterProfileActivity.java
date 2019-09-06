package com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admineditprofiledokterpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admindokterlistpage.AdminDokterListActivity;
import com.rosinante.penebusanresepdokter.adapters.spinneradapters.SpinnerPoliklinikAdapter;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminEditDokterProfileActivity extends AppCompatActivity implements AdminEditDokterProfileInterface {

    @BindView(R.id.edit_text_username)
    EditText editTextUsername;
    @BindView(R.id.edit_text_nama_lengkap)
    EditText editTextNamaLengkap;
    @BindView(R.id.edit_text_specialist)
    EditText editTextSpecialist;
    @BindView(R.id.edit_text_alamat)
    EditText editTextAlamat;
    @BindView(R.id.edit_text_phone)
    EditText editTextPhone;
    @BindView(R.id.edit_text_email)
    EditText editTextEmail;
    @BindView(R.id.edit_text_password)
    EditText editTextPassword;
    @BindView(R.id.edit_text_tarif)
    EditText editTextTarif;
    @BindView(R.id.text_spinner_poliklinik)
    TextView textSpinnerPoliklinik;
    @BindView(R.id.spinner_poliklinik)
    Spinner spinnerPoliklinik;
    @BindView(R.id.radio_button_pria)
    RadioButton radioButtonPria;
    @BindView(R.id.radio_button_wanita)
    RadioButton radioButtonWanita;
    @BindView(R.id.button_update)
    Button buttonUpdate;
    @BindView(R.id.button_delete)
    Button buttonDelete;
    @BindView(R.id.cardregister)
    CardView cardregister;

    String usernameDokter, passwordDokter, namaDokter, tarifDokter, genderDokter, addressDokter, spesialisDokter, phoneDokter, emailDokter, poliklinikName;
    int poliklinikIdDokter;
    private AdminEditDokterProfilePresenter adminEditDokterProfilePresenter = new AdminEditDokterProfilePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_dokter_profile);
        ButterKnife.bind(this);

        String username = getIntent().getStringExtra("username");
        String userPassword = getIntent().getStringExtra("user_password");
        String dokterName = getIntent().getStringExtra("dokter_name");
        String dokterGender = getIntent().getStringExtra("dokter_gender");
        String dokterAddress = getIntent().getStringExtra("dokter_address");
        String dokterSpecialist = getIntent().getStringExtra("dokter_specialist");
        String dokterEmail = getIntent().getStringExtra("dokter_email");
        String dokterPhone = getIntent().getStringExtra("dokter_phone");
        String doktertarif = getIntent().getStringExtra("dokter_tarif");
        String namePoliklinik = getIntent().getStringExtra("poliklinik_name");

        if (dokterGender == null || dokterAddress == null || dokterSpecialist == null || dokterEmail == null || dokterPhone == null || doktertarif == null) {
            editTextAlamat.setText("");
            editTextSpecialist.setText("");
            editTextEmail.setText("");
            editTextPhone.setText("");
            editTextTarif.setText("");
            radioButtonPria.setChecked(false);
            radioButtonWanita.setChecked(false);
        } else {
            switch (dokterGender) {
                case "Pria":
                    radioButtonPria.setChecked(true);
                    radioButtonWanita.setChecked(false);
                    break;
                case "Wanita":
                    radioButtonPria.setChecked(false);
                    radioButtonWanita.setChecked(true);
                    break;
                default:
                    radioButtonPria.setChecked(false);
                    radioButtonWanita.setChecked(false);
                    break;
            }

            editTextAlamat.setText(dokterAddress);
            editTextSpecialist.setText(dokterSpecialist);
            editTextEmail.setText(dokterEmail);
            editTextPhone.setText(dokterPhone);
            editTextTarif.setText(doktertarif);
        }
        editTextUsername.setText(username);
        editTextPassword.setText(userPassword);
        editTextNamaLengkap.setText(dokterName);
        adminEditDokterProfilePresenter.getPoliklinikData();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminEditDokterProfileActivity.this, AdminDokterListActivity.class));
        finish();
    }

    @OnClick({R.id.text_spinner_poliklinik, R.id.radio_button_pria, R.id.radio_button_wanita, R.id.button_update, R.id.button_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_spinner_poliklinik:
                textSpinnerPoliklinik.setVisibility(View.GONE);
                spinnerPoliklinik.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_button_pria:
                genderDokter = "Pria";
                break;
            case R.id.radio_button_wanita:
                genderDokter = "Wanita";
                break;
            case R.id.button_update:
                int dokterID = getIntent().getIntExtra("dokter_id", 0);
                usernameDokter = editTextUsername.getText().toString();
                passwordDokter = editTextPassword.getText().toString();
                namaDokter = editTextNamaLengkap.getText().toString();
                addressDokter = editTextAlamat.getText().toString();
                spesialisDokter = editTextSpecialist.getText().toString();
                emailDokter = editTextEmail.getText().toString();
                phoneDokter = editTextPhone.getText().toString();
                tarifDokter = editTextTarif.getText().toString();
                if (radioButtonPria.isChecked()) {
                    genderDokter = "Pria";
                } else if (radioButtonWanita.isChecked()) {
                    genderDokter = "Wanita";
                }
                adminEditDokterProfilePresenter.updateDokter(dokterID, usernameDokter, passwordDokter, addressDokter, spesialisDokter, emailDokter, phoneDokter, tarifDokter, poliklinikIdDokter, genderDokter, namaDokter);
                break;
            case R.id.button_delete:
                int dokterIDs = getIntent().getIntExtra("dokter_id", 0);
                adminEditDokterProfilePresenter.deleteDokter(dokterIDs);
                break;
        }
    }


    @Override
    public void onGetPoliklinikDataSuccess(PoliklinikModel poliklinikModel) {
        spinnerPoliklinik.setAdapter(new SpinnerPoliklinikAdapter(AdminEditDokterProfileActivity.this, android.R.layout.simple_spinner_item, Objects.requireNonNull(poliklinikModel).getHasil()));
        spinnerPoliklinik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPoliklinik.setVisibility(View.GONE);
                textSpinnerPoliklinik.setVisibility(View.VISIBLE);
                poliklinikIdDokter = poliklinikModel.getHasil().get(position).getPoliklinik_id();
                textSpinnerPoliklinik.setText(String.valueOf(poliklinikModel.getHasil().get(position).getPoliklinik_name()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerPoliklinik.setVisibility(View.GONE);
                textSpinnerPoliklinik.setVisibility(View.VISIBLE);
                poliklinikIdDokter = poliklinikModel.getHasil().get(0).getPoliklinik_id();
                textSpinnerPoliklinik.setText(String.valueOf(poliklinikModel.getHasil().get(0).getPoliklinik_name()));
            }
        });
    }

    @Override
    public void onGetPoliklinikDataFailed(String errorMessage) {
        Toast.makeText(AdminEditDokterProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdateDokterDataSuccess(RegisterResponse registerResponse) {
        if (!Objects.requireNonNull(registerResponse).isResponse()) {
            Toast.makeText(AdminEditDokterProfileActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdminEditDokterProfileActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminEditDokterProfileActivity.this, AdminDokterListActivity.class));
            finish();
        }
    }

    @Override
    public void onUpdateDokterDataFailed(String errorMessage) {
        Toast.makeText(AdminEditDokterProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteDokterDataSuccess(RegisterResponse registerResponse) {
        if (!Objects.requireNonNull(registerResponse).isResponse()) {
            Toast.makeText(AdminEditDokterProfileActivity.this, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdminEditDokterProfileActivity.this, "Sukses!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminEditDokterProfileActivity.this, AdminDokterListActivity.class));
            finish();
        }
    }

    @Override
    public void onDeleteDokterDataFailed(String errorMessage) {
        Toast.makeText(AdminEditDokterProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
