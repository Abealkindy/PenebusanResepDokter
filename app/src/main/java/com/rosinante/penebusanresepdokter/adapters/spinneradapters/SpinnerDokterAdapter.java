package com.rosinante.penebusanresepdokter.adapters.spinneradapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.DokterDetailModel;
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;

import java.util.List;

public class SpinnerDokterAdapter extends ArrayAdapter<DokterDetailModel.DokterDetailData> {
    private Context context;
    private List<DokterDetailModel.DokterDetailData> poliklinikModel;

    public SpinnerDokterAdapter(@NonNull Context context, int textViewResourceId, List<DokterDetailModel.DokterDetailData> poliklinikModel) {
        super(context, textViewResourceId, poliklinikModel);
        this.context = context;
        this.poliklinikModel = poliklinikModel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_dokter_row, parent, false);
        TextView textPoliklinikName = rowView.findViewById(R.id.nama_dokter_text_view);
        textPoliklinikName.setText(poliklinikModel.get(position).getDokter_name());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_dokter_row, parent, false);
        TextView textPoliklinikName = rowView.findViewById(R.id.nama_dokter_text_view);
        textPoliklinikName.setText(poliklinikModel.get(position).getDokter_name());
        return rowView;
    }
}
