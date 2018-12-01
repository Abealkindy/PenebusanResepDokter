package com.rosinante.penebusanresepdokter.activities.kasirpages;

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
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerResepKasirAdapter;
import com.rosinante.penebusanresepdokter.models.ResepAllModel;
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

public class MainKasirActivity extends AppCompatActivity {

    @BindView(R.id.recycler_resep_kasir)
    RecyclerView recyclerResepKasir;
    @BindView(R.id.floating_menu_logout_kasir)
    FloatingActionButton floatingMenuLogoutKasir;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.swipe_refresh_resep_kasir)
    SwipeRefreshLayout swipeRefreshResepKasir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kasir);
        ButterKnife.bind(this);
        setTitle("Resep List");
        getResepAllData();
        swipeRefreshResepKasir.setOnRefreshListener(() -> {
            swipeRefreshResepKasir.setRefreshing(false);
            getResepAllData();
        });
    }

    private void getResepAllData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<ResepAllModel> resepAllModelCall = apiService.getResepAll();
        resepAllModelCall.enqueue(new Callback<ResepAllModel>() {
            @Override
            public void onResponse(@NonNull Call<ResepAllModel> call, @NonNull Response<ResepAllModel> response) {
                recyclerResepKasir.setLayoutManager(new LinearLayoutManager(MainKasirActivity.this));
                recyclerResepKasir.setAdapter(new RecyclerResepKasirAdapter(MainKasirActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<ResepAllModel> call, @NonNull Throwable t) {
                Toast.makeText(MainKasirActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.floating_menu_logout_kasir, R.id.material_design_android_floating_action_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_logout_kasir:
                UserSession userSession = new UserSession(MainKasirActivity.this);
                userSession.logout();
                startActivity(new Intent(MainKasirActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
        }
    }
}
