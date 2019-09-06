package com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admineditprofiledokterpage;

import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface AdminEditDokterProfileInterface {
    void onGetPoliklinikDataSuccess(PoliklinikModel poliklinikModel);

    void onGetPoliklinikDataFailed(String errorMessage);

    void onUpdateDokterDataSuccess(RegisterResponse registerResponse);

    void onUpdateDokterDataFailed(String errorMessage);

    void onDeleteDokterDataSuccess(RegisterResponse registerResponse);

    void onDeleteDokterDataFailed(String errorMessage);
}
