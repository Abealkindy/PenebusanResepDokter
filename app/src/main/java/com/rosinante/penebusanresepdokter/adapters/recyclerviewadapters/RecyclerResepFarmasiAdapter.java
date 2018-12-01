package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.farmasipages.FarmasiAddDetailActivity;
import com.rosinante.penebusanresepdokter.models.ResepModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerResepFarmasiAdapter extends RecyclerView.Adapter<RecyclerResepFarmasiAdapter.ViewHolder> {

    private Context context;
    private List<ResepModel.ResepData> resepDataList;

    public RecyclerResepFarmasiAdapter(Context context, List<ResepModel.ResepData> resepDataList) {
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
        viewHolder.textListResep.append("Tanggal Resep : " + resepDataList.get(postion).getResep_date() + "\n");
        viewHolder.textListResep.append("Isi Resep : " + resepDataList.get(postion).getResep_text() + "\n");
        if (resepDataList.get(postion).getResep_status() == 0) {
            viewHolder.textListResep.append("Status Resep : Belum Diproses");
            viewHolder.cardItemResep.setOnClickListener(v -> {
                Intent intent = new Intent(context.getApplicationContext(), FarmasiAddDetailActivity.class);
                intent.putExtra("resepID", resepDataList.get(postion).getResep_id());
                context.startActivity(intent);
            });
        } else if (resepDataList.get(postion).getResep_status() == 1) {
            viewHolder.textListResep.append("Status Resep : Sudah Diproses");
            viewHolder.cardItemResep.setOnClickListener(v -> {
                Toast.makeText(context, "Resep sudah diproses!", Toast.LENGTH_SHORT).show();
            });
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
