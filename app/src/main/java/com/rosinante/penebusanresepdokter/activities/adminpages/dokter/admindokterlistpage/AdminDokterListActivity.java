package com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admindokterlistpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDokterAdapter;
import com.rosinante.penebusanresepdokter.models.DokterModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminDokterListActivity extends AppCompatActivity implements AdminDokterListInterface {

    @BindView(R.id.recycler_dokter_list)
    RecyclerView recyclerDokterList;
    private AdminDokterListPresenter adminDokterListPresenter = new AdminDokterListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dokter_list);
        ButterKnife.bind(this);
        setTitle("Dokter List");
        adminDokterListPresenter.getAllDokterData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminDokterListActivity.this, MainAdminActivity.class));
        finish();
    }

    @Override
    public void onGetAllDokterListSuccess(DokterModel dokterModel) {
        recyclerDokterList.setLayoutManager(new LinearLayoutManager(AdminDokterListActivity.this));
        recyclerDokterList.setAdapter(new RecyclerDokterAdapter(AdminDokterListActivity.this, dokterModel.getHasil()));
    }

    @Override
    public void onGetAllDokterListFailed(String errorMessage) {
        Toast.makeText(AdminDokterListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
