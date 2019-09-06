package com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage;

import com.rosinante.penebusanresepdokter.models.LoginModel;

public interface LoginInterface {
    void onLoginPasienSuccess(LoginModel loginModel);

    void onLoginPasienFailed(String errorMessage);
}
