package com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminaddobatpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminAddObatPresenter {
    private AdminAddObatInterface adminAddObatInterface;

    public AdminAddObatPresenter(AdminAddObatInterface adminAddObatInterface) {
        this.adminAddObatInterface = adminAddObatInterface;
    }

    public void addObatData(String namaObat, String hargaObat) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.add_obat(
                namaObat,
                Double.parseDouble(hargaObat)
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        adminAddObatInterface.onGetAllObatDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAddObatInterface.onGetAllObatDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
