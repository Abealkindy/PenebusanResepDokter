package com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminantrianlistpage;

import com.rosinante.penebusanresepdokter.models.AntrianModel;

public interface AdminAntrianListInterface {
    void onGetAntrianDataSuccess(AntrianModel antrianModel);

    void onGetAntrianDataFailed(String errorMessage);
}
