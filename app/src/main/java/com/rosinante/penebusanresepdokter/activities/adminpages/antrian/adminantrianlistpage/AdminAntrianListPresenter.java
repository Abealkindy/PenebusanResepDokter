package com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminantrianlistpage;

import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminAntrianListPresenter {
    private AdminAntrianListInterface adminAntrianListInterface;

    public AdminAntrianListPresenter(AdminAntrianListInterface adminAntrianListInterface) {
        this.adminAntrianListInterface = adminAntrianListInterface;
    }

    public void getAntrianData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getAntrian()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AntrianModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AntrianModel antrianModel) {
                        adminAntrianListInterface.onGetAntrianDataSuccess(antrianModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAntrianListInterface.onGetAntrianDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
