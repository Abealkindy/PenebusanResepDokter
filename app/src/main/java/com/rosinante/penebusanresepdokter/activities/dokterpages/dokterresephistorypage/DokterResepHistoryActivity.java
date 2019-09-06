package com.rosinante.penebusanresepdokter.activities.dokterpages.dokterresephistorypage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage.MainDokterActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerResepDokterAdapter;
import com.rosinante.penebusanresepdokter.models.ResepDokterModel;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DokterResepHistoryActivity extends AppCompatActivity implements DokterResepHistoryInterface {

    @BindView(R.id.recycler_history_dokter)
    RecyclerView recyclerHistoryDokter;
    @BindView(R.id.swipe_refresh_history_dokter)
    SwipeRefreshLayout swipeRefreshHistoryDokter;

    UserSession userSession = new UserSession(DokterResepHistoryActivity.this);
    private DokterResepHistoryPresenter dokterResepHistoryPresenter = new DokterResepHistoryPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_resep_history);
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.resep_history_title));
        dokterResepHistoryPresenter.getResepList(userSession.getIdUser());
        swipeRefreshHistoryDokter.setOnRefreshListener(() -> {
            swipeRefreshHistoryDokter.setRefreshing(false);
            dokterResepHistoryPresenter.getResepList(userSession.getIdUser());
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DokterResepHistoryActivity.this, MainDokterActivity.class));
        finish();
    }

    @Override
    public void onGetAllResepSuccess(ResepDokterModel resepDokterModel) {
        recyclerHistoryDokter.setLayoutManager(new LinearLayoutManager(DokterResepHistoryActivity.this));
        recyclerHistoryDokter.setAdapter(new RecyclerResepDokterAdapter(DokterResepHistoryActivity.this, Objects.requireNonNull(resepDokterModel).getHasil()));
    }

    @Override
    public void onGetAllResepFailed(String errorMessage) {
        Toast.makeText(DokterResepHistoryActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
