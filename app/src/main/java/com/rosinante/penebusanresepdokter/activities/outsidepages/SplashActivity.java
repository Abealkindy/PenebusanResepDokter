package com.rosinante.penebusanresepdokter.activities.outsidepages;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.activities.dokterpages.maindokterpage.MainDokterActivity;
import com.rosinante.penebusanresepdokter.activities.farmasipages.mainfarmasipage.MainFarmasiActivity;
import com.rosinante.penebusanresepdokter.activities.kasirpages.mainkasirpage.MainKasirActivity;
import com.rosinante.penebusanresepdokter.activities.pasienpages.mainpasien.MainPasienActivity;
import com.rosinante.penebusanresepdokter.utils.UserSession;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final UserSession userSession = new UserSession(SplashActivity.this);
        new Handler().postDelayed(() -> {
            if (userSession.isLogin()) {
                switch (userSession.getRole()) {
                    case "Dokter":
                        Toast.makeText(SplashActivity.this, "Dokter!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this, MainDokterActivity.class));
                        finish();
                        break;
                    case "Pasien":
                        Toast.makeText(SplashActivity.this, "Pasien!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this, MainPasienActivity.class));
                        finish();
                        break;
                    case "Admin":
                        Toast.makeText(SplashActivity.this, "Admin!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this, MainAdminActivity.class));
                        finish();
                        break;
                    case "Farmasi":
                        Toast.makeText(SplashActivity.this, "Farmasi!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this, MainFarmasiActivity.class));
                        finish();
                        break;
                    case "Kasir":
                        Toast.makeText(SplashActivity.this, "Kasir!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SplashActivity.this, MainKasirActivity.class));
                        finish();
                        break;
                }
            } else {
                startActivity(new Intent(SplashActivity.this, IntroSliderActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

}
