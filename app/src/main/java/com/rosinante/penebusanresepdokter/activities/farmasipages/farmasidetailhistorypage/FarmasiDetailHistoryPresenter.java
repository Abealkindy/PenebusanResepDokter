package com.rosinante.penebusanresepdokter.activities.farmasipages.farmasidetailhistorypage;

import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FarmasiDetailHistoryPresenter {
    private FarmasiDetailHistoryInterface farmasiDetailHistoryInterface;

    public FarmasiDetailHistoryPresenter(FarmasiDetailHistoryInterface farmasiDetailHistoryInterface) {
        this.farmasiDetailHistoryInterface = farmasiDetailHistoryInterface;
    }

    public void getDetailForPharmacy() {
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
                        farmasiDetailHistoryInterface.onGetFarmasiDetailSuccess(detailFarmasiModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        farmasiDetailHistoryInterface.onGetFarmasiDetailFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
