package com.rosinante.penebusanresepdokter.activities.adminpages.user.adminuserlistpage;

import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminUserListPresenter {
    private AdminUserListInterface adminUserListInterface;

    public AdminUserListPresenter(AdminUserListInterface adminUserListInterface) {
        this.adminUserListInterface = adminUserListInterface;
    }

    public void getAllUserData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        adminUserListInterface.onGetAllUserDataSuccess(loginModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminUserListInterface.onGetAllUserDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
