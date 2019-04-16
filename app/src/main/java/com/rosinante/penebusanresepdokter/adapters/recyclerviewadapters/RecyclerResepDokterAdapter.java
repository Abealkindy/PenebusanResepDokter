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
import com.rosinante.penebusanresepdokter.models.ResepDokterModel;
import com.rosinante.penebusanresepdokter.models.ResepModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerResepDokterAdapter extends RecyclerView.Adapter<RecyclerResepDokterAdapter.ViewHolder> {

    private Context context;
    private List<ResepDokterModel.ResepModelData> resepDataList;

    public RecyclerResepDokterAdapter(Context context, List<ResepDokterModel.ResepModelData> resepDataList) {
        this.context = context;
        this.resepDataList = resepDataList;
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
        viewHolder.textListResep.setText("ID Resep : " + resepDataList.get(postion).getResep_id() + "\n");
        viewHolder.textListResep.setText("Nama Dokter : " + resepDataList.get(postion).getDokter_name() + "\n");
        viewHolder.textListResep.setText("Nama Pasien : " + resepDataList.get(postion).getPasien_name() + "\n");
        viewHolder.textListResep.append("Tanggal Resep : " + resepDataList.get(postion).getResep_date() + "\n");
        viewHolder.textListResep.append("Isi Resep : " + resepDataList.get(postion).getResep_text() + "\n");
        if (resepDataList.get(postion).getResep_status() == 0) {
            viewHolder.textListResep.append("Status Resep : Belum Diproses");
        } else if (resepDataList.get(postion).getResep_status() == 1) {
            viewHolder.textListResep.append("Status Resep : Sudah Diproses");
        } else if (resepDataList.get(postion).getResep_status() == 2) {
            viewHolder.textListResep.append("Status Resep : Sudah Dibayar");
        }

    }

    @Override
    public int getItemCount() {
        return resepDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_resep)
        TextView textListResep;
        @BindView(R.id.card_item_resep)
        CardView cardItemResep;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
