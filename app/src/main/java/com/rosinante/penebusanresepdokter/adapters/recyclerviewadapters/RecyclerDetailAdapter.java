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
import com.rosinante.penebusanresepdokter.models.ResepModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDetailAdapter extends RecyclerView.Adapter<RecyclerDetailAdapter.ViewHolder> {

    private Context context;
    private List<DetailModel.DetailModelData> detailModelData;

    public RecyclerDetailAdapter(Context context, List<DetailModel.DetailModelData> detailModelData) {
        this.context = context;
        this.detailModelData = detailModelData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textListDetail.setText("ID Detail : " + detailModelData.get(postion).getDetail_id() + "\n");
        viewHolder.textListDetail.append("ID Resep : " + detailModelData.get(postion).getResep_id());
    }

    @Override
    public int getItemCount() {
        return detailModelData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_list_detail)
        TextView textListDetail;
        @BindView(R.id.card_item_detail)
        CardView cardItemDetail;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
