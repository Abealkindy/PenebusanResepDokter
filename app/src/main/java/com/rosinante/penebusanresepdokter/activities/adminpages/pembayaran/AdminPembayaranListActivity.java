package com.rosinante.penebusanresepdokter.activities.adminpages.pembayaran;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPembayaranAdapter;
import com.rosinante.penebusanresepdokter.models.PembayaranModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPembayaranListActivity extends AppCompatActivity implements AdminPembayaranListInterface {

    @BindView(R.id.recycler_pembayaran)
    RecyclerView recyclerPembayaran;
    private AdminPembayaranListPresenter adminPembayaranListPresenter = new AdminPembayaranListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pembayaran_list);
        ButterKnife.bind(this);
        setTitle("Pembayaran List");
        adminPembayaranListPresenter.getPembayaranData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPembayaranListActivity.this, MainAdminActivity.class));
        finish();
    }


    @Override
    public void onGetAllPembayaranDataSuccess(PembayaranModel pembayaranModel) {
        recyclerPembayaran.setLayoutManager(new LinearLayoutManager(AdminPembayaranListActivity.this));
        recyclerPembayaran.setAdapter(new RecyclerPembayaranAdapter(AdminPembayaranListActivity.this, Objects.requireNonNull(pembayaranModel).getHasil()));
    }

    @Override
    public void onGetAllPembayaranDataFailed(String errorMessage) {
        Toast.makeText(AdminPembayaranListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
