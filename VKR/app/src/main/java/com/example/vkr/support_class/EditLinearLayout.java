package com.example.vkr.support_class;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.vkr.R;

public class EditLinearLayout {

    private static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
    public static void onAddField(Bitmap v, LinearLayout linearLayout, Activity activity) {
        LayoutInflater inflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.field, null);
        ImageView view = (ImageView) rowView.findViewById(R.id.image_edit);
        view.getLayoutParams().width = dpToPx(340, activity);
        view.getLayoutParams().height = dpToPx(180, activity);
        view.setImageBitmap(v);
        linearLayout.addView(rowView);//, layoutForImagePassport2.getChildCount() - 1);
    }


    public static void onDelete(View v, LinearLayout linearLayout) {
        linearLayout.removeView((View) v.getParent());
    }
    public static void clearAll(LinearLayout linearLayout){
        linearLayout.removeAllViews();
    }
}
