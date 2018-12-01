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
import com.rosinante.penebusanresepdokter.models.ObatModel;

import java.util.List;

public class SpinnerObatAdapter extends ArrayAdapter<ObatModel.ObatModelData> {
    private Context context;
    private List<ObatModel.ObatModelData> obatModelData;

    public SpinnerObatAdapter(@NonNull Context context, int textViewResourceId, List<ObatModel.ObatModelData> obatModelData) {
        super(context, textViewResourceId, obatModelData);
        this.context = context;
        this.obatModelData = obatModelData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_obat_row, parent, false);
        TextView textPoliklinikName = rowView.findViewById(R.id.nama_obat_text_view);
        textPoliklinikName.setText(obatModelData.get(position).getNama_obat());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View rowView = LayoutInflater.from(context).inflate(R.layout.spinner_obat_row, parent, false);
        TextView textPoliklinikName = rowView.findViewById(R.id.nama_obat_text_view);
        textPoliklinikName.setText(obatModelData.get(position).getNama_obat());
        return rowView;
    }
}
