package com.rosinante.penebusanresepdokter.activities.pasienpages.pasienantrianhistory;

import com.rosinante.penebusanresepdokter.models.AntrianPasienModel;

public interface PasienAntrianHistoryInterface {
    void onGetAllAntrianDataSuccess(AntrianPasienModel antrianPasienModel);

    void onGetAllAntrianDataFailed(String errorMessage);
}
