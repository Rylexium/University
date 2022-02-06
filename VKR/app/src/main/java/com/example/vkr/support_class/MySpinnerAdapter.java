package com.example.vkr.support_class;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MySpinnerAdapter extends ArrayAdapter {

    public MySpinnerAdapter(@NonNull Context context, int textViewResourceId, @NonNull List objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public boolean isEnabled(int position) {
        // Отключаем первый итем у спиннера
        // Делаем его как hint
        return position != 0;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if(position == 0)// Set the hint text color gray
            tv.setTextColor(Color.GRAY);
        else
            tv.setTextColor(Color.BLACK);
        return view;
    }
}
