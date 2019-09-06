package com.rosinante.penebusanresepdokter.activities.pasienpages.pasienaddantrian;

import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PasienAddAntrianPresenter {
    private PasienAddAntrianInterface pasienAddAntrianInterface;

    public PasienAddAntrianPresenter(PasienAddAntrianInterface pasienAddAntrianInterface) {
        this.pasienAddAntrianInterface = pasienAddAntrianInterface;
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
                        pasienAddAntrianInterface.onGetPoliklinikDataSuccess(poliklinikModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pasienAddAntrianInterface.onGetPoliklinikDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDokterDataByPoliklinikID(int poliklinikID) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getDokterByPoliklinikID(poliklinikID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DokterDetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DokterDetailModel dokterDetailModel) {
                        pasienAddAntrianInterface.onGetDokterDataByPoliklinikIDSuccess(dokterDetailModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pasienAddAntrianInterface.onGetDokterDataByPoliklinikIDFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertAntrianData(String tanggalPendaftaran, int dokterID, int pasienID, int poliklinikID, String keteranganAntrian) {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.add_antrian(
                tanggalPendaftaran,
                keteranganAntrian,
                dokterID,
                pasienID,
                poliklinikID
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        pasienAddAntrianInterface.onInsertPasienAntrianDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pasienAddAntrianInterface.onInsertPasienAntrianDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
