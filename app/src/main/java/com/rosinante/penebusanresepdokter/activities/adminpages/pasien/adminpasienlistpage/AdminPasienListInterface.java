package com.rosinante.penebusanresepdokter.activities.adminpages.pasien.adminpasienlistpage;

import com.rosinante.penebusanresepdokter.models.PasienModel;

public interface AdminPasienListInterface {
    void onGetAllPasienDataSuccess(PasienModel pasienModel);

    void onGetAllPasienDataFailed(String errorMessage);
}
