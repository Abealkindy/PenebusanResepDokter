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
import com.rosinante.penebusanresepdokter.models.AntrianModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerPasienQueueAdapter extends RecyclerView.Adapter<RecyclerPasienQueueAdapter.ViewHolder> {
    private List<AntrianModel.AntrianModelData> antrianModelList;
    private Context context;
    public int pos;

    public RecyclerPasienQueueAdapter(Context context, List<AntrianModel.AntrianModelData> antrianModelList) {
        this.context = context;
        this.antrianModelList = antrianModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_queue, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        pos = viewHolder.getAdapterPosition();
        viewHolder.textListAntrian.setText("Nomor antrian : " + antrianModelList.get(position).getAntrian_id() + "\n" +
                "Tanggal antrian : " + antrianModelList.get(position).getTanggal_antrian() + "\n" +
                "Nama pasien : " + antrianModelList.get(position).getPasien_name() + "\n" +
                "Nama dokter : " + antrianModelList.get(position).getDokter_name() + "\n" +
                "Nama poliklinik : " + antrianModelList.get(position).getPoliklinik_name());
    }

    @Override
    public int getItemCount() {
        return antrianModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_antrian)
        TextView textListAntrian;
        @BindView(R.id.card_item_antrian)
        CardView cardItemAntrian;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
