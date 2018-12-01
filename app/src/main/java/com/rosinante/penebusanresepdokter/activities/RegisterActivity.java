package com.rosinante.penebusanresepdokter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.LoginModel;
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

public class RegisterActivity extends AppCompatActivity {

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
                    registerPasien(username, confirmpassword, role);
                }
                break;
            case R.id.direct_to_signin_page_text_view:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void registerPasien(String username, String confirmpassword, String role) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<RegisterResponse> registerResponseCall = apiService.request_register(username, confirmpassword, role);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {

                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }


            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
