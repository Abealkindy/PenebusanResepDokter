package com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admineditprofiledokterpage;

import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminEditDokterProfilePresenter {
    private AdminEditDokterProfileInterface adminEditDokterProfileInterface;

    public AdminEditDokterProfilePresenter(AdminEditDokterProfileInterface adminEditDokterProfileInterface) {
        this.adminEditDokterProfileInterface = adminEditDokterProfileInterface;
    }

    public void getPoliklinikData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getPoliklinik()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PoliklinikModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PoliklinikModel poliklinikModel) {
                        adminEditDokterProfileInterface.onGetPoliklinikDataSuccess(poliklinikModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminEditDokterProfileInterface.onGetPoliklinikDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateDokter(int dokterID, String usernameDokter, String passwordDokter, String addressDokter, String spesialisDokter, String emailDokter, String phoneDokter, String tarifDokter, int poliklinikIdDokter, String genderDokter, String namaDokter) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.request_update_dokter(
                dokterID,
                dokterID,
                usernameDokter,
                passwordDokter,
                namaDokter,
                genderDokter,
                addressDokter,
                spesialisDokter,
                emailDokter,
                phoneDokter,
                tarifDokter,
                poliklinikIdDokter
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        adminEditDokterProfileInterface.onUpdateDokterDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminEditDokterProfileInterface.onUpdateDokterDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteDokter(int dokterIDs) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.delete_dokter(dokterIDs)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        adminEditDokterProfileInterface.onDeleteDokterDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminEditDokterProfileInterface.onDeleteDokterDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
