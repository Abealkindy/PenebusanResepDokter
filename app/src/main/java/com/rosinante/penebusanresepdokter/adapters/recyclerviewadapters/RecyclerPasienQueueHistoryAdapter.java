package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_queue, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        int antrianIDLength = String.valueOf(antrianModelList.get(position).getAntrian_id()).length();
        if (antrianIDLength == 1) {
            viewHolder.textViewIdAntrian.setText("QUE00" + antrianModelList.get(position).getAntrian_id());
        } else if (antrianIDLength == 2) {
            viewHolder.textViewIdAntrian.setText("QUE0" + antrianModelList.get(position).getAntrian_id());
        } else if (antrianIDLength >= 3) {
            viewHolder.textViewIdAntrian.setText("QUE" + antrianModelList.get(position).getAntrian_id());
        }
        switch (antrianModelList.get(position).getStatus_antrian()) {
            case 0:
                viewHolder.linearCenter.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                break;
            case 1:
                viewHolder.linearCenter.setBackgroundColor(context.getResources().getColor(R.color.orange));
                break;
            case 2:
                viewHolder.linearCenter.setBackgroundColor(context.getResources().getColor(R.color.green_lumut));
                break;
        }
        viewHolder.textViewAntrianDetail.setText(antrianModelList.get(position).getPasien_name() + "\n" + antrianModelList.get(position).getPoliklinik_name() + " - " + antrianModelList.get(position).getDokter_name());
        pos = viewHolder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return antrianModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_id_antrian)
        TextView textViewIdAntrian;
        @BindView(R.id.text_view_antrian_detail)
        TextView textViewAntrianDetail;
        @BindView(R.id.linear_center)
        LinearLayout linearCenter;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
