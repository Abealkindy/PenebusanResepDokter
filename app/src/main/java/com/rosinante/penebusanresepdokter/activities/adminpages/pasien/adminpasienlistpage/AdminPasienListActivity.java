package com.rosinante.penebusanresepdokter.activities.adminpages.pasien.adminpasienlistpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienAdapter;
import com.rosinante.penebusanresepdokter.models.PasienModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminPasienListActivity extends AppCompatActivity implements AdminPasienListInterface {

    @BindView(R.id.recycler_pasien_list)
    RecyclerView recyclerPasienList;
    private AdminPasienListPresenter adminPasienListPresenter = new AdminPasienListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pasien_list);
        ButterKnife.bind(this);
        setTitle("Pasien List");
        adminPasienListPresenter.getAllPasienData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPasienListActivity.this, MainAdminActivity.class));
        finish();
    }

    @Override
    public void onGetAllPasienDataSuccess(PasienModel pasienModel) {
        recyclerPasienList.setLayoutManager(new LinearLayoutManager(AdminPasienListActivity.this));
        recyclerPasienList.setAdapter(new RecyclerPasienAdapter(AdminPasienListActivity.this, Objects.requireNonNull(pasienModel).getHasil()));
    }

    @Override
    public void onGetAllPasienDataFailed(String errorMessage) {
        Toast.makeText(AdminPasienListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
