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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.kasirpages.kasiraddpembayaranpage.KasirAddPembayaranActivity;
import com.rosinante.penebusanresepdokter.models.ResepAllModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_resep_dokter, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textViewPasienNameResep.setText(resepDataList.get(postion).getPasien_name());
        if (resepDataList.get(postion).getResep_status() == 0) {
            viewHolder.relativeListBottomResep.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (resepDataList.get(postion).getResep_status() == 1) {
            viewHolder.relativeListBottomResep.setBackgroundColor(context.getResources().getColor(R.color.orange));
        } else if (resepDataList.get(postion).getResep_status() == 2) {
            viewHolder.relativeListBottomResep.setBackgroundColor(context.getResources().getColor(R.color.green_lumut));
        }
        if (String.valueOf(resepDataList.get(postion).getResep_id()).length() == 1) {
            viewHolder.textViewResepDetail.setText("RX00" + resepDataList.get(postion).getResep_id() + " - " + setDateFormat(resepDataList.get(postion).getResep_date()));
        } else if (String.valueOf(resepDataList.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewResepDetail.setText("RX0" + resepDataList.get(postion).getResep_id() + " - " + setDateFormat(resepDataList.get(postion).getResep_date()));
        } else if (String.valueOf(resepDataList.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewResepDetail.setText("RX" + resepDataList.get(postion).getResep_id() + " - " + setDateFormat(resepDataList.get(postion).getResep_date()));
        }
        viewHolder.cardItemListWithText.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), KasirAddPembayaranActivity.class);
            intent.putExtra("pasienID", resepDataList.get(postion).getPasien_id());
            intent.putExtra("antrianID", resepDataList.get(postion).getAntrian_id());
            intent.putExtra("resepID", resepDataList.get(postion).getResep_id());
            intent.putExtra("resepDate", resepDataList.get(postion).getResep_date());
            intent.putExtra("totalHarga", resepDataList.get(postion).getTotal_harga());
            context.startActivity(intent);
        });
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
        return resepDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_pasien_name_resep)
        TextView textViewPasienNameResep;
        @BindView(R.id.text_view_resep_detail)
        TextView textViewResepDetail;
        @BindView(R.id.relative_list_bottom_resep)
        RelativeLayout relativeListBottomResep;
        @BindView(R.id.card_item_list_with_text)
        CardView cardItemListWithText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
