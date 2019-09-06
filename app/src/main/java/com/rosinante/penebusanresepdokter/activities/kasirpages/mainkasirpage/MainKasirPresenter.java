package com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage;

import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

public class MainKasirPresenter {
    private MainKasirInterface mainKasirInterface;

    public MainKasirPresenter(MainKasirInterface mainKasirInterface) {
        this.mainKasirInterface = mainKasirInterface;
    }

    public void getResepAllData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getResepAll();
    }
}
