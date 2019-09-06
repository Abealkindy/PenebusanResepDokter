package com.rosinante.penebusanresepdokter.activities.pasienpages.pasienantrianhistory;

import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

public class PasienAntrianHistoryPresenter {
    private PasienAntrianHistoryInterface pasienAntrianHistoryInterface;

    public PasienAntrianHistoryPresenter(PasienAntrianHistoryInterface pasienAntrianHistoryInterface) {
        this.pasienAntrianHistoryInterface = pasienAntrianHistoryInterface;
    }

    public void getAntrianList(String userID) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getAntrianByPasienID(Integer.parseInt(userID));
    }
}
