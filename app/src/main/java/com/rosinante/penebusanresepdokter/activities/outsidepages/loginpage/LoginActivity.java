package com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage.MainDokterActivity;
import com.rosinante.penebusanresepdokter.activities.farmasipages.mainfarmasipage.MainFarmasiActivity;
import com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage.MainKasirActivity;
import com.rosinante.penebusanresepdokter.activities.outsidepages.mainregisterpage.RegisterActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien.MainPasienActivity;
import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginInterface {

    @BindView(R.id._edit_text_email_user)
    EditText EditTextEmailUser;
    @BindView(R.id._edit_text_password_user)
    EditText EditTextPasswordUser;
    @BindView(R.id.button_sign_in)
    Button buttonSignIn;
    @BindView(R.id.direct_to_signup_page_text_view)
    TextView directToSignupPageTextView;
    private LoginPresenter loginPresenter = new LoginPresenter(this);

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
                    loginPresenter.loginPasien(email_pasien, password_pasien);
                }
                break;
            case R.id.direct_to_signup_page_text_view:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }


    @Override
    public void onLoginPasienSuccess(LoginModel loginModel) {
        if (!loginModel.isResponse()) {
            Toast.makeText(LoginActivity.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
        } else {
            for (int position = 0; position < loginModel.getHasil().size(); position++) {
                UserSession userSession = new UserSession(LoginActivity.this);
                userSession.createLoginSession(loginModel.getHasil().get(position).getUser_password());
                userSession.setIduser(String.valueOf(loginModel.getHasil().get(position).getId_user()));
                userSession.setUsername(loginModel.getHasil().get(position).getUsername());
                userSession.setPassword(loginModel.getHasil().get(position).getUser_password());
                userSession.setRole(loginModel.getHasil().get(position).getUser_role());

                switch (loginModel.getHasil().get(position).getUser_role()) {
                    case "Dokter":
                        Toast.makeText(LoginActivity.this, "You're Doctor!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainDokterActivity.class));
                        finish();
                        break;
                    case "Pasien":
                        Toast.makeText(LoginActivity.this, "You're Patient!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainPasienActivity.class));
                        finish();
                        break;
                    case "Admin":
                        Toast.makeText(LoginActivity.this, "You're Admin!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
                        finish();
                        break;
                    case "Farmasi":
                        Toast.makeText(LoginActivity.this, "You're Farmasi!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainFarmasiActivity.class));
                        finish();
                        break;
                    case "Kasir":
                        Toast.makeText(LoginActivity.this, "You're Kasir!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainKasirActivity.class));
                        finish();
                        break;
                }
            }
        }
    }

    @Override
    public void onLoginPasienFailed(String errorMessage) {
        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
