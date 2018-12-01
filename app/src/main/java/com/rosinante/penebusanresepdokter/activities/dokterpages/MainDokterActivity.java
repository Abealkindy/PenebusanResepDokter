package com.rosinante.penebusanresepdokter.activities.dokterpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.LoginActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDokterQueueAdapter;
import com.rosinante.penebusanresepdokter.models.AntrianDokterModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDokterActivity extends AppCompatActivity {

    @BindView(R.id.recycler_antrian_dokter)
    RecyclerView recyclerAntrianDokter;
    @BindView(R.id.floating_menu_logout_dokter)
    FloatingActionButton floatingMenuLogoutDokter;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.swipe_refresh_antrian_dokter)
    SwipeRefreshLayout swipeRefreshAntrianDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dokter);
        ButterKnife.bind(this);
        setTitle("Antrian List");
        getAntrianList();
        swipeRefreshAntrianDokter.setOnRefreshListener(() -> {
            swipeRefreshAntrianDokter.setRefreshing(false);
            getAntrianList();
        });
    }

    private void getAntrianList() {
        UserSession userSession = new UserSession(MainDokterActivity.this);
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<AntrianDokterModel> antrianModelCall = apiService.getAntrianByDokterID(Integer.parseInt(userSession.getIdUser()));
        antrianModelCall.enqueue(new Callback<AntrianDokterModel>() {
            @Override
            public void onResponse(@NonNull Call<AntrianDokterModel> call, @NonNull Response<AntrianDokterModel> response) {
                recyclerAntrianDokter.setLayoutManager(new LinearLayoutManager(MainDokterActivity.this));
                recyclerAntrianDokter.setAdapter(new RecyclerDokterQueueAdapter(MainDokterActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<AntrianDokterModel> call, @NonNull Throwable t) {
                Toast.makeText(MainDokterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.floating_menu_logout_dokter, R.id.material_design_android_floating_action_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_logout_dokter:
                UserSession userSession = new UserSession(MainDokterActivity.this);
                userSession.logout();
                startActivity(new Intent(MainDokterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
        }
    }
}
