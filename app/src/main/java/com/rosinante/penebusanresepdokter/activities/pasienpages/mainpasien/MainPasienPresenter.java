package com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien;


import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.models.PasienDetailModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPasienPresenter {
    private MainPasienInterface mainPasienInterface;

    public MainPasienPresenter(MainPasienInterface mainPasienInterface) {
        this.mainPasienInterface = mainPasienInterface;
    }

    public void intentPasienData(String userID) {

        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getPasienByID(Integer.parseInt(userID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PasienDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PasienDetailModel pasienDetailModel) {
                        mainPasienInterface.onGetPasienDataForIntentSuccess(pasienDetailModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainPasienInterface.onGetPasienDataForIntentFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPasienAntrianStatus(String userID) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getPasienByID(Integer.parseInt(userID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PasienDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PasienDetailModel pasienDetailModel) {
                        mainPasienInterface.onGetPasienAntrianStatusSuccess(pasienDetailModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainPasienInterface.onGetPasienAntrianStatusFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAntrianData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getAntrian()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AntrianModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AntrianModel antrianModel) {
                        mainPasienInterface.onGetAntrianDataSuccess(antrianModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainPasienInterface.onGetAntrianDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
