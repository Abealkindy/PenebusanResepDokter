package com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.admindetailstruklistpage;

import com.rosinante.penebusanresepdokter.models.DetailStrukModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminDetailStrukListPresenter {
    private AdminDetailStrukListInterface adminDetailStrukListInterface;

    public AdminDetailStrukListPresenter(AdminDetailStrukListInterface adminDetailStrukListInterface) {
        this.adminDetailStrukListInterface = adminDetailStrukListInterface;
    }


    public void getDetailStruk() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getDataStruk()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailStrukModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailStrukModel detailStrukModel) {
                        adminDetailStrukListInterface.onGetAllStrukDataSuccess(detailStrukModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminDetailStrukListInterface.onGetAllStrukDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
