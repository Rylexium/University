package com.example.vkr.personal_cabinet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.HideKeyboardClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamsResultActivity extends AppCompatActivity {

    private LinearLayout examsLayout;
    private SharedPreferences sharedPreferences;
    private ImageView imageCabinetPassport1;

    private final String KEY_NAME_EXAM   = "exam";
    private final String KEY_POINTS_EXAM = "points";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.exams_result_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initComponents();
        appleEvents();
        comebackAfterOnBackPressed();
    }

    @Override
    public void onBackPressed(){
        saveLastState();
        super.onBackPressed();
    }

    private void comebackAfterOnBackPressed(){
        String idSelectedExam = sharedPreferences.getString(KEY_NAME_EXAM + "0", null);
        if(TextUtils.isEmpty(idSelectedExam)) return;
        for(int i = 0; !TextUtils.isEmpty(idSelectedExam); i++){
            idSelectedExam = sharedPreferences.getString(KEY_NAME_EXAM + i, null);
            String pointsExam = sharedPreferences.getString(KEY_POINTS_EXAM + i, null);
            onAddField(Integer.parseInt(idSelectedExam), pointsExam);
            idSelectedExam = sharedPreferences.getString(KEY_NAME_EXAM + (i+1), null);
        }
    }

    public void onAddField(View v){
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.field_for_exam, null);
        EditText pointsExam = rowView.findViewById(R.id.text_points_of_exam);

        pointsExam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (Integer.parseInt(charSequence.toString()) < 0)
                        pointsExam.setText("0");
                    else if (Integer.parseInt(charSequence.toString()) > 100) {
                        pointsExam.setText("100");
                        pointsExam.setSelection(3);
                    }
                } catch (Exception ignored){}
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
        examsLayout.addView(rowView, examsLayout.getChildCount() - 1);
    }
    private void onAddField(int selectedItem, String points){
        LayoutInflater inflater= (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.field_for_exam, null);
        Spinner nameExam = rowView.findViewById(R.id.exam);
        EditText pointsExam = rowView.findViewById(R.id.text_points_of_exam);

        pointsExam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (Integer.parseInt(charSequence.toString()) < 0)
                        pointsExam.setText("0");
                    else if (Integer.parseInt(charSequence.toString()) > 100) {
                        pointsExam.setText("100");
                        pointsExam.setSelection(3);
                    }
                } catch (Exception ignored){}
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
        nameExam.setSelection(selectedItem);
        pointsExam.setText(points);
        examsLayout.addView(rowView, examsLayout.getChildCount() - 1);
    }
    public void onDelete(View v) {
        examsLayout.removeView((View) v.getParent().getParent());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
            examsLayout.clearFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void appleEvents(){
        findViewById(R.id.button_next_to_lk).setOnClickListener(view -> {
            if(examsLayout.getChildCount() > 3)
                startActivity(new Intent(ExamsResultActivity.this, PersonalCabinetActivity.class));
            else
                Toast.makeText(this, "Экзаменов не может быть меньше 3", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveLastState(){
        sharedPreferences.edit().clear().apply();
        for(int i=0; i<examsLayout.getChildCount() - 1; i++){
            Spinner nameExam = examsLayout.getChildAt(i).findViewById(R.id.exam);
            EditText pointsExam = examsLayout.getChildAt(i).findViewById(R.id.text_points_of_exam);
            sharedPreferences.edit()
                    .putString(KEY_NAME_EXAM + i,   String.valueOf(nameExam.getSelectedItemPosition()))
                    .putString(KEY_POINTS_EXAM + i, pointsExam.getText().toString())
                    .apply();
        }
    }

    private void initComponents(){
        examsLayout = findViewById(R.id.exams_layout);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        imageCabinetPassport1 = findViewById(R.id.image_cabinet_passport1);
        new Thread(()->{
            Connection connection = new Database().connect();
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement("select image_passport1 from abit where login=?");
                statement.setString(1, "rylexium");
                ResultSet picture = statement.executeQuery();
                if(picture.next()){
                    new Handler(Looper.getMainLooper()).post(() -> {
                        try {
                            imageCabinetPassport1.setImageBitmap(ConvertClass.convertStringToBitmap(picture.getString("image_passport1")));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    });
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();
    }
}