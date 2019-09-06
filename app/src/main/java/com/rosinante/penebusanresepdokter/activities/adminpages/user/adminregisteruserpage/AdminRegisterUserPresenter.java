package com.rosinante.penebusanresepdokter.activities.adminpages.user.adminregisteruserpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminRegisterUserPresenter {
    private AdminRegisterUserInterface adminRegisterUserInterface;

    public AdminRegisterUserPresenter(AdminRegisterUserInterface adminRegisterUserInterface) {
        this.adminRegisterUserInterface = adminRegisterUserInterface;
    }

    public void registerUser(String username, String confirmpassword, String role) {
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
                        adminRegisterUserInterface.onInsertUserRegistrationDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminRegisterUserInterface.onInsertUserRegistrationDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
