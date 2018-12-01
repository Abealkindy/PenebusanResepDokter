package com.rosinante.penebusanresepdokter.activities.adminpages.resep;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerResepAdapter;
import com.rosinante.penebusanresepdokter.models.ResepModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminResepListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_resep_list)
    RecyclerView recyclerResepList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_resep_list);
        ButterKnife.bind(this);
        setTitle("Resep List");
        getResepData();
    }

    private void getResepData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<ResepModel> resepModelCall = apiService.getResep();
        resepModelCall.enqueue(new Callback<ResepModel>() {
            @Override
            public void onResponse(@NonNull Call<ResepModel> call, @NonNull Response<ResepModel> response) {
                recyclerResepList.setLayoutManager(new LinearLayoutManager(AdminResepListActivity.this));
                recyclerResepList.setAdapter(new RecyclerResepAdapter(AdminResepListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<ResepModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminResepListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminResepListActivity.this, MainAdminActivity.class));
        finish();
    }
}
