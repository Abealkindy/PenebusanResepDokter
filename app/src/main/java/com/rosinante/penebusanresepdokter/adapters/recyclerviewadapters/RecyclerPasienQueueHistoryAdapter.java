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
import com.rosinante.penebusanresepdokter.models.AntrianPasienModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerPasienQueueHistoryAdapter extends RecyclerView.Adapter<RecyclerPasienQueueHistoryAdapter.ViewHolder> {
    private List<AntrianPasienModel.AntrianModelData> antrianModelList;
    private Context context;
    public int pos;

    public RecyclerPasienQueueHistoryAdapter(Context context, List<AntrianPasienModel.AntrianModelData> antrianModelList) {
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
        viewHolder.textListAntrian.setText("Nomor antrian : " + antrianModelList.get(position).getAntrian_id() + "\n" +
                "Tanggal antrian : " + antrianModelList.get(position).getTanggal_antrian() + "\n" +
                "Nama pasien : " + antrianModelList.get(position).getPasien_name() + "\n" +
                "Nama dokter : " + antrianModelList.get(position).getDokter_name() + "\n" +
                "Nama poliklinik : " + antrianModelList.get(position).getPoliklinik_name() + "\n" +
                "Status antrian : " + antrianModelList.get(position).getStatus_antrian()
        );
        pos = viewHolder.getAdapterPosition();
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
