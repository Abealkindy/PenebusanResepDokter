package com.rosinante.penebusanresepdokter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.activities.dokterpages.MainDokterActivity;
import com.rosinante.penebusanresepdokter.activities.farmasipages.MainFarmasiActivity;
import com.rosinante.penebusanresepdokter.activities.kasirpages.MainKasirActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.MainPasienActivity;
import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id._edit_text_email_user)
    EditText EditTextEmailUser;
    @BindView(R.id._edit_text_password_user)
    EditText EditTextPasswordUser;
    @BindView(R.id.button_sign_in)
    Button buttonSignIn;
    @BindView(R.id.direct_to_signup_page_text_view)
    TextView directToSignupPageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_sign_in, R.id.direct_to_signup_page_text_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:
                String email_pasien = EditTextEmailUser.getText().toString();
                String password_pasien = EditTextPasswordUser.getText().toString();
                if (email_pasien.isEmpty()) {
                    Toast.makeText(this, "Jangan dikosongin", Toast.LENGTH_SHORT).show();
                } else if (password_pasien.isEmpty()) {
                    Toast.makeText(this, "Jangan dikosongin", Toast.LENGTH_SHORT).show();
                } else {
                    loginPasien(email_pasien, password_pasien);
                }
                break;
            case R.id.direct_to_signup_page_text_view:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }

    private void loginPasien(String email_pasien, String password_pasien) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<LoginModel> loginResponseCall = apiService.request_login(email_pasien, password_pasien);
        loginResponseCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {

                if (!Objects.requireNonNull(response.body()).isResponse()) {
                    Toast.makeText(LoginActivity.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
                } else {
                    for (int position = 0; position < response.body().getHasil().size(); position++) {

                        UserSession userSession = new UserSession(LoginActivity.this);
                        userSession.createLoginSession(response.body().getHasil().get(position).getUser_password());
                        userSession.setIduser(String.valueOf(response.body().getHasil().get(position).getId_user()));
                        userSession.setUsername(response.body().getHasil().get(position).getUsername());
                        userSession.setPassword(response.body().getHasil().get(position).getUser_password());
                        userSession.setRole(response.body().getHasil().get(position).getUser_role());

                        switch (response.body().getHasil().get(position).getUser_role()) {
                            case "Dokter":
                                Toast.makeText(LoginActivity.this, "Your'e Doctor!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainDokterActivity.class));
                                finish();
                                break;
                            case "Pasien":
                                Toast.makeText(LoginActivity.this, "Your'e Patient!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainPasienActivity.class));
                                finish();
                                break;
                            case "Admin":
                                Toast.makeText(LoginActivity.this, "Your'e Admin!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
                                finish();
                                break;
                            case "Farmasi":
                                Toast.makeText(LoginActivity.this, "Your'e Farmasi!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainFarmasiActivity.class));
                                finish();
                                break;
                            case "Kasir":
                                Toast.makeText(LoginActivity.this, "Your'e Kasir!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainKasirActivity.class));
                                finish();
                                break;
                        }
                    }
                }
            }


            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
