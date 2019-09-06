package com.rosinante.penebusanresepdokter.activities.adminpages.resep;

import com.rosinante.penebusanresepdokter.models.ResepModel;

public interface AdminResepListInterface {
    void onGetAllResepDataSuccess(ResepModel resepModel);

    void onGetAllResepDataFailed(String errorMessage);
}
