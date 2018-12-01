package com.rosinante.penebusanresepdokter.activities.adminpages.antrian;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienQueueAdapter;
import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAntrianListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.fab_add_antrian)
    FloatingActionButton fabAddAntrian;
    @BindView(R.id.recycler_antrian_user)
    RecyclerView recyclerAntrianUser;
    List<AntrianModel.AntrianModelData> antrianModelData;
    @BindView(R.id.swipe_refresh_antrian_admin)
    SwipeRefreshLayout swipeRefreshAntrianAdmin;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_antrian_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getAntrianData();
        toolbarText.setText("Antrian List");
        swipeRefreshAntrianAdmin.setOnRefreshListener(() -> {
            swipeRefreshAntrianAdmin.setRefreshing(false);
            getAntrianData();
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminAntrianListActivity.this, MainAdminActivity.class));
        finish();
    }

    private void getAntrianData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<AntrianModel> antrianModelCall = apiService.getAntrian();
        antrianModelCall.enqueue(new Callback<AntrianModel>() {
            @Override
            public void onResponse(@NonNull Call<AntrianModel> call, @NonNull Response<AntrianModel> response) {
                antrianModelData = Objects.requireNonNull(response.body()).getHasil();
                recyclerAntrianUser.setLayoutManager(new LinearLayoutManager(AdminAntrianListActivity.this));
                recyclerAntrianUser.setAdapter(new RecyclerPasienQueueAdapter(AdminAntrianListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<AntrianModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminAntrianListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.fab_add_antrian)
    public void onViewClicked() {
        startActivity(new Intent(AdminAntrianListActivity.this, AdminAddAntrianActivity.class));
    }
}
