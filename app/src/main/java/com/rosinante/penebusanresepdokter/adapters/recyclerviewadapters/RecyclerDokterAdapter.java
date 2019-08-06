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
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_list_doctor, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        if (dokterDataList.get(postion).getDokter_gender() == null
                && dokterDataList.get(postion).getDokter_address() == null
                && dokterDataList.get(postion).getDokter_specialist() == null
                && dokterDataList.get(postion).getDokter_email() == null
                && dokterDataList.get(postion).getDokter_phone() == null
                && dokterDataList.get(postion).getDokter_tarif() == null) {
            viewHolder.textViewDoctorName.setText("Nama belum diisi");
            viewHolder.textViewDoctorDetail.setText("Detail belum diisi");
            viewHolder.relativeListBottomDoctor.setOnClickListener(v -> {
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
        } else {
            viewHolder.textViewDoctorName.setText(dokterDataList.get(postion).getDokter_name());
            viewHolder.textViewDoctorDetail.append(dokterDataList.get(postion).getDokter_specialist() + " - " + dokterDataList.get(postion).getPoliklinik_name());
            if (dokterDataList.get(postion).getDokter_gender().equals("Pria")) {
                viewHolder.imageListDoctor.setImageDrawable(context.getResources().getDrawable(R.drawable.maledoctor));
                viewHolder.relativeListBottomDoctor.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                viewHolder.imageListDoctor.setImageDrawable(context.getResources().getDrawable(R.drawable.femaledoctor));
                viewHolder.relativeListBottomDoctor.setBackgroundColor(context.getResources().getColor(R.color.lavender_color));
            }
            viewHolder.relativeListBottomDoctor.setOnClickListener(v -> {
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

    }

    @Override
    public int getItemCount() {
        return dokterDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_list_doctor)
        ImageView imageListDoctor;
        @BindView(R.id.text_view_doctor_name)
        TextView textViewDoctorName;
        @BindView(R.id.text_view_doctor_detail)
        TextView textViewDoctorDetail;
        @BindView(R.id.relative_list_bottom_doctor)
        RelativeLayout relativeListBottomDoctor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
