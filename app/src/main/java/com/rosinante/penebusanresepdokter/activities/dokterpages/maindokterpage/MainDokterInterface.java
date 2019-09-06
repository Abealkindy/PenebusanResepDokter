package com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage;

import com.rosinante.penebusanresepdokter.models.AntrianDokterModel;

public interface MainDokterInterface {
    void onGetAntrianPasienDataSuccess(AntrianDokterModel antrianDokterModel);

    void onGetAntrianPasienDataFailed(String errorMessage);
}
