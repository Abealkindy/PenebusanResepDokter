package com.rosinante.penebusanresepdokter.activities.dokterpages.dokteraddreseppage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DokterAddResepPresenter {
    private DokterAddResepInterface dokterAddResepInterface;

    public DokterAddResepPresenter(DokterAddResepInterface dokterAddResepInterface) {
        this.dokterAddResepInterface = dokterAddResepInterface;
    }

    public void insertResepData(int antrianID, int dokterID, int pasienID, String tanggalResep, String keteranganResep) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.addResep(
                antrianID,
                dokterID,
                pasienID,
                tanggalResep,
                keteranganResep
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        dokterAddResepInterface.onInsertResepDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dokterAddResepInterface.onInsertResepDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
