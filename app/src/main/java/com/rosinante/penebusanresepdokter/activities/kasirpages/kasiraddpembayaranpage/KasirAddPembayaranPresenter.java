package com.rosinante.penebusanresepdokter.activities.kasirpages.kasiraddpembayaranpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KasirAddPembayaranPresenter {
    private KasirAddPembayaranInterface kasirAddPembayaranInterface;

    public KasirAddPembayaranPresenter(KasirAddPembayaranInterface kasirAddPembayaranInterface) {
        this.kasirAddPembayaranInterface = kasirAddPembayaranInterface;
    }

    public void addPembayaran(int pasienID, int antrianID, int resepID, String resepDate, double uangBayar, double kembalian) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.addPembayaran(
                pasienID,
                antrianID,
                resepID,
                resepDate,
                uangBayar,
                kembalian
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        kasirAddPembayaranInterface.onAddPembayaranSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        kasirAddPembayaranInterface.onAddPembayaranFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
