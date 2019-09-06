package com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage;

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
import com.rosinante.penebusanresepdokter.activities.dokterpages.dokterresephistorypage.DokterResepHistoryActivity;
import com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage.LoginActivity;
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

public class MainDokterActivity extends AppCompatActivity implements MainDokterInterface {

    @BindView(R.id.recycler_antrian_dokter)
    RecyclerView recyclerAntrianDokter;
    @BindView(R.id.floating_menu_logout_dokter)
    FloatingActionButton floatingMenuLogoutDokter;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.floating_menu_history_dokter)
    FloatingActionButton floatingMenuHistoryDokter;
    @BindView(R.id.swipe_refresh_antrian_dokter)
    SwipeRefreshLayout swipeRefreshAntrianDokter;
    UserSession userSession = new UserSession(MainDokterActivity.this);
    private MainDokterPresenter mainDokterPresenter = new MainDokterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dokter);
        ButterKnife.bind(this);
        setTitle("Antrian List");
        mainDokterPresenter.getAntrianList(userSession.getIdUser());
        swipeRefreshAntrianDokter.setOnRefreshListener(() -> {
            swipeRefreshAntrianDokter.setRefreshing(false);
            mainDokterPresenter.getAntrianList(userSession.getIdUser());
        });
    }

    @OnClick({R.id.floating_menu_logout_dokter, R.id.material_design_android_floating_action_menu, R.id.floating_menu_history_dokter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_logout_dokter:
                UserSession userSession = new UserSession(MainDokterActivity.this);
                userSession.logout();
                startActivity(new Intent(MainDokterActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.floating_menu_history_dokter:
                startActivity(new Intent(MainDokterActivity.this, DokterResepHistoryActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
        }
    }

    @Override
    public void onGetAntrianPasienDataSuccess(AntrianDokterModel antrianDokterModel) {
        recyclerAntrianDokter.setLayoutManager(new LinearLayoutManager(MainDokterActivity.this));
        recyclerAntrianDokter.setAdapter(new RecyclerDokterQueueAdapter(MainDokterActivity.this, Objects.requireNonNull(antrianDokterModel).getHasil()));
    }

    @Override
    public void onGetAntrianPasienDataFailed(String errorMessage) {
        Toast.makeText(MainDokterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
