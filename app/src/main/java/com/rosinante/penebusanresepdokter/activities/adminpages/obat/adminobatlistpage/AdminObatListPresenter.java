package com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminobatlistpage;

import com.rosinante.penebusanresepdokter.models.ObatModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminObatListPresenter {
    private AdminObatListInterface adminObatListInterface;

    public AdminObatListPresenter(AdminObatListInterface adminObatListInterface) {
        this.adminObatListInterface = adminObatListInterface;
    }

    public void getObatData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getObat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ObatModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ObatModel obatModel) {
                        adminObatListInterface.onGetAllObatDataSuccess(obatModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminObatListInterface.onGetAllObatDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
