package com.rosinante.penebusanresepdokter.activities.pasienpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienQueueHistoryAdapter;
import com.rosinante.penebusanresepdokter.models.AntrianPasienModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasienAntrianHistoryActivity extends AppCompatActivity {

    @BindView(R.id.recycler_history_pasien)
    RecyclerView recyclerHistoryPasien;
    @BindView(R.id.swipe_refresh_history_pasien)
    SwipeRefreshLayout swipeRefreshHistoryPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_antrian_history);
        ButterKnife.bind(this);
        setTitle("Antrian History");
        getAntrianList();
        swipeRefreshHistoryPasien.setOnRefreshListener(() -> {
            swipeRefreshHistoryPasien.setRefreshing(false);
            getAntrianList();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PasienAntrianHistoryActivity.this, MainPasienActivity.class));
        finish();
    }

    private void getAntrianList() {
        UserSession userSession = new UserSession(PasienAntrianHistoryActivity.this);
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<AntrianPasienModel> antrianModelCall = apiService.getAntrianByPasienID(Integer.parseInt(userSession.getIdUser()));
        antrianModelCall.enqueue(new Callback<AntrianPasienModel>() {
            @Override
            public void onResponse(@NonNull Call<AntrianPasienModel> call, @NonNull Response<AntrianPasienModel> response) {
                recyclerHistoryPasien.setLayoutManager(new LinearLayoutManager(PasienAntrianHistoryActivity.this));
                recyclerHistoryPasien.setAdapter(new RecyclerPasienQueueHistoryAdapter(PasienAntrianHistoryActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<AntrianPasienModel> call, @NonNull Throwable t) {
                Toast.makeText(PasienAntrianHistoryActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
