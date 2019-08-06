package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.ObatModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerObatAdapter extends RecyclerView.Adapter<RecyclerObatAdapter.ViewHolder> {

    private Context context;
    private List<ObatModel.ObatModelData> obatModelData;

    public RecyclerObatAdapter(Context context, List<ObatModel.ObatModelData> obatModelData) {
        this.context = context;
        this.obatModelData = obatModelData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_obat, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.textViewObatName.setText(obatModelData.get(position).getNama_obat());
        viewHolder.textViewObatDetail.setText(convertMoney(obatModelData.get(position).getHarga_obat()));
    }

    @SuppressLint("DefaultLocale")
    private String convertMoney(Double money) {
        return String.format("Rp. %,.0f", money).replaceAll(",", ".") + ",00";
    }

    @Override
    public int getItemCount() {
        return obatModelData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_obat_name)
        TextView textViewObatName;
        @BindView(R.id.text_view_obat_detail)
        TextView textViewObatDetail;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
