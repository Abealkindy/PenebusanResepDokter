package com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPoliklinikAdapter;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPoliklinikListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_poliklinik_list)
    RecyclerView recyclerPoliklinikList;
    @BindView(R.id.fab_add_poliklinik)
    FloatingActionButton fabAddPoliklinik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_poliklinik_list);
        ButterKnife.bind(this);
        setTitle("Poliklinik List");
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<PoliklinikModel> poliklinikModelCall = apiService.getPoliklinik();
        poliklinikModelCall.enqueue(new Callback<PoliklinikModel>() {
            @Override
            public void onResponse(@NonNull Call<PoliklinikModel> call, @NonNull Response<PoliklinikModel> response) {
                assert response.body() != null;
                recyclerPoliklinikList.setLayoutManager(new LinearLayoutManager(AdminPoliklinikListActivity.this));
                recyclerPoliklinikList.setAdapter(new RecyclerPoliklinikAdapter(AdminPoliklinikListActivity.this, Objects.requireNonNull(response.body()).getHasil()));

            }

            @Override
            public void onFailure(@NonNull Call<PoliklinikModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminPoliklinikListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPoliklinikListActivity.this, MainAdminActivity.class));
        finish();
    }

    @OnClick(R.id.fab_add_poliklinik)
    public void onViewClicked() {
        startActivity(new Intent(AdminPoliklinikListActivity.this, AdminAddPoliklinikActivity.class));
        finish();
    }
}
