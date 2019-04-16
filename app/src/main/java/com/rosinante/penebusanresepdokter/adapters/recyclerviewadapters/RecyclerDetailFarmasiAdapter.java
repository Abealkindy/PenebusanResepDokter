package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDetailFarmasiAdapter extends RecyclerView.Adapter<RecyclerDetailFarmasiAdapter.ViewHolder> {

    private Context context;
    private List<DetailFarmasiModel.DetailFarmasiData> detailFarmasiData;

    public RecyclerDetailFarmasiAdapter(Context context, List<DetailFarmasiModel.DetailFarmasiData> detailFarmasiData) {
        this.context = context;
        this.detailFarmasiData = detailFarmasiData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_resep, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textListResep.setText("ID Detail : " + detailFarmasiData.get(postion).getDetail_obat_id() + "\n");
        viewHolder.textListResep.append("Resep : " + detailFarmasiData.get(postion).getResep_text() + "\n");
        viewHolder.textListResep.append("Nama Obat : " + detailFarmasiData.get(postion).getNama_obat() + "\n");
        viewHolder.textListResep.append("Dosis Obat : " + detailFarmasiData.get(postion).getDosis_obat() + "\n");
        viewHolder.textListResep.append("Harga Obat/gr : " + detailFarmasiData.get(postion).getHarga_obat() + "\n");
        viewHolder.textListResep.append("Total Harga : " + detailFarmasiData.get(postion).getTotal_harga() + "\n");
        if (detailFarmasiData.get(postion).getResep_status() == 0) {
            viewHolder.textListResep.append("Status Resep : Belum Diproses");
        } else if (detailFarmasiData.get(postion).getResep_status() == 1) {
            viewHolder.textListResep.append("Status Resep : Sudah Diproses");
        } else if (detailFarmasiData.get(postion).getResep_status() == 2) {
            viewHolder.textListResep.append("Status Resep : Sudah Dibayar");
        }

    }

    @Override
    public int getItemCount() {
        return detailFarmasiData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_resep)
        TextView textListResep;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
