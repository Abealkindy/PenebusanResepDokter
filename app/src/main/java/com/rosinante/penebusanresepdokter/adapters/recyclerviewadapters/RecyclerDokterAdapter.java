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

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.dokter.AdminEditDokterProfileActivity;
import com.rosinante.penebusanresepdokter.models.DokterModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDokterAdapter extends RecyclerView.Adapter<RecyclerDokterAdapter.ViewHolder> {

    private Context context;
    private List<DokterModel.DokterData> dokterDataList;

    public RecyclerDokterAdapter(Context context, List<DokterModel.DokterData> dokterDataList) {
        this.context = context;
        this.dokterDataList = dokterDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_dokter, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textListAntrian.setText("Nama Dokter : " + dokterDataList.get(postion).getDokter_name() + "\n");
        viewHolder.textListAntrian.append("Spesialis Dokter : " + dokterDataList.get(postion).getDokter_specialist() + "\n");
        viewHolder.textListAntrian.append("Poliklinik : " + dokterDataList.get(postion).getPoliklinik_name());
        viewHolder.cardItemDokter.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), AdminEditDokterProfileActivity.class);
            intent.putExtra("dokter_id", dokterDataList.get(postion).getId_user());
            intent.putExtra("username", dokterDataList.get(postion).getUsername());
            intent.putExtra("user_password", dokterDataList.get(postion).getUser_password());
            intent.putExtra("dokter_name", dokterDataList.get(postion).getDokter_name());
            intent.putExtra("dokter_gender", dokterDataList.get(postion).getDokter_gender());
            intent.putExtra("dokter_address", dokterDataList.get(postion).getDokter_address());
            intent.putExtra("dokter_specialist", dokterDataList.get(postion).getDokter_specialist());
            intent.putExtra("dokter_email", dokterDataList.get(postion).getDokter_email());
            intent.putExtra("dokter_phone", dokterDataList.get(postion).getDokter_phone());
            intent.putExtra("dokter_tarif", dokterDataList.get(postion).getDokter_tarif());
            intent.putExtra("poliklinik_id", dokterDataList.get(postion).getPoliklinik_id());
            intent.putExtra("poliklinik_name", dokterDataList.get(postion).getPoliklinik_name());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dokterDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_dokter)
        TextView textListAntrian;
        @BindView(R.id.card_item_dokter)
        CardView cardItemDokter;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
