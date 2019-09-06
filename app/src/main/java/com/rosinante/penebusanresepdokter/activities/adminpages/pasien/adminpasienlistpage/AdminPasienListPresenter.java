package com.rosinante.penebusanresepdokter.activities.adminpages.pasien.adminpasienlistpage;

import com.rosinante.penebusanresepdokter.models.PasienModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminPasienListPresenter {
    private AdminPasienListInterface adminPasienListInterface;

    public AdminPasienListPresenter(AdminPasienListInterface adminPasienListInterface) {
        this.adminPasienListInterface = adminPasienListInterface;
    }

    public void getAllPasienData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getPasien()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PasienModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PasienModel pasienModel) {
                        adminPasienListInterface.onGetAllPasienDataSuccess(pasienModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminPasienListInterface.onGetAllPasienDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
