package com.rosinante.penebusanresepdokter.activities.farmasipages.mainfarmasipage;

import com.rosinante.penebusanresepdokter.models.ResepModel;

public interface MainFarmasiInterface {
    void onGetResepActiveForFarmasiSuccess(ResepModel resepModel);

    void onGetResepActiveForFarmasiFailed(String errorMessage);
}
