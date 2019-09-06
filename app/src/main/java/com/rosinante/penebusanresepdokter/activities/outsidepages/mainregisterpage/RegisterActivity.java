package com.rosinante.penebusanresepdokter.activities.outsidepages.mainregisterpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage.LoginActivity;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements MainRegisterInterface {

    @BindView(R.id.edit_text_username)
    EditText editTextUsername;
    @BindView(R.id.edit_text_password_user)
    EditText editTextPasswordUser;
    @BindView(R.id.edit_text_confirm_password_user)
    EditText editTextConfirmPasswordUser;
    @BindView(R.id.button_sign_up)
    Button buttonSignUp;
    @BindView(R.id.cardregister)
    CardView cardregister;
    @BindView(R.id.direct_to_signin_page_text_view)
    TextView directToSigninPageTextView;

    String username, password, confirmpassword, role;
    int passLength;
    private MainRegisterPresenter mainRegisterPresenter = new MainRegisterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_sign_up, R.id.direct_to_signin_page_text_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_sign_up:
                username = editTextUsername.getText().toString();
                password = editTextPasswordUser.getText().toString();
                role = "Pasien";
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
                    mainRegisterPresenter.registerPasien(username, confirmpassword, role);
                }
                break;
            case R.id.direct_to_signin_page_text_view:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }


    @Override
    public void onRegisterPasienSuccess(RegisterResponse registerResponse) {
        if (!registerResponse.isResponse()) {
            Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    public void onRegisterPasienFailed(String errorMessage) {
        Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
