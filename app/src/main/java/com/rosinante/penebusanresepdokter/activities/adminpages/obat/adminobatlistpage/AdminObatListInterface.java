package com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminobatlistpage;

import com.rosinante.penebusanresepdokter.models.ObatModel;

public interface AdminObatListInterface {
    void onGetAllObatDataSuccess(ObatModel obatModel);

    void onGetAllObatDataFailed(String errorMessage);
}
