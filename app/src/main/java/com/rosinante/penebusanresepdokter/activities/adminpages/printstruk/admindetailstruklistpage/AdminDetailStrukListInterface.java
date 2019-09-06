package com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.admindetailstruklistpage;

import com.rosinante.penebusanresepdokter.models.DetailStrukModel;

public interface AdminDetailStrukListInterface {
    void onGetAllStrukDataSuccess(DetailStrukModel detailStrukModel);

    void onGetAllStrukDataFailed(String errorMessage);
}
