package com.rosinante.penebusanresepdokter.activities.kasirpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPembayaranAdapter;
import com.rosinante.penebusanresepdokter.models.PembayaranModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.lang.reflect.Array;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KasirPembayaranHistoryActivity extends AppCompatActivity {

    @BindView(R.id.recycler_history_kasir)
    RecyclerView recyclerHistoryKasir;
    @BindView(R.id.swipe_refresh_history_kasir)
    SwipeRefreshLayout swipeRefreshHistoryKasir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir_pembayaran_history);
        ButterKnife.bind(this);
        setTitle(R.string.pembayaran_history_text);
        getPembayaranHistory();
        swipeRefreshHistoryKasir.setOnRefreshListener(() -> {
            swipeRefreshHistoryKasir.setRefreshing(false);
            getPembayaranHistory();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(KasirPembayaranHistoryActivity.this, MainKasirActivity.class));
        finish();
    }

    private void getPembayaranHistory() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<PembayaranModel> pembayaranModelCall = apiService.getPembayaran();
        pembayaranModelCall.enqueue(new Callback<PembayaranModel>() {
            @Override
            public void onResponse(@NonNull Call<PembayaranModel> call, @NonNull Response<PembayaranModel> response) {
                recyclerHistoryKasir.setLayoutManager(new LinearLayoutManager(KasirPembayaranHistoryActivity.this));
                recyclerHistoryKasir.setAdapter(new RecyclerPembayaranAdapter(KasirPembayaranHistoryActivity.this, response.body().getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<PembayaranModel> call, @NonNull Throwable t) {
                Toast.makeText(KasirPembayaranHistoryActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
