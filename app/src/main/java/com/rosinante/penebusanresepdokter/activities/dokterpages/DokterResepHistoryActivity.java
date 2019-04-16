package com.rosinante.penebusanresepdokter.activities.dokterpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerResepDokterAdapter;
import com.rosinante.penebusanresepdokter.models.ResepDokterModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterResepHistoryActivity extends AppCompatActivity {

    @BindView(R.id.recycler_history_dokter)
    RecyclerView recyclerHistoryDokter;
    @BindView(R.id.swipe_refresh_history_dokter)
    SwipeRefreshLayout swipeRefreshHistoryDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_resep_history);
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.resep_history_title));
        getResepList();
        swipeRefreshHistoryDokter.setOnRefreshListener(() -> {
            swipeRefreshHistoryDokter.setRefreshing(false);
            getResepList();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DokterResepHistoryActivity.this, MainDokterActivity.class));
        finish();
    }

    private void getResepList() {
        UserSession userSession = new UserSession(DokterResepHistoryActivity.this);
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<ResepDokterModel> antrianModelCall = apiService.getResepByDokterID(Integer.parseInt(userSession.getIdUser()));
        antrianModelCall.enqueue(new Callback<ResepDokterModel>() {
            @Override
            public void onResponse(@NonNull Call<ResepDokterModel> call, @NonNull Response<ResepDokterModel> response) {
                recyclerHistoryDokter.setLayoutManager(new LinearLayoutManager(DokterResepHistoryActivity.this));
                recyclerHistoryDokter.setAdapter(new RecyclerResepDokterAdapter(DokterResepHistoryActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<ResepDokterModel> call, @NonNull Throwable t) {
                Toast.makeText(DokterResepHistoryActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
