package com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage;

import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {
    private LoginInterface loginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void loginPasien(String email_pasien, String password_pasien) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.request_login(email_pasien, password_pasien)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        loginInterface.onLoginPasienSuccess(loginModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginInterface.onLoginPasienFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
