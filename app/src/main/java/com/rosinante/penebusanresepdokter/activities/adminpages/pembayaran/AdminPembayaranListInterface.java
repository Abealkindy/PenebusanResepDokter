package com.rosinante.penebusanresepdokter.activities.adminpages.pembayaran;

import com.rosinante.penebusanresepdokter.models.PembayaranModel;

public interface AdminPembayaranListInterface {
    void onGetAllPembayaranDataSuccess(PembayaranModel pembayaranModel);

    void onGetAllPembayaranDataFailed(String errorMessage);
}
