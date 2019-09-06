package com.rosinante.penebusanresepdokter.activities.pasienpages.editpasienprofile;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditPasienProfilePresenter {
    private EditPasienProfileInterface editPasienProfileInterface;

    public EditPasienProfilePresenter(EditPasienProfileInterface editPasienProfileInterface) {
        this.editPasienProfileInterface = editPasienProfileInterface;
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
                        editPasienProfileInterface.onSuccessEditPasienProfile(registerResponse);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        editPasienProfileInterface.onFailedEditPasienProfile(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}

