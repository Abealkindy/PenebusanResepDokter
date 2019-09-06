package com.rosinante.penebusanresepdokter.activities.adminpages.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDetailFarmasiAdapter;
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminDetailListActivity extends AppCompatActivity implements AdminDetailListInterface {

    @BindView(R.id.recycler_detail)
    RecyclerView recyclerDetail;
    private AdminDetailListPresenter adminDetailListPresenter = new AdminDetailListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_list);
        ButterKnife.bind(this);
        setTitle("Detail List");
        adminDetailListPresenter.getDetailData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminDetailListActivity.this, MainAdminActivity.class));
        finish();
    }


    @Override
    public void onGetDetailDataSuccess(DetailFarmasiModel detailFarmasiModel) {
        recyclerDetail.setLayoutManager(new LinearLayoutManager(AdminDetailListActivity.this));
        recyclerDetail.setAdapter(new RecyclerDetailFarmasiAdapter(AdminDetailListActivity.this, Objects.requireNonNull(detailFarmasiModel).getHasil()));
    }

    @Override
    public void onGetDetailDataFailed(String errorMessage) {
        Toast.makeText(AdminDetailListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
