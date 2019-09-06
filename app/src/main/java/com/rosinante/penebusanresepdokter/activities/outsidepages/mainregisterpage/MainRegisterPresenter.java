package com.rosinante.penebusanresepdokter.activities.outsidepages.mainregisterpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainRegisterPresenter {
    private MainRegisterInterface mainRegisterInterface;

    public MainRegisterPresenter(MainRegisterInterface mainRegisterInterface) {
        this.mainRegisterInterface = mainRegisterInterface;
    }

    public void registerPasien(String username, String confirmpassword, String role) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.request_register(username, confirmpassword, role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        mainRegisterInterface.onRegisterPasienSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainRegisterInterface.onRegisterPasienFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
