package com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage.LoginActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.pasienaddantrian.PasienAddAntrianActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.pasienantrianhistory.PasienAntrianHistoryActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.editpasienprofile.EditPasienProfileActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienQueueAdapter;
import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.models.PasienDetailModel;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainPasienActivity extends AppCompatActivity implements MainPasienInterface {


    @BindView(R.id.recycler_antrian_user)
    RecyclerView recyclerAntrianUser;
    @BindView(R.id.floating_menu_profile_edit)
    FloatingActionButton floatingMenuProfileEdit;
    @BindView(R.id.floating_menu_logout)
    FloatingActionButton floatingMenuLogout;
    @BindView(R.id.floating_menu_item_3)
    FloatingActionButton floatingMenuItem3;
    @BindView(R.id.floating_menu_history_pasien)
    FloatingActionButton floatingMenuHistoryPasien;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.swipe_refresh_antrian)
    SwipeRefreshLayout swipeRefreshAntrian;
    @BindView(R.id.linear_empty)
    LinearLayout linearEmpty;
    private MainPasienPresenter mainPasienPresenter = new MainPasienPresenter(this);


    private UserSession userSession = new UserSession(MainPasienActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        ButterKnife.bind(this);
        setTitle("Antrian List");
        mainPasienPresenter.getAntrianData();
        mainPasienPresenter.getPasienAntrianStatus(userSession.getIdUser());
        swipeRefreshAntrian.setOnRefreshListener(() -> {
            swipeRefreshAntrian.setRefreshing(false);
            mainPasienPresenter.getAntrianData();
            mainPasienPresenter.getPasienAntrianStatus(userSession.getIdUser());
        });
    }


    @OnClick({R.id.floating_menu_profile_edit, R.id.floating_menu_logout, R.id.floating_menu_item_3, R.id.material_design_android_floating_action_menu, R.id.floating_menu_history_pasien})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_profile_edit:
                mainPasienPresenter.intentPasienData(userSession.getIdUser());
                break;
            case R.id.floating_menu_logout:
                userSession.logout();
                startActivity(new Intent(MainPasienActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.floating_menu_item_3:
                startActivity(new Intent(MainPasienActivity.this, PasienAddAntrianActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
            case R.id.floating_menu_history_pasien:
                startActivity(new Intent(MainPasienActivity.this, PasienAntrianHistoryActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onGetPasienAntrianStatusSuccess(PasienDetailModel pasienDetailModel) {
        if (pasienDetailModel.getHasil().get(0).getStatus_antrian() == 0) {
            Toast.makeText(MainPasienActivity.this, "Anda belum mengantri, silahkan mengantri", Toast.LENGTH_SHORT).show();
            floatingMenuItem3.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(MainPasienActivity.this, "Anda sudah mengantri", Toast.LENGTH_SHORT).show();
            floatingMenuItem3.setVisibility(View.GONE);
        }
    }

    @Override
    public void onGetPasienAntrianStatusFailed(String errorMessage) {
        Toast.makeText(MainPasienActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPasienDataForIntentSuccess(PasienDetailModel pasienDetailModel) {
        Intent intent = new Intent(MainPasienActivity.this, EditPasienProfileActivity.class);
        intent.putExtra("pasien_id_pasien", pasienDetailModel.getHasil().get(0).getId_user());
        intent.putExtra("pasien_address_pasien", pasienDetailModel.getHasil().get(0).getPasien_address());
        intent.putExtra("pasien_age_pasien", pasienDetailModel.getHasil().get(0).getPasien_age());
        intent.putExtra("pasien_email_pasien", pasienDetailModel.getHasil().get(0).getPasien_email());
        intent.putExtra("pasien_name_pasien", pasienDetailModel.getHasil().get(0).getPasien_name());
        intent.putExtra("pasien_gender_pasien", pasienDetailModel.getHasil().get(0).getPasien_gender());
        intent.putExtra("user_password_pasien", pasienDetailModel.getHasil().get(0).getUser_password());
        intent.putExtra("username_pasien", pasienDetailModel.getHasil().get(0).getUsername());
        intent.putExtra("pasien_phone_pasien", pasienDetailModel.getHasil().get(0).getPasien_phone());
        startActivity(intent);
    }

    @Override
    public void onGetPasienDataForIntentFailed(String errorMessage) {
        Toast.makeText(MainPasienActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAntrianDataSuccess(AntrianModel antrianModel) {
        if (antrianModel.getHasil().isEmpty()) {
            linearEmpty.setVisibility(View.VISIBLE);
            recyclerAntrianUser.setVisibility(View.GONE);
        } else {
            recyclerAntrianUser.setVisibility(View.VISIBLE);
            linearEmpty.setVisibility(View.GONE);
            recyclerAntrianUser.setLayoutManager(new LinearLayoutManager(MainPasienActivity.this));
            recyclerAntrianUser.setAdapter(new RecyclerPasienQueueAdapter(MainPasienActivity.this, antrianModel.getHasil()));
        }
    }

    @Override
    public void onGetAntrianDataFailed(String errorMessage) {
        Toast.makeText(MainPasienActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
