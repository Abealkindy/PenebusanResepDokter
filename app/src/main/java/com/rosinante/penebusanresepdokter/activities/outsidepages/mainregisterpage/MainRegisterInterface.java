package com.rosinante.penebusanresepdokter.activities.outsidepages.mainregisterpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface MainRegisterInterface {
    void onRegisterPasienSuccess(RegisterResponse registerResponse);

    void onRegisterPasienFailed(String errorMessage);
}
