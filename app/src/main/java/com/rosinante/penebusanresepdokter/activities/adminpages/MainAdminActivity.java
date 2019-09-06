package com.rosinante.penebusanresepdokter.activities.adminpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage.LoginActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.antrian.adminantrianlistpage.AdminAntrianListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.detail.AdminDetailListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.dokter.admindokterlistpage.AdminDokterListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.obat.adminobatlistpage.AdminObatListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.pasien.adminpasienlistpage.AdminPasienListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.pembayaran.AdminPembayaranListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.poliklinik.adminpolikliniklistpage.AdminPoliklinikListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.admindetailstruklistpage.AdminDetailStrukListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.resep.AdminResepListActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.user.adminregisteruserpage.AdminRegisterActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.user.adminuserlistpage.AdminUserListActivity;
import com.rosinante.penebusanresepdokter.utils.UserSession;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainAdminActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.card_pendaftaran)
    CardView cardPendaftaran;
    @BindView(R.id.card_pembayaran)
    CardView cardPembayaran;
    @BindView(R.id.card_resep)
    CardView cardResep;
    @BindView(R.id.card_detail)
    CardView cardDetail;
    @BindView(R.id.card_obat)
    CardView cardObat;
    @BindView(R.id.card_poliklinik)
    CardView cardPoliklinik;
    @BindView(R.id.card_pasien)
    CardView cardPasien;
    @BindView(R.id.card_doctor)
    CardView cardDoctor;
    @BindView(R.id.card_user)
    CardView cardUser;
    @BindView(R.id.card_print)
    CardView cardPrint;
    @BindView(R.id.floating_menu_add_user)
    FloatingActionButton floatingMenuAddUser;
    @BindView(R.id.floating_menu_logout)
    FloatingActionButton floatingMenuLogout;
    @BindView(R.id.material_design_android_floating_action_menu)
    FloatingActionMenu materialDesignAndroidFloatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick({R.id.card_pendaftaran, R.id.card_pembayaran, R.id.card_resep, R.id.card_detail, R.id.card_obat, R.id.card_poliklinik, R.id.card_pasien, R.id.card_doctor, R.id.floating_menu_add_user, R.id.floating_menu_logout, R.id.material_design_android_floating_action_menu, R.id.card_user, R.id.card_print})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_pendaftaran:
                startActivity(new Intent(MainAdminActivity.this, AdminAntrianListActivity.class));
                finish();
                break;
            case R.id.card_pembayaran:
                startActivity(new Intent(MainAdminActivity.this, AdminPembayaranListActivity.class));
                finish();
                break;
            case R.id.card_resep:
                startActivity(new Intent(MainAdminActivity.this, AdminResepListActivity.class));
                finish();
                break;
            case R.id.card_detail:
                startActivity(new Intent(MainAdminActivity.this, AdminDetailListActivity.class));
                finish();
                break;
            case R.id.card_obat:
                startActivity(new Intent(MainAdminActivity.this, AdminObatListActivity.class));
                finish();
                break;
            case R.id.card_poliklinik:
                startActivity(new Intent(MainAdminActivity.this, AdminPoliklinikListActivity.class));
                finish();
                break;
            case R.id.card_pasien:
                startActivity(new Intent(MainAdminActivity.this, AdminPasienListActivity.class));
                finish();
                break;
            case R.id.card_doctor:
                startActivity(new Intent(MainAdminActivity.this, AdminDokterListActivity.class));
                finish();
                break;
            case R.id.card_user:
                startActivity(new Intent(MainAdminActivity.this, AdminUserListActivity.class));
                finish();
                break;
            case R.id.card_print:
                startActivity(new Intent(MainAdminActivity.this, AdminDetailStrukListActivity.class));
                finish();
                break;
            case R.id.floating_menu_add_user:
                startActivity(new Intent(MainAdminActivity.this, AdminRegisterActivity.class));
                finish();
                break;
            case R.id.floating_menu_logout:
                UserSession userSession = new UserSession(MainAdminActivity.this);
                userSession.logout();
                startActivity(new Intent(MainAdminActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.material_design_android_floating_action_menu:
                break;
        }
    }
}
