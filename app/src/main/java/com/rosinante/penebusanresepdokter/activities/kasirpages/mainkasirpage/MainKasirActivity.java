package com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage;

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
import com.rosinante.penebusanresepdokter.activities.kasirpages.kasirpembayaranhistorypage.KasirPembayaranHistoryActivity;
import com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage.LoginActivity;
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

public class MainKasirActivity extends AppCompatActivity implements MainKasirInterface {

    @BindView(R.id.recycler_resep_kasir)
    RecyclerView recyclerResepKasir;
    @BindView(R.id.floating_menu_logout_kasir)
    FloatingActionButton floatingMenuLogoutKasir;
    @BindView(R.id.floating_menu_history_kasir)
    FloatingActionButton floatingMenuHistoryKasir;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.swipe_refresh_resep_kasir)
    SwipeRefreshLayout swipeRefreshResepKasir;
    private MainKasirPresenter mainKasirPresenter = new MainKasirPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kasir);
        ButterKnife.bind(this);
        setTitle("Resep List");
        mainKasirPresenter.getResepAllData();
        swipeRefreshResepKasir.setOnRefreshListener(() -> {
            swipeRefreshResepKasir.setRefreshing(false);
            mainKasirPresenter.getResepAllData();
        });
    }


    @OnClick({R.id.floating_menu_logout_kasir, R.id.floating_menu_history_kasir, R.id.material_design_android_floating_action_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_logout_kasir:
                UserSession userSession = new UserSession(MainKasirActivity.this);
                userSession.logout();
                startActivity(new Intent(MainKasirActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.floating_menu_history_kasir:
                startActivity(new Intent(MainKasirActivity.this, KasirPembayaranHistoryActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
        }
    }

    @Override
    public void onGetKasirResepSuccess(ResepAllModel resepAllModel) {
        recyclerResepKasir.setLayoutManager(new LinearLayoutManager(MainKasirActivity.this));
        recyclerResepKasir.setAdapter(new RecyclerResepKasirAdapter(MainKasirActivity.this, resepAllModel.getHasil()));
    }

    @Override
    public void onGetKasirResepFailed(String errorMessage) {
        Toast.makeText(MainKasirActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
