package com.rosinante.penebusanresepdokter.activities.dokterpages.dokterresephistorypage;

import com.rosinante.penebusanresepdokter.models.ResepDokterModel;

public interface DokterResepHistoryInterface {
    void onGetAllResepSuccess(ResepDokterModel resepDokterModel);

    void onGetAllResepFailed(String errorMessage);
}
