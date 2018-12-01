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
import com.rosinante.penebusanresepdokter.activities.kasirpages.KasirAddPembayaranActivity;
import com.rosinante.penebusanresepdokter.models.ResepAllModel;
import com.rosinante.penebusanresepdokter.models.ResepModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerResepKasirAdapter extends RecyclerView.Adapter<RecyclerResepKasirAdapter.ViewHolder> {

    private Context context;
    private List<ResepAllModel.ResepAllModelData> resepDataList;

    public RecyclerResepKasirAdapter(Context context, List<ResepAllModel.ResepAllModelData> resepDataList) {
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
        viewHolder.textListResep.append("ID Antrian : " + resepDataList.get(postion).getAntrian_id() + "\n");
        viewHolder.textListResep.append("Tanggal Resep : " + resepDataList.get(postion).getResep_date() + "\n");
        viewHolder.textListResep.append("Nama Dokter : " + resepDataList.get(postion).getDokter_name() + "\n");
        viewHolder.textListResep.append("Nama Pasien : " + resepDataList.get(postion).getPasien_name());

        viewHolder.cardItemResep.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), KasirAddPembayaranActivity.class);
            intent.putExtra("pasienID", resepDataList.get(postion).getPasien_id());
            intent.putExtra("antrianID", resepDataList.get(postion).getAntrian_id());
            intent.putExtra("resepID", resepDataList.get(postion).getResep_id());
            intent.putExtra("resepDate", resepDataList.get(postion).getResep_date());
            intent.putExtra("totalHarga", resepDataList.get(postion).getTotal_harga());
            context.startActivity(intent);
        });
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
