package com.rosinante.penebusanresepdokter.activities.farmasipages.farmasidetailhistorypage;

import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;

public interface FarmasiDetailHistoryInterface {
    void onGetFarmasiDetailSuccess(DetailFarmasiModel detailFarmasiModel);

    void onGetFarmasiDetailFailed(String errorMessage);
}
