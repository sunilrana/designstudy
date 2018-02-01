package com.example.myapplication;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.timessquare.CalendarPickerView;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private EditText amount;
    private String current = "";
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.amount);
        amount.addTextChangedListener(this);
        send = findViewById(R.id.send_action);
//        send.setEnabled(false);

        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(current)) {
            amount.removeTextChangedListener(this);

            String cleanString = s.toString().replaceAll("[$,.]", "");

            double parsed = Double.parseDouble(cleanString);
            System.out.println("LUIS" + parsed);
            if(parsed/100>=1.00) {
                // enable button
                send.setEnabled(true);
            } else {
                send.setEnabled(false);
            }
            String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));
            current = formatted;
            amount.setText(formatted);
            amount.setSelection(formatted.length());

            amount.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void showDialog(){
//        Dialog alertDialog =new Dialog(this,R.style.full_screen_dialog);
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.calender_dialog, null);
//        alertDialog.setContentView(dialogView);
//
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 1);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.MONTH, 0);
//
//        CalendarPickerView calendarPickerView = dialogView.findViewById(R.id.calendar_view);
//        calendarPickerView.init(lastYear.getTime(), nextYear.getTime()) //
//                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
//                .withSelectedDate(new Date());
//        //do some stuff with your dialog and builder
////        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
////        lp.copyFrom(alertDialog.getWindow().getAttributes());
////        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
////        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        alertDialog.show();
////        alertDialog.getWindow().setAttributes(lp);

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.calender_dialog);
                CalendarPickerView calendarPickerView = dialog.findViewById(R.id.calendar_view);
        calendarPickerView.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.SINGLE) //
                .withSelectedDate(new Date());
        dialog.show();
    }


}
