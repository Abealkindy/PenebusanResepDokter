package com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admindokterlistpage;

import com.rosinante.penebusanresepdokter.models.DokterModel;

public interface AdminDokterListInterface {
    void onGetAllDokterListSuccess(DokterModel dokterModel);

    void onGetAllDokterListFailed(String errorMessage);
}
