package com.rosinante.penebusanresepdokter.activities.adminpages.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerUserAdapter;
import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminUserListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_user_list)
    RecyclerView recyclerUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);
        ButterKnife.bind(this);
        setTitle("User List");
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<LoginModel> loginModelCall = apiService.getUser();
        loginModelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                recyclerUserList.setLayoutManager(new LinearLayoutManager(AdminUserListActivity.this));
                recyclerUserList.setAdapter(new RecyclerUserAdapter(AdminUserListActivity.this, Objects.requireNonNull(response.body()).getHasil()));
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                Toast.makeText(AdminUserListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminUserListActivity.this, MainAdminActivity.class));
        finish();
    }
}
