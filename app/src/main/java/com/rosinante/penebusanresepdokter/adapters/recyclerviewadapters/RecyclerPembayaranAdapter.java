package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.PembayaranModel;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerPembayaranAdapter extends RecyclerView.Adapter<RecyclerPembayaranAdapter.ViewHolder> {

    private Context context;
    private List<PembayaranModel.PembayaranModelData> pembayaranModelData;

    public RecyclerPembayaranAdapter(Context context, List<PembayaranModel.PembayaranModelData> pembayaranModelData) {
        this.context = context;
        this.pembayaranModelData = pembayaranModelData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_pembayaran, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textViewPasienNamePembayaram.setText(pembayaranModelData.get(postion).getPasien_name());
        viewHolder.textViewDatePembayaran.setText(setDateFormat(pembayaranModelData.get(postion).getPembayaran_date()));
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
        return pembayaranModelData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_pasien_name_pembayaram)
        TextView textViewPasienNamePembayaram;
        @BindView(R.id.text_view_date_pembayaran)
        TextView textViewDatePembayaran;
        @BindView(R.id.relative_list_bottom)
        RelativeLayout relativeListBottom;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
