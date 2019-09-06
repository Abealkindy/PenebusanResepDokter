package com.rosinante.penebusanresepdokter.activities.adminpages.pasien.admineditpasienprofile;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminEditPasienProfilePresenter {
    private AdminEditPasienProfileInterface adminEditPasienProfileInterface;

    public AdminEditPasienProfilePresenter(AdminEditPasienProfileInterface adminEditPasienProfileInterface) {
        this.adminEditPasienProfileInterface = adminEditPasienProfileInterface;
    }

    public void updatePasien(int idPasien, String pasienUsername, String pasienPassword, String pasienName, String pasienGender, String pasienAddress, String dateUmur, String pasienEmail, String pasienPhone) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.request_update_pasien(
                idPasien,
                idPasien,
                pasienUsername,
                pasienPassword,
                pasienName,
                pasienGender,
                pasienAddress,
                dateUmur,
                pasienEmail,
                pasienPhone
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        adminEditPasienProfileInterface.onUpdatePasienProfileDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminEditPasienProfileInterface.onUpdatePasienProfileDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deletePasien(int pasienID) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.delete_pasien(pasienID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        adminEditPasienProfileInterface.onDeletePasienProfileDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminEditPasienProfileInterface.onDeletePasienProfileDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
