package com.rosinante.penebusanresepdokter.activities.pasienpages.editpasienprofile;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface EditPasienProfileInterface {
    void onSuccessEditPasienProfile(RegisterResponse registerResponse);

    void onFailedEditPasienProfile(String errorMessage);
}
