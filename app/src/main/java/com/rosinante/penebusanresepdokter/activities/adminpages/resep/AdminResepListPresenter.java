package com.rosinante.penebusanresepdokter.activities.adminpages.resep;

import com.rosinante.penebusanresepdokter.models.ResepModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminResepListPresenter {
    private AdminResepListInterface adminResepListInterface;

    public AdminResepListPresenter(AdminResepListInterface adminResepListInterface) {
        this.adminResepListInterface = adminResepListInterface;
    }

    public void getResepData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getResep()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResepModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResepModel resepModel) {
                        adminResepListInterface.onGetAllResepDataSuccess(resepModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminResepListInterface.onGetAllResepDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
