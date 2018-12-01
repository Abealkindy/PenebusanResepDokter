package com.rosinante.penebusanresepdokter.adapters.viewpageradapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

/*
 * Created by Rosinante24 on 01/08/18.
 */
public class ViewPagerSliderAdapter extends PagerAdapter {

    private Context context;
    private int[] layouts;

    public ViewPagerSliderAdapter(Context context, int[] layouts) {
        this.context = context;
        this.layouts = layouts;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = Objects.requireNonNull(inflater).inflate(layouts[position], container, false);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View v = (View) object;
        container.removeView(v);

    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
