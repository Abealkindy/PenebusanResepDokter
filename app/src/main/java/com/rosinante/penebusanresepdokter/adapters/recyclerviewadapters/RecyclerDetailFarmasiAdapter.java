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
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDetailFarmasiAdapter extends RecyclerView.Adapter<RecyclerDetailFarmasiAdapter.ViewHolder> {

    private Context context;
    private List<DetailFarmasiModel.DetailFarmasiData> detailFarmasiData;

    public RecyclerDetailFarmasiAdapter(Context context, List<DetailFarmasiModel.DetailFarmasiData> detailFarmasiData) {
        this.context = context;
        this.detailFarmasiData = detailFarmasiData;
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
        if (String.valueOf(detailFarmasiData.get(postion).getDetail_obat_id()).length() == 1) {
            viewHolder.textViewDetailId.setText("DTL00" + detailFarmasiData.get(postion).getDetail_obat_id());
        } else if (String.valueOf(detailFarmasiData.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewDetailId.setText("DTL0" + detailFarmasiData.get(postion).getDetail_obat_id());
        } else if (String.valueOf(detailFarmasiData.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewDetailId.setText("DTL" + detailFarmasiData.get(postion).getDetail_obat_id());
        }
        if (String.valueOf(detailFarmasiData.get(postion).getResep_id()).length() == 1) {
            viewHolder.textViewResepId.setText("RX00" + detailFarmasiData.get(postion).getResep_id() + " - " + setDateFormat(detailFarmasiData.get(postion).getResep_date()));
        } else if (String.valueOf(detailFarmasiData.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewResepId.setText("RX0" + detailFarmasiData.get(postion).getResep_id() + " - " + setDateFormat(detailFarmasiData.get(postion).getResep_date()));
        } else if (String.valueOf(detailFarmasiData.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewResepId.setText("RX" + detailFarmasiData.get(postion).getResep_id() + " - " + setDateFormat(detailFarmasiData.get(postion).getResep_date()));
        }
    }

    private String setDateFormat(String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat.applyPattern("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return detailFarmasiData.size();
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
