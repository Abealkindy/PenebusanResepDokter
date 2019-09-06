package com.rosinante.penebusanresepdokter.activities.adminpages.pasien.admineditpasienprofile;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface AdminEditPasienProfileInterface {
    void onUpdatePasienProfileDataSuccess(RegisterResponse registerResponse);

    void onUpdatePasienProfileDataFailed(String errorMessage);

    void onDeletePasienProfileDataSuccess(RegisterResponse registerResponse);

    void onDeletePasienProfileDataFailed(String errorMessage);
}
