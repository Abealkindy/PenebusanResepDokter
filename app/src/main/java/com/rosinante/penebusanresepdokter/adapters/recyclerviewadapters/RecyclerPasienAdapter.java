package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.pasien.admineditpasienprofile.AdminEditPasienProfileActivity;
import com.rosinante.penebusanresepdokter.models.PasienModel;

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

public class RecyclerPasienAdapter extends RecyclerView.Adapter<RecyclerPasienAdapter.ViewHolder> {

    private Context context;
    private List<PasienModel.PasienData> pasienDataList;

    public RecyclerPasienAdapter(Context context, List<PasienModel.PasienData> pasienDataList) {
        this.context = context;
        this.pasienDataList = pasienDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_list_pasien, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textViewPasienName.setText(pasienDataList.get(postion).getPasien_name());
        if (pasienDataList.get(postion).getPasien_age() == null) {
            viewHolder.textViewPasienDetail.setText(pasienDataList.get(postion).getPasien_gender() + " - " + "Umur belum diinput");
        } else {
            viewHolder.textViewPasienDetail.setText(pasienDataList.get(postion).getPasien_gender() + " - " + getAge(pasienDataList.get(postion).getPasien_age()));
        }
        if (pasienDataList.get(postion).getPasien_gender().equals("Pria")) {
            viewHolder.imageListPasien.setImageDrawable(context.getResources().getDrawable(R.drawable.malepatient));
            viewHolder.relativeListBottom.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.imageListPasien.setImageDrawable(context.getResources().getDrawable(R.drawable.femalepatient));
            viewHolder.relativeListBottom.setBackgroundColor(context.getResources().getColor(R.color.lavender_color));
        }

        viewHolder.relativeListBottom.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), AdminEditPasienProfileActivity.class);
            intent.putExtra("pasien_id", pasienDataList.get(postion).getId_user());
            intent.putExtra("username", pasienDataList.get(postion).getUsername());
            intent.putExtra("user_password", pasienDataList.get(postion).getUser_password());
            intent.putExtra("pasien_name", pasienDataList.get(postion).getPasien_name());
            intent.putExtra("pasien_gender", pasienDataList.get(postion).getPasien_gender());
            intent.putExtra("pasien_address", pasienDataList.get(postion).getPasien_address());
            intent.putExtra("pasien_age", pasienDataList.get(postion).getPasien_age());
            intent.putExtra("pasien_email", pasienDataList.get(postion).getPasien_email());
            intent.putExtra("pasien_phone", pasienDataList.get(postion).getPasien_phone());
            context.startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private String getAge(String dobString) {

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd", Locale.getDefault());
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar dob = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        LocalDate birthDate = new LocalDate(year, month + 1, day);
        LocalDate nowDate = LocalDate.now();
        Period period = new Period(birthDate, nowDate, PeriodType.yearMonthDay());
        return period.getYears() + " Tahun " + period.getMonths() + " Bulan " + period.getDays() + " Hari";
    }

    @Override
    public int getItemCount() {
        return pasienDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_list_pasien)
        ImageView imageListPasien;
        @BindView(R.id.text_view_pasien_name)
        TextView textViewPasienName;
        @BindView(R.id.text_view_pasien_detail)
        TextView textViewPasienDetail;
        @BindView(R.id.relative_list_bottom)
        RelativeLayout relativeListBottom;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
