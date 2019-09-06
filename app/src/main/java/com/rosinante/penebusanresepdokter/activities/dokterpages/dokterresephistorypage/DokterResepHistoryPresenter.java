package com.rosinante.penebusanresepdokter.activities.dokterpages.dokterresephistorypage;

import com.rosinante.penebusanresepdokter.models.ResepDokterModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DokterResepHistoryPresenter {
    private DokterResepHistoryInterface dokterResepHistoryInterface;

    public DokterResepHistoryPresenter(DokterResepHistoryInterface dokterResepHistoryInterface) {
        this.dokterResepHistoryInterface = dokterResepHistoryInterface;
    }

    public void getResepList(String userID) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getResepByDokterID(Integer.parseInt(userID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResepDokterModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResepDokterModel resepDokterModel) {
                        dokterResepHistoryInterface.onGetAllResepSuccess(resepDokterModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dokterResepHistoryInterface.onGetAllResepFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
