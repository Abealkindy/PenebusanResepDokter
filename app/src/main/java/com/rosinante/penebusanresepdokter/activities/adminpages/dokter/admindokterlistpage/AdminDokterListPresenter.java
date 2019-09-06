package com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admindokterlistpage;

import com.rosinante.penebusanresepdokter.models.DokterModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminDokterListPresenter {
    private AdminDokterListInterface adminDokterListInterface;

    public AdminDokterListPresenter(AdminDokterListInterface adminDokterListInterface) {
        this.adminDokterListInterface = adminDokterListInterface;
    }

    public void getAllDokterData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getDokter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DokterModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DokterModel dokterModel) {
                        adminDokterListInterface.onGetAllDokterListSuccess(dokterModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminDokterListInterface.onGetAllDokterListFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
