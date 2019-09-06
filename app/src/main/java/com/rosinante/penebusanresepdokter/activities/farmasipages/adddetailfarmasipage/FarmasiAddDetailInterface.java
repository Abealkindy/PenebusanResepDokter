package com.rosinante.penebusanresepdokter.activities.farmasipages.adddetailfarmasipage;

import com.rosinante.penebusanresepdokter.models.ObatModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface FarmasiAddDetailInterface {
    void onGetObatDataSuccess(ObatModel obatModel);

    void onGetObatDataFailed(String errorMessage);

    void onAddDetailDataSuccess(RegisterResponse registerResponse);

    void onAddDetailDataFailed(String errorMessage);
}
