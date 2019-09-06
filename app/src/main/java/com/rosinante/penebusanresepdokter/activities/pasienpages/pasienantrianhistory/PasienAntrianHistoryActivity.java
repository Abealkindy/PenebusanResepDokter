package com.rosinante.penebusanresepdokter.activities.pasienpages.pasienantrianhistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien.MainPasienActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienQueueHistoryAdapter;
import com.rosinante.penebusanresepdokter.models.AntrianPasienModel;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasienAntrianHistoryActivity extends AppCompatActivity implements PasienAntrianHistoryInterface {

    @BindView(R.id.recycler_history_pasien)
    RecyclerView recyclerHistoryPasien;
    @BindView(R.id.swipe_refresh_history_pasien)
    SwipeRefreshLayout swipeRefreshHistoryPasien;

    private UserSession userSession = new UserSession(PasienAntrianHistoryActivity.this);
    private PasienAntrianHistoryPresenter pasienAntrianHistoryPresenter = new PasienAntrianHistoryPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_antrian_history);
        ButterKnife.bind(this);
        setTitle("Antrian History");
        pasienAntrianHistoryPresenter.getAntrianList(userSession.getIdUser());
        swipeRefreshHistoryPasien.setOnRefreshListener(() -> {
            swipeRefreshHistoryPasien.setRefreshing(false);
            pasienAntrianHistoryPresenter.getAntrianList(userSession.getIdUser());
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PasienAntrianHistoryActivity.this, MainPasienActivity.class));
        finish();
    }

    @Override
    public void onGetAllAntrianDataSuccess(AntrianPasienModel antrianPasienModel) {
        recyclerHistoryPasien.setLayoutManager(new LinearLayoutManager(PasienAntrianHistoryActivity.this));
        recyclerHistoryPasien.setAdapter(new RecyclerPasienQueueHistoryAdapter(PasienAntrianHistoryActivity.this, antrianPasienModel.getHasil()));

    }

    @Override
    public void onGetAllAntrianDataFailed(String errorMessage) {
        Toast.makeText(PasienAntrianHistoryActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
