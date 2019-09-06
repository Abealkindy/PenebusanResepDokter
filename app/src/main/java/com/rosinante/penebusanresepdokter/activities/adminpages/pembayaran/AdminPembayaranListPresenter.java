package com.rosinante.penebusanresepdokter.activities.adminpages.pembayaran;

import com.rosinante.penebusanresepdokter.models.PembayaranModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminPembayaranListPresenter {
    private AdminPembayaranListInterface adminPembayaranListInterface;

    public AdminPembayaranListPresenter(AdminPembayaranListInterface adminPembayaranListInterface) {
        this.adminPembayaranListInterface = adminPembayaranListInterface;
    }

    public void getPembayaranData() {
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
                        adminPembayaranListInterface.onGetAllPembayaranDataSuccess(pembayaranModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminPembayaranListInterface.onGetAllPembayaranDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
