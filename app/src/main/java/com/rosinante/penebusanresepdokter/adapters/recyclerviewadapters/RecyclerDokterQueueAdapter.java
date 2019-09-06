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
import com.rosinante.penebusanresepdokter.activities.dokterpages.dokteraddreseppage.DokterAddResepActivity;
import com.rosinante.penebusanresepdokter.models.AntrianDokterModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDokterQueueAdapter extends RecyclerView.Adapter<RecyclerDokterQueueAdapter.ViewHolder> {

    private List<AntrianDokterModel.AntrianModelData> antrianModelList;
    private Context context;

    public RecyclerDokterQueueAdapter(Context context, List<AntrianDokterModel.AntrianModelData> antrianModelList) {
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
        if (String.valueOf(antrianModelList.get(position).getAntrian_id()).length() == 1) {
            viewHolder.textViewIdAntrian.setText("QUE00" + antrianModelList.get(position).getAntrian_id());
        } else if (String.valueOf(antrianModelList.get(position).getAntrian_id()).length() == 2) {
            viewHolder.textViewIdAntrian.setText("QUE0" + antrianModelList.get(position).getAntrian_id());
        } else if (String.valueOf(antrianModelList.get(position).getAntrian_id()).length() >= 3) {
            viewHolder.textViewIdAntrian.setText("QUE" + antrianModelList.get(position).getAntrian_id());
        }
        viewHolder.textViewAntrianDetail.setText(antrianModelList.get(position).getPasien_name() + "\n" + antrianModelList.get(position).getPoliklinik_name() + " - " + antrianModelList.get(position).getDokter_name());
        viewHolder.cardItemListWithText.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), DokterAddResepActivity.class);
            intent.putExtra("antrian_id", antrianModelList.get(position).getAntrian_id());
            intent.putExtra("dokter_id", antrianModelList.get(position).getDokter_id());
            intent.putExtra("pasien_id", antrianModelList.get(position).getPasien_id());
            context.startActivity(intent);
        });
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
        @BindView(R.id.card_item_list_with_text)
        CardView cardItemListWithText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
