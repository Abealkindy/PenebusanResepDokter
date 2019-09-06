package com.rosinante.penebusanresepdokter.activities.outsidepages;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.activities.outsidepages.loginpage.LoginActivity;
import com.rosinante.penebusanresepdokter.utils.Pref;
import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.adapters.viewpageradapters.ViewPagerSliderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroSliderActivity extends AppCompatActivity {
    @BindView(R.id.viewPagerIntro)
    ViewPager viewPagerIntro;
    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    @BindView(R.id.buttonSkip)
    Button buttonSkip;
    @BindView(R.id.buttonNext)
    Button buttonNext;

    private Pref pref;
    TextView[] textDots;
    int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        ButterKnife.bind(this);
        pref = new Pref(this);
        if (!pref.isFirstTimeLaunch()) {
            lauchHome();
        }

        layouts = new int[]{
                R.layout.first_intro_slider,
                R.layout.second_intro_slider,
                R.layout.third_intro_slider
        };

        addBottomDots(0);

        ViewPagerSliderAdapter viewPagerAdapter = new ViewPagerSliderAdapter(getApplicationContext(), layouts);
        viewPagerIntro.setAdapter(viewPagerAdapter);
        viewPagerIntro.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
                if (position + 1 == layouts.length) {
                    buttonNext.setText(getResources().getString(R.string.start_button_text));
                    buttonSkip.setVisibility(View.GONE);
                } else {
                    buttonNext.setText(getResources().getString(R.string.next_button_text));
                    buttonSkip.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    private void addBottomDots(int i) {
        textDots = new TextView[layouts.length];
        int colorActive[] = getResources().getIntArray(R.array.array_dot_activ);
        int colorNonActive[] = getResources().getIntArray(R.array.array_dot_noactiv);
        layoutDots.removeAllViews();

        for (int a = 0; a < textDots.length; a++) {
            textDots[a] = new TextView(this);
            textDots[a].setText(Html.fromHtml("&#8226"));
            textDots[a].setTextSize(35);
            textDots[a].setTextColor(colorNonActive[i]);
            layoutDots.addView(textDots[a]);
        }

        if (textDots.length > 0) {
            textDots[i].setTextColor(colorActive[i]);
        }
    }

    private void lauchHome() {
        pref.setFirstLaunched(false);
        startActivity(new Intent(IntroSliderActivity.this, LoginActivity.class));
        finish();
    }

    @OnClick({R.id.buttonSkip, R.id.buttonNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSkip:
                lauchHome();
                break;
            case R.id.buttonNext:
                int current = getItem(+1);
                if (current < layouts.length) {
                    viewPagerIntro.setCurrentItem(current);
                } else {
                    lauchHome();
                }
                break;
        }
    }

    private int getItem(int i) {
        return viewPagerIntro.getCurrentItem() + i;
    }
}
