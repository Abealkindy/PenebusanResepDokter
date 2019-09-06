package com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminaddantrianpage;

import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PasienModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface AdminAddAntrianInterface {
    void onGetPasienDataSuccess(PasienModel pasienModel);

    void onGetPasienDataFailed(String errorMessage);

    void onGetPoliklinikDataSuccess(PoliklinikModel poliklinikModel);

    void onGetPoliklinikDataFailed(String errorMessage);

    void onGetDokterDataByPoliIDSuccess(DokterDetailModel dokterDetailModel);

    void onGetDokterDataByPoliIDFailed(String errorMessage);

    void onInsertAntrianDataSuccess(RegisterResponse registerResponse);

    void onInsertAntrianDataFailed(String errorMessage);
}
