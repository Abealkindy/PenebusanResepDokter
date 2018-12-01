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
import com.rosinante.penebusanresepdokter.models.PoliklinikModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerPoliklinikAdapter extends RecyclerView.Adapter<RecyclerPoliklinikAdapter.ViewHolder> {

    private Context context;
    private List<PoliklinikModel.PoliklinikData> poliklinikDataList;

    public RecyclerPoliklinikAdapter(Context context, List<PoliklinikModel.PoliklinikData> poliklinikDataList) {
        this.context = context;
        this.poliklinikDataList = poliklinikDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_poliklinik, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textListAntrian.setText("ID Poliklinik : " + poliklinikDataList.get(postion).getPoliklinik_id() + "\n");
        viewHolder.textListAntrian.append("Nama Poliklinik : " + poliklinikDataList.get(postion).getPoliklinik_name());
    }

    @Override
    public int getItemCount() {
        return poliklinikDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_poliklinik)
        TextView textListAntrian;
        @BindView(R.id.card_item_poliklinik)
        CardView cardItemDokter;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
