package com.rosinante.penebusanresepdokter.activities.kasirpages.kasirpembayaranhistorypage;

import com.rosinante.penebusanresepdokter.models.PembayaranModel;

public interface KasirPembayaranHistoryInterface {
    void onGetAllPembayaranDataSuccess(PembayaranModel pembayaranModel);

    void onGetAllPembayaranDataFailed(String errorMessage);
}
