package com.rosinante.penebusanresepdokter.activities.kasirpages.kasirpembayaranhistorypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage.MainKasirActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPembayaranAdapter;
import com.rosinante.penebusanresepdokter.models.PembayaranModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KasirPembayaranHistoryActivity extends AppCompatActivity implements KasirPembayaranHistoryInterface {

    @BindView(R.id.recycler_history_kasir)
    RecyclerView recyclerHistoryKasir;
    @BindView(R.id.swipe_refresh_history_kasir)
    SwipeRefreshLayout swipeRefreshHistoryKasir;
    private KasirPembayaranHistoryPresenter kasirPembayaranHistoryPresenter = new KasirPembayaranHistoryPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir_pembayaran_history);
        ButterKnife.bind(this);
        setTitle(R.string.pembayaran_history_text);
        kasirPembayaranHistoryPresenter.getPembayaranHistory();
        swipeRefreshHistoryKasir.setOnRefreshListener(() -> {
            swipeRefreshHistoryKasir.setRefreshing(false);
            kasirPembayaranHistoryPresenter.getPembayaranHistory();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(KasirPembayaranHistoryActivity.this, MainKasirActivity.class));
        finish();
    }

    @Override
    public void onGetAllPembayaranDataSuccess(PembayaranModel pembayaranModel) {
        recyclerHistoryKasir.setLayoutManager(new LinearLayoutManager(KasirPembayaranHistoryActivity.this));
        recyclerHistoryKasir.setAdapter(new RecyclerPembayaranAdapter(KasirPembayaranHistoryActivity.this, pembayaranModel.getHasil()));
    }

    @Override
    public void onGetAllPembayaranDataFailed(String errorMessage) {
        Toast.makeText(KasirPembayaranHistoryActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
