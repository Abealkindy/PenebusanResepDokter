package com.rosinante.penebusanresepdokter.activities.adminpages.pasien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienAdapter;
import com.rosinante.penebusanresepdokter.models.PasienModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPasienListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_pasien_list)
    RecyclerView recyclerPasienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pasien_list);
        ButterKnife.bind(this);
        setTitle("Pasien List");
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<PasienModel> pasienModelCall = apiService.getPasien();
        pasienModelCall.enqueue(new Callback<PasienModel>() {
            @Override
            public void onResponse(@NonNull Call<PasienModel> call, @NonNull Response<PasienModel> response) {
                recyclerPasienList.setLayoutManager(new LinearLayoutManager(AdminPasienListActivity.this));
                recyclerPasienList.setAdapter(new RecyclerPasienAdapter(AdminPasienListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<PasienModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminPasienListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPasienListActivity.this, MainAdminActivity.class));
        finish();
    }
}
