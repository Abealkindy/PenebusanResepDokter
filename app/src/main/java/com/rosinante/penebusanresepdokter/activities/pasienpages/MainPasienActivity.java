package com.rosinante.penebusanresepdokter.activities.pasienpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.rosinante.penebusanresepdokter.activities.LoginActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerPasienQueueAdapter;
import com.rosinante.penebusanresepdokter.models.AntrianModel;
import com.rosinante.penebusanresepdokter.models.PasienDetailModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPasienActivity extends AppCompatActivity {


    @BindView(R.id.recycler_antrian_user)
    RecyclerView recyclerAntrianUser;
    @BindView(R.id.floating_menu_profile_edit)
    FloatingActionButton floatingMenuProfileEdit;
    @BindView(R.id.floating_menu_logout)
    FloatingActionButton floatingMenuLogout;
    @BindView(R.id.floating_menu_item_3)
    FloatingActionButton floatingMenuItem3;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;
    @BindView(R.id.swipe_refresh_antrian)
    SwipeRefreshLayout swipeRefreshAntrian;
    @BindView(R.id.linear_empty)
    LinearLayout linearEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        ButterKnife.bind(this);
        setTitle("Antrian List");
        getAntrianData();
        getPasienAntrianStatus();
        swipeRefreshAntrian.setOnRefreshListener(() -> {
            swipeRefreshAntrian.setRefreshing(false);
            getAntrianData();
            getPasienAntrianStatus();
        });
    }

    private void intentPasienData() {
        UserSession userSession = new UserSession(MainPasienActivity.this);
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<PasienDetailModel> pasienDetailModelCall = apiService.getPasienByID(Integer.parseInt(userSession.getIdUser()));
        pasienDetailModelCall.enqueue(new Callback<PasienDetailModel>() {
            @Override
            public void onResponse(@NonNull Call<PasienDetailModel> call, @NonNull Response<PasienDetailModel> response) {
                Intent intent = new Intent(MainPasienActivity.this, EditPasienProfileActivity.class);
                intent.putExtra("pasien_id_pasien", Objects.requireNonNull(response.body()).getHasil().get(0).getId_user());
                intent.putExtra("pasien_address_pasien", response.body().getHasil().get(0).getPasien_address());
                intent.putExtra("pasien_age_pasien", response.body().getHasil().get(0).getPasien_age());
                intent.putExtra("pasien_email_pasien", response.body().getHasil().get(0).getPasien_email());
                intent.putExtra("pasien_name_pasien", response.body().getHasil().get(0).getPasien_name());
                intent.putExtra("pasien_gender_pasien", response.body().getHasil().get(0).getPasien_gender());
                intent.putExtra("user_password_pasien", response.body().getHasil().get(0).getUser_password());
                intent.putExtra("username_pasien", response.body().getHasil().get(0).getUsername());
                intent.putExtra("pasien_phone_pasien", response.body().getHasil().get(0).getPasien_phone());
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<PasienDetailModel> call, @NonNull Throwable t) {
                Toast.makeText(MainPasienActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPasienAntrianStatus() {
        UserSession userSession = new UserSession(MainPasienActivity.this);
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<PasienDetailModel> pasienDetailModelCall = apiService.getPasienByID(Integer.parseInt(userSession.getIdUser()));
        pasienDetailModelCall.enqueue(new Callback<PasienDetailModel>() {
            @Override
            public void onResponse(@NonNull Call<PasienDetailModel> call, @NonNull Response<PasienDetailModel> response) {
                if (Objects.requireNonNull(response.body()).getHasil().get(0).getStatus_antrian() == 0) {
                    Toast.makeText(MainPasienActivity.this, "Anda belum mengantri, silahkan mengantri", Toast.LENGTH_SHORT).show();
                    floatingMenuItem3.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainPasienActivity.this, "Anda sudah mengantri", Toast.LENGTH_SHORT).show();
                    floatingMenuItem3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PasienDetailModel> call, @NonNull Throwable t) {
                Toast.makeText(MainPasienActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAntrianData() {
        ApiService apiService = RetrofitConfig.getInitRetrofit();
        Call<AntrianModel> antrianModelCall = apiService.getAntrian();
        antrianModelCall.enqueue(new Callback<AntrianModel>() {
            @Override
            public void onResponse(@NonNull Call<AntrianModel> call, @NonNull Response<AntrianModel> response) {
                if (Objects.requireNonNull(response.body()).getHasil().isEmpty()) {
                    linearEmpty.setVisibility(View.VISIBLE);
                    recyclerAntrianUser.setVisibility(View.GONE);
                } else {
                    recyclerAntrianUser.setVisibility(View.VISIBLE);
                    linearEmpty.setVisibility(View.GONE);
                    recyclerAntrianUser.setLayoutManager(new LinearLayoutManager(MainPasienActivity.this));
                    recyclerAntrianUser.setAdapter(new RecyclerPasienQueueAdapter(MainPasienActivity.this, Objects.requireNonNull(response.body()).getHasil()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AntrianModel> call, @NonNull Throwable t) {
                Toast.makeText(MainPasienActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.floating_menu_profile_edit, R.id.floating_menu_logout, R.id.floating_menu_item_3, R.id.material_design_android_floating_action_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floating_menu_profile_edit:
                intentPasienData();
                break;
            case R.id.floating_menu_logout:
                UserSession userSession = new UserSession(MainPasienActivity.this);
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
        }
    }
}
