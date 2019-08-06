package com.rosinante.penebusanresepdokter.activities.pasienpages;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.LoginActivity;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasienProfileActivity extends AppCompatActivity {


    @BindView(R.id.edit_text_username_pasien)
    EditText editTextUsernamePasien;
    @BindView(R.id.edit_text_nama_pasien)
    EditText editTextNamaPasien;
    @BindView(R.id.edit_text_umur_pasien)
    EditText editTextUmurPasien;
    @BindView(R.id.edit_text_alamat_pasien)
    EditText editTextAlamatPasien;
    @BindView(R.id.edit_text_phone_pasien)
    EditText editTextPhonePasien;
    @BindView(R.id.edit_text_email_pasien)
    EditText editTextEmailPasien;
    @BindView(R.id.edit_text_password_pasien)
    EditText editTextPasswordPasien;
    @BindView(R.id.radio_button_pria)
    RadioButton radioButtonPria;
    @BindView(R.id.radio_button_wanita)
    RadioButton radioButtonWanita;
    @BindView(R.id.button_update_pasien)
    Button buttonUpdatePasien;
    @BindView(R.id.cardregister)
    CardView cardregister;

    DatePickerDialog datePickerDialog;
    Calendar calendar;
    int year, month, dateOfMonth, hours, minute, second, millsecond;
    String pasienUsername, pasienPassword, pasienName, pasienGender, pasienAddress, dateUmur, pasienEmail, pasienPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pasien_profile);
        ButterKnife.bind(this);
        String usernamePasien = getIntent().getStringExtra("username_pasien");
        String passwordPasien = getIntent().getStringExtra("user_password_pasien");
        String namaPasien = getIntent().getStringExtra("pasien_name_pasien");
        String genderPasien = getIntent().getStringExtra("pasien_gender_pasien");
        String addressPasien = getIntent().getStringExtra("pasien_address_pasien");
        String agePasien = getIntent().getStringExtra("pasien_age_pasien");
        String emailPasien = getIntent().getStringExtra("pasien_email_pasien");
        String phonePasien = getIntent().getStringExtra("pasien_phone_pasien");

        if (genderPasien == null || addressPasien == null || agePasien == null || emailPasien == null || phonePasien == null) {
            editTextAlamatPasien.setText("");
            editTextEmailPasien.setText("");
            editTextPhonePasien.setText("");
            editTextUmurPasien.setText("");
            radioButtonPria.setChecked(false);
            radioButtonWanita.setChecked(false);
        } else {
            editTextAlamatPasien.setText(addressPasien);
            editTextEmailPasien.setText(emailPasien);
            editTextPhonePasien.setText(phonePasien);
            switch (genderPasien) {
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
            getAge(agePasien);

            editTextUsernamePasien.setText(usernamePasien);
            editTextPasswordPasien.setText(passwordPasien);
            editTextNamaPasien.setText(namaPasien);

        }
    }

    @SuppressLint("SetTextI18n")
    private void getAge(String dobString) {

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date == null) return;

        Calendar dob = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        LocalDate birthDate = new LocalDate(year, month + 1, day);
        LocalDate nowDate = LocalDate.now();
        Period period = new Period(birthDate, nowDate, PeriodType.yearMonthDay());
        editTextUmurPasien.setText(period.getYears() + " Tahun " + period.getMonths() + " Bulan " + period.getDays() + " Hari");

    }

    private void updatePasien(int idPasien, String pasienUsername, String pasienPassword, String pasienName, String pasienGender, String pasienAddress, String dateUmur, String pasienEmail, String pasienPhone) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> updatePasienCall = apiService.request_update_pasien(
                idPasien,
                idPasien,
                pasienUsername,
                pasienPassword,
                pasienName,
                pasienGender,
                pasienAddress,
                dateUmur,
                pasienEmail,
                pasienPhone
        );
        updatePasienCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(EditPasienProfileActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditPasienProfileActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditPasienProfileActivity.this, MainPasienActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(EditPasienProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditPasienProfileActivity.this, MainPasienActivity.class));
        finish();
    }


    @SuppressLint("SetTextI18n")
    @OnClick({R.id.edit_text_umur_pasien, R.id.radio_button_pria, R.id.radio_button_wanita, R.id.button_update_pasien})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_text_umur_pasien:
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                hours = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                second = calendar.get(Calendar.SECOND);
                millsecond = calendar.get(Calendar.MILLISECOND);
                datePickerDialog = new DatePickerDialog(EditPasienProfileActivity.this, (view1, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
                    dateUmur = simpleDateFormat.format(calendar.getTime());
                    LocalDate birthDate = new LocalDate(year, month, dayOfMonth);
                    LocalDate nowDate = LocalDate.now();
                    Period period = new Period(birthDate, nowDate, PeriodType.yearMonthDay());
                    editTextUmurPasien.setText(period.getYears() + " Tahun " + period.getMonths() + " Bulan " + period.getDays() + " Hari");
                }, year, month, dateOfMonth);
                datePickerDialog.show();

                break;
            case R.id.radio_button_pria:
                pasienGender = "Pria";
                break;
            case R.id.radio_button_wanita:
                pasienGender = "Wanita";
                break;
            case R.id.button_update_pasien:
                int IDPasien = getIntent().getIntExtra("pasien_id_pasien", 0);
                pasienUsername = editTextUsernamePasien.getText().toString();
                pasienPassword = editTextPasswordPasien.getText().toString();
                pasienName = editTextNamaPasien.getText().toString();
                pasienAddress = editTextAlamatPasien.getText().toString();
                pasienEmail = editTextEmailPasien.getText().toString();
                pasienPhone = editTextPhonePasien.getText().toString();
                if (radioButtonPria.isChecked()) {
                    pasienGender = "Pria";
                } else if (radioButtonWanita.isChecked()) {
                    pasienGender = "Wanita";
                }
                if (dateUmur == null) {
                    dateUmur = getIntent().getStringExtra("pasien_age_pasien");
                }
                updatePasien(IDPasien, pasienUsername, pasienPassword, pasienName, pasienGender, pasienAddress, dateUmur, pasienEmail, pasienPhone);
                break;
        }
    }
}
