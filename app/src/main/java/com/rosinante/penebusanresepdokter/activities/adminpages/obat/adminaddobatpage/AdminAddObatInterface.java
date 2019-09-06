package com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminaddobatpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface AdminAddObatInterface {
    void onInsertObatDataSuccess(RegisterResponse registerResponse);

    void onInsertObatDataFailed(String errorMessage);
}
