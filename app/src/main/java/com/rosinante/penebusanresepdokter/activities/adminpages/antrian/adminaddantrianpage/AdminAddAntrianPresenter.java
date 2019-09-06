package com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminaddantrianpage;

import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PasienModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.models.RegisterResponse;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminAddAntrianPresenter {
    private AdminAddAntrianInterface adminAddAntrianInterface;

    public AdminAddAntrianPresenter(AdminAddAntrianInterface adminAddAntrianInterface) {
        this.adminAddAntrianInterface = adminAddAntrianInterface;
    }


    public void getPasienData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        apiService.getPasien()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PasienModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PasienModel pasienModel) {
                        adminAddAntrianInterface.onGetPasienDataSuccess(pasienModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAddAntrianInterface.onGetPasienDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
                        adminAddAntrianInterface.onGetPoliklinikDataSuccess(poliklinikModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAddAntrianInterface.onGetPoliklinikDataFailed(e.getMessage());
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
                        adminAddAntrianInterface.onGetDokterDataByPoliIDSuccess(dokterDetailModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAddAntrianInterface.onGetDokterDataByPoliIDFailed(e.getMessage());
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
                        adminAddAntrianInterface.onInsertAntrianDataSuccess(registerResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        adminAddAntrianInterface.onInsertAntrianDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
