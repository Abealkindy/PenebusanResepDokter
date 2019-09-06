package com.rosinante.penebusanresepdokter.activities.adminpages.user.adminregisteruserpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminRegisterActivity extends AppCompatActivity implements AdminRegisterUserInterface {

    @BindView(R.id.edit_text_username)
    EditText editTextUsername;
    @BindView(R.id.edit_text_password_user)
    EditText editTextPasswordUser;
    @BindView(R.id.edit_text_confirm_password_user)
    EditText editTextConfirmPasswordUser;
    @BindView(R.id.text_spinner_role)
    TextView textSpinnerRole;
    @BindView(R.id.spinner_role)
    Spinner spinnerRole;
    @BindView(R.id.button_sign_up)
    Button buttonSignUp;
    @BindView(R.id.cardregister)
    CardView cardregister;

    String[] roleArray = {"Admin", "Dokter", "Farmasi", "Pasien", "Kasir"};
    String username, password, confirmpassword, role;
    int passLength;
    private AdminRegisterUserPresenter adminRegisterUserPresenter = new AdminRegisterUserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        ButterKnife.bind(this);

        ArrayAdapter<String> spinnerRoleArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roleArray);
        spinnerRoleArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(spinnerRoleArrayAdapter);
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textSpinnerRole.setVisibility(View.VISIBLE);
                spinnerRole.setVisibility(View.GONE);
                textSpinnerRole.setText(roleArray[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminRegisterActivity.this, MainAdminActivity.class));
        finish();
    }

    @OnClick({R.id.text_spinner_role, R.id.button_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_spinner_role:
                spinnerRole.setVisibility(View.VISIBLE);
                textSpinnerRole.setVisibility(View.GONE);
                break;
            case R.id.button_sign_up:
                username = editTextUsername.getText().toString();
                password = editTextPasswordUser.getText().toString();
                role = textSpinnerRole.getText().toString();
                confirmpassword = editTextConfirmPasswordUser.getText().toString();
                passLength = confirmpassword.length();

                if (username.isEmpty()) {
                    editTextUsername.setError("Isi username terlebih dahulu!");
                } else if (password.isEmpty()) {
                    editTextPasswordUser.setError("Isi password terlebih dahulu!");
                } else if (confirmpassword.isEmpty()) {
                    editTextConfirmPasswordUser.setError("Konfirmasi password terlebih dahulu!");
                } else if (!confirmpassword.equals(password)) {
                    editTextConfirmPasswordUser.setError("Konfirmasi password salah!");
                } else {
                    adminRegisterUserPresenter.registerUser(username, confirmpassword, role);
                }
                break;
        }
    }

    @Override
    public void onInsertUserRegistrationDataSuccess(RegisterResponse registerResponse) {
        if (!Objects.requireNonNull(registerResponse).isResponse()) {
            Toast.makeText(AdminRegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AdminRegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminRegisterActivity.this, MainAdminActivity.class));
            finish();
        }

    }

    @Override
    public void onInsertUserRegistrationDataFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
