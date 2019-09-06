package com.rosinante.penebusanresepdokter.activities.farmasipages.adddetailfarmasipage;

import com.rosinante.penebusanresepdokter.models.ObatModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FarmasiAddDetailPresenter {
    private FarmasiAddDetailInterface farmasiAddDetailInterface;

    public FarmasiAddDetailPresenter(FarmasiAddDetailInterface farmasiAddDetailInterface) {
        this.farmasiAddDetailInterface = farmasiAddDetailInterface;
    }

    public void addDetail(int obatID, int resepID, double hargaObat, double totalHarga, String dosisObat) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.addDetail(
                obatID,
                resepID,
                hargaObat,
                totalHarga,
                dosisObat
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        farmasiAddDetailInterface.onAddDetailDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        farmasiAddDetailInterface.onAddDetailDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
                        farmasiAddDetailInterface.onGetObatDataSuccess(obatModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        farmasiAddDetailInterface.onGetObatDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
