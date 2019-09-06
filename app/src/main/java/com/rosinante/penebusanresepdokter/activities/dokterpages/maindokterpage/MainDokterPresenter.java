package com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage;

import com.rosinante.penebusanresepdokter.models.AntrianDokterModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainDokterPresenter {
    private MainDokterInterface mainDokterInterface;

    public MainDokterPresenter(MainDokterInterface mainDokterInterface) {
        this.mainDokterInterface = mainDokterInterface;
    }

    public void getAntrianList(String userID) {

        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getAntrianByDokterID(Integer.parseInt(userID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AntrianDokterModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AntrianDokterModel antrianDokterModel) {
                        mainDokterInterface.onGetAntrianPasienDataSuccess(antrianDokterModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainDokterInterface.onGetAntrianPasienDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
