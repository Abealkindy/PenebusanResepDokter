package com.rosinante.penebusanresepdokter.activities.kasirpages.kasirpembayaranhistorypage;

import com.rosinante.penebusanresepdokter.models.PembayaranModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class KasirPembayaranHistoryPresenter {
    private KasirPembayaranHistoryInterface kasirPembayaranHistoryInterface;

    public KasirPembayaranHistoryPresenter(KasirPembayaranHistoryInterface kasirPembayaranHistoryInterface) {
        this.kasirPembayaranHistoryInterface = kasirPembayaranHistoryInterface;
    }

    public void getPembayaranHistory() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getPembayaran()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PembayaranModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PembayaranModel pembayaranModel) {
                        kasirPembayaranHistoryInterface.onGetAllPembayaranDataSuccess(pembayaranModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        kasirPembayaranHistoryInterface.onGetAllPembayaranDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
