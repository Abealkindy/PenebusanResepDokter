package com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage;

import com.rosinante.penebusanresepdokter.models.ResepAllModel;

public interface MainKasirInterface {
    void onGetKasirResepSuccess(ResepAllModel resepAllModel);

    void onGetKasirResepFailed(String errorMessage);
}
