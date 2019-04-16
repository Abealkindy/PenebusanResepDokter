package com.rosinante.penebusanresepdokter.activities.farmasipages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDetailFarmasiAdapter;
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmasiDetailHistoryActivity extends AppCompatActivity {

    @BindView(R.id.recycler_history_farmasi)
    RecyclerView recyclerHistoryFarmasi;
    @BindView(R.id.swipe_refresh_history_farmasi)
    SwipeRefreshLayout swipeRefreshHistoryFarmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmasi_detail_history);
        ButterKnife.bind(this);
        setTitle(R.string.detail_resep_history_text);
        getDetailForPharmacy();
        swipeRefreshHistoryFarmasi.setOnRefreshListener(() -> {
            swipeRefreshHistoryFarmasi.setRefreshing(false);
            getDetailForPharmacy();
        });
    }

    private void getDetailForPharmacy() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<DetailFarmasiModel> detailFarmasiModelCall = apiService.getDetailForPharmacy();
        detailFarmasiModelCall.enqueue(new Callback<DetailFarmasiModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailFarmasiModel> call, @NonNull Response<DetailFarmasiModel> response) {
                recyclerHistoryFarmasi.setLayoutManager(new LinearLayoutManager(FarmasiDetailHistoryActivity.this));
                recyclerHistoryFarmasi.setAdapter(new RecyclerDetailFarmasiAdapter(FarmasiDetailHistoryActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<DetailFarmasiModel> call, @NonNull Throwable t) {
                Toast.makeText(FarmasiDetailHistoryActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FarmasiDetailHistoryActivity.this, MainFarmasiActivity.class));
        finish();
    }
}
