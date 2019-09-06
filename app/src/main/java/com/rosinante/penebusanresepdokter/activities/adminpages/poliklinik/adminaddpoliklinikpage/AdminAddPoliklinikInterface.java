package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminaddpoliklinikpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface AdminAddPoliklinikInterface {
    void onInsertPoliklinikDataSuccess(RegisterResponse registerResponse);

    void onInsertPoliklinikDataFailed(String errorMessage);
}
