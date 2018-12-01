package com.rosinante.penebusanresepdokter.activities.adminpages.dokter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDokterAdapter;
import com.rosinante.penebusanresepdokter.models.DokterModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDokterListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_dokter_list)
    RecyclerView recyclerDokterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dokter_list);
        ButterKnife.bind(this);
        setTitle("Dokter List");
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<DokterModel> dokterModelCall = apiService.getDokter();
        dokterModelCall.enqueue(new Callback<DokterModel>() {
            @Override
            public void onResponse(@NonNull Call<DokterModel> call, @NonNull Response<DokterModel> response) {
                assert response.body() != null;
                recyclerDokterList.setLayoutManager(new LinearLayoutManager(AdminDokterListActivity.this));
                recyclerDokterList.setAdapter(new RecyclerDokterAdapter(AdminDokterListActivity.this, response.body().getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<DokterModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminDokterListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminDokterListActivity.this, MainAdminActivity.class));
        finish();
    }
}
