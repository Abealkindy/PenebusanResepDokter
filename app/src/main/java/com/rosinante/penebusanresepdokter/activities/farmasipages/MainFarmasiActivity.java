package com.rosinante.penebusanresepdokter.activities.farmasipages;

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
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerResepFarmasiAdapter;
import com.rosinante.penebusanresepdokter.models.ResepModel;
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

public class MainFarmasiActivity extends AppCompatActivity {

    @BindView(R.id.recycler_resep_farmasi)
    RecyclerView recyclerResepFarmasi;
    @BindView(R.id.floating_menu_logout_farmasi)
    FloatingActionButton floatingMenuLogoutFarmasi;
    @BindView(R.id.floating_menu_history_farmasi)
    FloatingActionButton floatingMenuHistoryFarmasi;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.swipe_refresh_resep_farmasi)
    SwipeRefreshLayout swipeRefreshResepFarmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_farmasi);
        ButterKnife.bind(this);
        setTitle("Resep List");
        getResep();
        swipeRefreshResepFarmasi.setOnRefreshListener(() -> {
            swipeRefreshResepFarmasi.setRefreshing(false);
            getResep();
        });
    }

    private void getResep() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<ResepModel> resepModelCall = apiService.getActiveResep();
        resepModelCall.enqueue(new Callback<ResepModel>() {
            @Override
            public void onResponse(@NonNull Call<ResepModel> call, @NonNull Response<ResepModel> response) {
                recyclerResepFarmasi.setLayoutManager(new LinearLayoutManager(MainFarmasiActivity.this));
                recyclerResepFarmasi.setAdapter(new RecyclerResepFarmasiAdapter(MainFarmasiActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<ResepModel> call, @NonNull Throwable t) {
                Toast.makeText(MainFarmasiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.floating_menu_logout_farmasi, R.id.floating_menu_history_farmasi, R.id.material_design_android_floating_action_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_logout_farmasi:
                UserSession userSession = new UserSession(MainFarmasiActivity.this);
                userSession.logout();
                startActivity(new Intent(MainFarmasiActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.floating_menu_history_farmasi:
                startActivity(new Intent(MainFarmasiActivity.this, FarmasiDetailHistoryActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
        }
    }
}
