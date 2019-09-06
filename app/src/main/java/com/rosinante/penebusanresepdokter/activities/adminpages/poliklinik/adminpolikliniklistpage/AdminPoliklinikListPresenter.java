package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminpolikliniklistpage;

import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminPoliklinikListPresenter {
    private AdminPoliklinikListInterface adminPoliklinikListInterface;

    public AdminPoliklinikListPresenter(AdminPoliklinikListInterface adminPoliklinikListInterface) {
        this.adminPoliklinikListInterface = adminPoliklinikListInterface;
    }

    public void getAllDataPoliklinik() {
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
                        adminPoliklinikListInterface.onGetAllPoliklinikDataSuccess(poliklinikModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminPoliklinikListInterface.onGetAllPoliklinikDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
