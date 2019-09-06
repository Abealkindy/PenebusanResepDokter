package com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.admindetailstruklistpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDetailStrukAdapter;
import com.rosinante.penebusanresepdokter.models.DetailStrukModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminDetailStrukListActivity extends AppCompatActivity implements AdminDetailStrukListInterface {

    @BindView(R.id.recycler_detail_struk)
    RecyclerView recyclerDetailStruk;
    @BindView(R.id.swipe_refresh_detail_struk)
    SwipeRefreshLayout swipeRefreshDetailStruk;
    private AdminDetailStrukListPresenter adminDetailStrukListPresenter = new AdminDetailStrukListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_struk_list);
        ButterKnife.bind(this);
        setTitle(R.string.detail_struk_list_title);
        adminDetailStrukListPresenter.getDetailStruk();
        swipeRefreshDetailStruk.setOnRefreshListener(() -> {
            swipeRefreshDetailStruk.setRefreshing(false);
            adminDetailStrukListPresenter.getDetailStruk();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminDetailStrukListActivity.this, MainAdminActivity.class));
        finish();
    }

    @Override
    public void onGetAllStrukDataSuccess(DetailStrukModel detailStrukModel) {
        recyclerDetailStruk.setLayoutManager(new LinearLayoutManager(AdminDetailStrukListActivity.this));
        recyclerDetailStruk.setAdapter(new RecyclerDetailStrukAdapter(AdminDetailStrukListActivity.this, Objects.requireNonNull(detailStrukModel).getHasil()));
    }

    @Override
    public void onGetAllStrukDataFailed(String errorMessage) {
        Toast.makeText(AdminDetailStrukListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
