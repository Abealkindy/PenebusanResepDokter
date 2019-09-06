package com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien;

import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.models.PasienDetailModel;

public interface MainPasienInterface {
    void onGetPasienAntrianStatusSuccess(PasienDetailModel pasienDetailModel);

    void onGetPasienAntrianStatusFailed(String errorMessage);

    void onGetPasienDataForIntentSuccess(PasienDetailModel pasienDetailModel);

    void onGetPasienDataForIntentFailed(String errorMessage);

    void onGetAntrianDataSuccess(AntrianModel antrianModel);

    void onGetAntrianDataFailed(String errorMessage);
}
