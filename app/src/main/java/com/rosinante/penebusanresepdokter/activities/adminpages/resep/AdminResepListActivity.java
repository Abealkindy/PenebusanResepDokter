package com.rosinante.penebusanresepdokter.activities.adminpages.resep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerResepAdapter;
import com.rosinante.penebusanresepdokter.models.ResepModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminResepListActivity extends AppCompatActivity implements AdminResepListInterface {

    @BindView(R.id.recycler_resep_list)
    RecyclerView recyclerResepList;
    private AdminResepListPresenter adminResepListPresenter = new AdminResepListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_resep_list);
        ButterKnife.bind(this);
        setTitle("Resep List");
        adminResepListPresenter.getResepData();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminResepListActivity.this, MainAdminActivity.class));
        finish();
    }

    @Override
    public void onGetAllResepDataSuccess(ResepModel resepModel) {
        recyclerResepList.setLayoutManager(new LinearLayoutManager(AdminResepListActivity.this));
        recyclerResepList.setAdapter(new RecyclerResepAdapter(AdminResepListActivity.this, Objects.requireNonNull(resepModel).getHasil()));
    }

    @Override
    public void onGetAllResepDataFailed(String errorMessage) {
        Toast.makeText(AdminResepListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
