package com.rosinante.penebusanresepdokter.activities.adminpages.detail;

import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;

public interface AdminDetailListInterface {
    void onGetDetailDataSuccess(DetailFarmasiModel detailFarmasiModel);

    void onGetDetailDataFailed(String errorMessage);
}
