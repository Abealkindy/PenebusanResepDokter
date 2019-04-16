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
import com.rosinante.penebusanresepdokter.activities.adminpages.pasien.AdminEditPasienProfileActivity;
import com.rosinante.penebusanresepdokter.activities.adminpages.printstruk.AdminPrintStrukActivity;
import com.rosinante.penebusanresepdokter.models.DetailFarmasiModel;
import com.rosinante.penebusanresepdokter.models.DetailStrukModel;

import java.util.List;

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
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_resep, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textListResep.setText("ID Struk : " + detailStrukModelData.get(postion).getDetail_obat_id() + "\n");
        viewHolder.textListResep.append("Tanggal Pembayaran : " + detailStrukModelData.get(postion).getPembayaran_date() + "\n");
        viewHolder.textListResep.append("Nama Pasien : " + detailStrukModelData.get(postion).getPasien_name() + "\n");
        viewHolder.textListResep.append("Nama Dokter : " + detailStrukModelData.get(postion).getDokter_name() + "\n");
        viewHolder.textListResep.append("Nama Poliklinik : " + detailStrukModelData.get(postion).getPoliklinik_name() + "\n");
        viewHolder.textListResep.append("Nama Obat : " + detailStrukModelData.get(postion).getNama_obat() + "\n");
        if (detailStrukModelData.get(postion).getResep_status() == 0) {
            viewHolder.textListResep.append("Status Resep : Belum Diproses");
        } else if (detailStrukModelData.get(postion).getResep_status() == 1) {
            viewHolder.textListResep.append("Status Resep : Sudah Diproses");
        } else if (detailStrukModelData.get(postion).getResep_status() == 2) {
            viewHolder.textListResep.append("Status Resep : Sudah Dibayar");
        }
        viewHolder.cardItemResep.setOnClickListener(v -> {
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

    @Override
    public int getItemCount() {
        return detailStrukModelData.size();
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
