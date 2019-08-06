package com.rosinante.penebusanresepdokter.activities.adminpages.printstruk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDetailStrukAdapter;
import com.rosinante.penebusanresepdokter.models.DetailStrukModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDetailStrukListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_detail_struk)
    RecyclerView recyclerDetailStruk;
    @BindView(R.id.swipe_refresh_detail_struk)
    SwipeRefreshLayout swipeRefreshDetailStruk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_struk_list);
        ButterKnife.bind(this);
        setTitle(R.string.detail_struk_list_title);
        getDetailStruk();
        swipeRefreshDetailStruk.setOnRefreshListener(() -> {
            swipeRefreshDetailStruk.setRefreshing(false);
            getDetailStruk();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminDetailStrukListActivity.this, MainAdminActivity.class));
        finish();
    }

    private void getDetailStruk() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<DetailStrukModel> detailStrukModelCall = apiService.getDataStruk();
        detailStrukModelCall.enqueue(new Callback<DetailStrukModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailStrukModel> call, @NonNull Response<DetailStrukModel> response) {
                recyclerDetailStruk.setLayoutManager(new LinearLayoutManager(AdminDetailStrukListActivity.this));
                recyclerDetailStruk.setAdapter(new RecyclerDetailStrukAdapter(AdminDetailStrukListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<DetailStrukModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminDetailStrukListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
