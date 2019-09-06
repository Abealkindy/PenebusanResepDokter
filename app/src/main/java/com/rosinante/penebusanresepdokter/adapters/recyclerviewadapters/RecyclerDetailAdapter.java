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
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_detail, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        if (String.valueOf(detailModelData.get(postion).getDetail_id()).length() == 1) {
            viewHolder.textViewDetailId.setText("DTL00" + detailModelData.get(postion).getDetail_id());
        } else if (String.valueOf(detailModelData.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewDetailId.setText("DTL0" + detailModelData.get(postion).getDetail_id());
        } else if (String.valueOf(detailModelData.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewDetailId.setText("DTL" + detailModelData.get(postion).getDetail_id());
        }
        if (String.valueOf(detailModelData.get(postion).getResep_id()).length() == 1) {
            viewHolder.textViewResepId.setText("RX00" + detailModelData.get(postion).getResep_id());
        } else if (String.valueOf(detailModelData.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewResepId.setText("RX0" + detailModelData.get(postion).getResep_id());
        } else if (String.valueOf(detailModelData.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewResepId.setText("RX" + detailModelData.get(postion).getResep_id());
        }
    }

    @Override
    public int getItemCount() {
        return detailModelData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_detail_id)
        TextView textViewDetailId;
        @BindView(R.id.text_view_resep_id)
        TextView textViewResepId;
        @BindView(R.id.card_item_list_with_text)
        CardView cardItemListWithText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
