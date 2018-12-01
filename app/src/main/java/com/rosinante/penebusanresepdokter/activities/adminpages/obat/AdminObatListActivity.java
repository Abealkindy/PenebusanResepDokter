package com.rosinante.penebusanresepdokter.activities.adminpages.obat;

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
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerObatAdapter;
import com.rosinante.penebusanresepdokter.models.ObatModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminObatListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_obat)
    RecyclerView recyclerObat;
    @BindView(R.id.fab_add_obat)
    FloatingActionButton fabAddObat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_obat_list);
        ButterKnife.bind(this);
        setTitle("Obat List");
        getObatData();
    }

    private void getObatData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<ObatModel> obatModelCall = apiService.getObat();
        obatModelCall.enqueue(new Callback<ObatModel>() {
            @Override
            public void onResponse(@NonNull Call<ObatModel> call, @NonNull Response<ObatModel> response) {
                recyclerObat.setLayoutManager(new LinearLayoutManager(AdminObatListActivity.this));
                recyclerObat.setAdapter(new RecyclerObatAdapter(AdminObatListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<ObatModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminObatListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminObatListActivity.this, MainAdminActivity.class));
        finish();
    }

    @OnClick(R.id.fab_add_obat)
    public void onViewClicked() {
        startActivity(new Intent(AdminObatListActivity.this, AdminAddObatActivity.class));
        finish();
    }
}
