package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminpolikliniklistpage;

import com.rosinante.penebusanresepdokter.models.PoliklinikModel;

public interface AdminPoliklinikListInterface {
    void onGetAllPoliklinikDataSuccess(PoliklinikModel poliklinikModel);

    void onGetAllPoliklinikDataFailed(String errorMessage);
}
