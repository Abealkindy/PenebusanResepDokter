package com.rosinante.penebusanresepdokter.activities.adminpages.detail;

import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminDetailListPresenter {
    private AdminDetailListInterface adminDetailListInterface;

    public AdminDetailListPresenter(AdminDetailListInterface adminDetailListInterface) {
        this.adminDetailListInterface = adminDetailListInterface;
    }

    public void getDetailData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getDetailForPharmacy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailFarmasiModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailFarmasiModel detailFarmasiModel) {
                        adminDetailListInterface.onGetDetailDataSuccess(detailFarmasiModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminDetailListInterface.onGetDetailDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
