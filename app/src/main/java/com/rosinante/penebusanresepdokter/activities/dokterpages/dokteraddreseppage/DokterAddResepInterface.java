package com.rosinante.penebusanresepdokter.activities.dokterpages.dokteraddreseppage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface DokterAddResepInterface {
    void onInsertResepDataSuccess(RegisterResponse registerResponse);

    void onInsertResepDataFailed(String errorMessage);
}
