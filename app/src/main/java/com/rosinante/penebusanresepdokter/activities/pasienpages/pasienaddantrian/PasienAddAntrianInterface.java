package com.rosinante.penebusanresepdokter.activities.pasienpages.pasienaddantrian;

import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface PasienAddAntrianInterface {
    void onGetPoliklinikDataSuccess(PoliklinikModel poliklinikModel);

    void onGetPoliklinikDataFailed(String errorMessage);

    void onGetDokterDataByPoliklinikIDSuccess(DokterDetailModel dokterDetailModel);

    void onGetDokterDataByPoliklinikIDFailed(String errorMessage);

    void onInsertPasienAntrianDataSuccess(RegisterResponse registerResponse);

    void onInsertPasienAntrianDataFailed(String errorMessage);
}
