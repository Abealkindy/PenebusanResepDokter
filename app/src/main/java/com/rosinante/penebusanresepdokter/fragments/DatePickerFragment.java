package com.rosinante.penebusanresepdokter.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.rosinante.penebusanresepdokter.R;

import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    private DatePicker datePicker;

    public interface DateDialogListener {
        void onFinishDialog(Date date);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        @SuppressLint("InflateParams")
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);
        datePicker = v.findViewById(R.id.dialog_date_date_picker);
        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        (dialog, which) -> {
                            int year = datePicker.getYear();
                            int mon = datePicker.getMonth();
                            int day = datePicker.getDayOfMonth();
                            Date date = new GregorianCalendar(year, mon, day).getTime();
                            DateDialogListener activity = (DateDialogListener) getActivity();
                            activity.onFinishDialog(date);
                            dismiss();
                        })
                .create();
    }
}
