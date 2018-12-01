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
import com.rosinante.penebusanresepdokter.models.DetailModel;
import com.rosinante.penebusanresepdokter.models.PembayaranModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerPembayaranAdapter extends RecyclerView.Adapter<RecyclerPembayaranAdapter.ViewHolder> {

    private Context context;
    private List<PembayaranModel.PembayaranModelData> pembayaranModelData;

    public RecyclerPembayaranAdapter(Context context, List<PembayaranModel.PembayaranModelData> pembayaranModelData) {
        this.context = context;
        this.pembayaranModelData = pembayaranModelData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_pembayaran, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textListPembayaran.setText("ID Pembayaran : " + pembayaranModelData.get(postion).getPembayaran_id() + "\n");
        viewHolder.textListPembayaran.append("Nama Pasien : " + pembayaranModelData.get(postion).getPasien_name() + "\n");
        viewHolder.textListPembayaran.append("Tanggal Pembayaran : " + pembayaranModelData.get(postion).getPembayaran_date());
    }

    @Override
    public int getItemCount() {
        return pembayaranModelData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_pembayaran)
        TextView textListPembayaran;
        @BindView(R.id.card_item_pembayaran)
        CardView cardItemDetail;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
