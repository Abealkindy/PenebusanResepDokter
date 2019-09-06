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
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.AdminPrintStrukActivity;
import com.rosinante.penebusanresepdokter.models.DetailStrukModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDetailStrukAdapter extends RecyclerView.Adapter<RecyclerDetailStrukAdapter.ViewHolder> {

    private Context context;
    private List<DetailStrukModel.DetailStrukModelData> detailStrukModelData;

    public RecyclerDetailStrukAdapter(Context context, List<DetailStrukModel.DetailStrukModelData> detailStrukModelData) {
        this.context = context;
        this.detailStrukModelData = detailStrukModelData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_print_struk, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        if (String.valueOf(detailStrukModelData.get(postion).getDetail_obat_id()).length() == 1) {
            viewHolder.textViewStrukId.setText("STR00" + detailStrukModelData.get(postion).getDetail_obat_id());
        } else if (String.valueOf(detailStrukModelData.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewStrukId.setText("STR0" + detailStrukModelData.get(postion).getDetail_obat_id());
        } else if (String.valueOf(detailStrukModelData.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewStrukId.setText("STR" + detailStrukModelData.get(postion).getDetail_obat_id());
        }
        if (String.valueOf(detailStrukModelData.get(postion).getResep_id()).length() == 1) {
            viewHolder.textViewResepIdAndDate.setText("RX00" + detailStrukModelData.get(postion).getResep_id() + " - " + setDateFormat(detailStrukModelData.get(postion).getPembayaran_date()));
        } else if (String.valueOf(detailStrukModelData.get(postion).getResep_id()).length() == 2) {
            viewHolder.textViewResepIdAndDate.setText("RX0" + detailStrukModelData.get(postion).getResep_id() + " - " + setDateFormat(detailStrukModelData.get(postion).getPembayaran_date()));
        } else if (String.valueOf(detailStrukModelData.get(postion).getResep_id()).length() >= 3) {
            viewHolder.textViewResepIdAndDate.setText("RX" + detailStrukModelData.get(postion).getResep_id() + " - " + setDateFormat(detailStrukModelData.get(postion).getPembayaran_date()));
        }
        if (detailStrukModelData.get(postion).getResep_status() == 0) {
            viewHolder.relativeListBottom.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else if (detailStrukModelData.get(postion).getResep_status() == 1) {
            viewHolder.relativeListBottom.setBackgroundColor(context.getResources().getColor(R.color.orange));
            viewHolder.cardItemListWithText.setOnClickListener(v -> Toast.makeText(context, "Resep sudah diproses!", Toast.LENGTH_SHORT).show());
        } else if (detailStrukModelData.get(postion).getResep_status() == 2) {
            viewHolder.relativeListBottom.setBackgroundColor(context.getResources().getColor(R.color.green_lumut));
        }
        viewHolder.relativeListBottom.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), AdminPrintStrukActivity.class);
            intent.putExtra("detail_obat_id", detailStrukModelData.get(postion).getDetail_obat_id());
            intent.putExtra("tanggal_pembayaran", detailStrukModelData.get(postion).getPembayaran_date());
            intent.putExtra("nama_pasien", detailStrukModelData.get(postion).getPasien_name());
            intent.putExtra("nama_dokter", detailStrukModelData.get(postion).getDokter_name());
            intent.putExtra("nama_poliklinik", detailStrukModelData.get(postion).getPoliklinik_name());
            intent.putExtra("resep_text", detailStrukModelData.get(postion).getResep_text());
            intent.putExtra("nama_obat", detailStrukModelData.get(postion).getNama_obat());
            intent.putExtra("dosis_obat", detailStrukModelData.get(postion).getDosis_obat());
            intent.putExtra("harga_obat", detailStrukModelData.get(postion).getHarga_obat());
            intent.putExtra("total_harga", detailStrukModelData.get(postion).getTotal_harga());
            intent.putExtra("dokter_tarif", detailStrukModelData.get(postion).getDokter_tarif());
            intent.putExtra("pembayaran", detailStrukModelData.get(postion).getUang_pembayaran());
            intent.putExtra("kembalian", detailStrukModelData.get(postion).getKembalian_pembayaran());
            intent.putExtra("resep_status", detailStrukModelData.get(postion).getResep_status());
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
        return detailStrukModelData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_struk_id)
        TextView textViewStrukId;
        @BindView(R.id.text_view_resep_id_and_date)
        TextView textViewResepIdAndDate;
        @BindView(R.id.relative_list_bottom)
        RelativeLayout relativeListBottom;
        @BindView(R.id.card_item_list_with_text)
        CardView cardItemListWithText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
