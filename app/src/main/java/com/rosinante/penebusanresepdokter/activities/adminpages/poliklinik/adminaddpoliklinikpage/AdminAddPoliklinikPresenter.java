package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminaddpoliklinikpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminAddPoliklinikPresenter {
    private AdminAddPoliklinikInterface adminAddPoliklinikInterface;

    public AdminAddPoliklinikPresenter(AdminAddPoliklinikInterface adminAddPoliklinikInterface) {
        this.adminAddPoliklinikInterface = adminAddPoliklinikInterface;
    }

    public void addPoliklinik(String namaPoliklinik) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.add_poliklinik(namaPoliklinik)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        adminAddPoliklinikInterface.onInsertPoliklinikDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAddPoliklinikInterface.onInsertPoliklinikDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
