package com.rosinante.penebusanresepdokter.activities.farmasipages.mainfarmasipage;

import com.rosinante.penebusanresepdokter.models.ResepModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainFarmasiPresenter {
    private MainFarmasiInterface mainFarmasiInterface;

    public MainFarmasiPresenter(MainFarmasiInterface mainFarmasiInterface) {
        this.mainFarmasiInterface = mainFarmasiInterface;
    }

    public void getResep() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getActiveResep()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResepModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResepModel resepModel) {
                        mainFarmasiInterface.onGetResepActiveForFarmasiSuccess(resepModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainFarmasiInterface.onGetResepActiveForFarmasiFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
