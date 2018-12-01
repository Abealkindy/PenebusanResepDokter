package com.rosinante.penebusanresepdokter.activities.adminpages.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDetailAdapter;
import com.rosinante.penebusanresepdokter.models.DetailModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDetailListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_detail)
    RecyclerView recyclerDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_list);
        ButterKnife.bind(this);
        setTitle("Detail List");
        getDetailData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminDetailListActivity.this, MainAdminActivity.class));
        finish();
    }

    private void getDetailData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<DetailModel> detailModelCall = apiService.getDetail();
        detailModelCall.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(@NonNull Call<DetailModel> call, @NonNull Response<DetailModel> response) {
                recyclerDetail.setLayoutManager(new LinearLayoutManager(AdminDetailListActivity.this));
                recyclerDetail.setAdapter(new RecyclerDetailAdapter(AdminDetailListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<DetailModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminDetailListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
