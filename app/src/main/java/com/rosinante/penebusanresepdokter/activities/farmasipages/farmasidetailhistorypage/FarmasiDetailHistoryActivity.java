package com.rosinante.penebusanresepdokter.activities.farmasipages.farmasidetailhistorypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.farmasipages.mainfarmasipage.MainFarmasiActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerDetailFarmasiAdapter;
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FarmasiDetailHistoryActivity extends AppCompatActivity implements FarmasiDetailHistoryInterface {

    @BindView(R.id.recycler_history_farmasi)
    RecyclerView recyclerHistoryFarmasi;
    @BindView(R.id.swipe_refresh_history_farmasi)
    SwipeRefreshLayout swipeRefreshHistoryFarmasi;
    private FarmasiDetailHistoryPresenter farmasiDetailHistoryPresenter = new FarmasiDetailHistoryPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmasi_detail_history);
        ButterKnife.bind(this);
        setTitle(R.string.detail_resep_history_text);
        farmasiDetailHistoryPresenter.getDetailForPharmacy();
        swipeRefreshHistoryFarmasi.setOnRefreshListener(() -> {
            swipeRefreshHistoryFarmasi.setRefreshing(false);
            farmasiDetailHistoryPresenter.getDetailForPharmacy();
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(FarmasiDetailHistoryActivity.this, MainFarmasiActivity.class));
        finish();
    }

    @Override
    public void onGetFarmasiDetailSuccess(DetailFarmasiModel detailFarmasiModel) {
        recyclerHistoryFarmasi.setLayoutManager(new LinearLayoutManager(FarmasiDetailHistoryActivity.this));
        recyclerHistoryFarmasi.setAdapter(new RecyclerDetailFarmasiAdapter(FarmasiDetailHistoryActivity.this, Objects.requireNonNull(detailFarmasiModel).getHasil()));
    }

    @Override
    public void onGetFarmasiDetailFailed(String errorMessage) {
        Toast.makeText(FarmasiDetailHistoryActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
