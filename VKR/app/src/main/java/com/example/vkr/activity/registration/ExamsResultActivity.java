package com.example.vkr.activity.registration;

import static android.widget.AdapterView.OnItemSelectedListener;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.personal_cabinet.PersonalCabinetActivity;
import com.example.vkr.utils.HideKeyboardClass;
import com.example.vkr.utils.MySpinnerAdapter;
import com.example.vkr.utils.ShowToast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ExamsResultActivity extends AppCompatActivity {

    private LinearLayout examsLayout;
    private static List<String> exams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.exams_result_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initComponents();
        appleEvents();
    }


    public void onAddField(View v){
        LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView=inflater.inflate(R.layout.field_for_exam, null);
        EditText pointsExam = rowView.findViewById(R.id.text_points_of_exam);
        Spinner spinnerExams = rowView.findViewById(R.id.exam);

        Spinner spinnerYear = rowView.findViewById(R.id.spinner_date_exam);
        spinnerYear.setAdapter(new MySpinnerAdapter(this, R.layout.spinner_item, Arrays.asList(getResources().getStringArray(R.array.date_of_exams))));
        spinnerYear.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { //выбран какой
                    TextView text = (TextView) view;
                    Optional.ofNullable(text).ifPresent(e -> text.setTextColor(Color.BLACK));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        spinnerExams.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { //выбран какой
                    TextView text = (TextView) view;
                    Optional.ofNullable(text).ifPresent(e -> text.setTextColor(Color.BLACK));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        spinnerExams.setAdapter(new MySpinnerAdapter(this, R.layout.spinner_item, exams));
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
            if(examsLayout.getChildCount() > 3){
                if(isCorrectData()){
                    new Thread(()->{
                        Connection connection = new Database().connect();
                        StringBuilder resQuery = new StringBuilder();
                        for(int i=0; i<examsLayout.getChildCount() - 1; ++i){
                            Spinner exam = examsLayout.getChildAt(i).findViewById(R.id.exam);
                            Spinner year = examsLayout.getChildAt(i).findViewById(R.id.spinner_date_exam);
                            EditText points = examsLayout.getChildAt(i).findViewById(R.id.text_points_of_exam);
                            resQuery.append(String.format("INSERT INTO public.abit_exams(\n" +
                                            "\tid_abit, id_exam, points, year)\n" +
                                            "\tVALUES (%s, %d, %s, %s);\n",
                                    getIntent().getStringExtra("id_abit"),
                                    exam.getSelectedItemPosition(),
                                    points.getText().toString(),
                                    year.getSelectedItem().toString()));
                        }
                        try {
                            connection.createStatement().execute(resQuery.toString());
                            connection.createStatement().execute("UPDATE public.users\n" +
                                    "\tSET is_entry=true\n" +
                                    "\tWHERE id_abit=" + getIntent().getStringExtra("id_abit"));
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    finish();
                    startActivity(new Intent(ExamsResultActivity.this, PersonalCabinetActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK)
                            .putExtra("login", getIntent().getStringExtra("login")));
                }
            }
            else
                ShowToast.show(this, "Экзаменов не может быть меньше 3");
        });
    }

    private boolean isCorrectData(){
        for(int i=0; i<examsLayout.getChildCount() - 1; ++i){
            Spinner exam = examsLayout.getChildAt(i).findViewById(R.id.exam);
            Spinner year = examsLayout.getChildAt(i).findViewById(R.id.spinner_date_exam);
            EditText points = examsLayout.getChildAt(i).findViewById(R.id.text_points_of_exam);
            if(exam.getSelectedItemPosition() == 0 //проверка на пустоту полей
                    || year.getSelectedItemPosition() == 0
                    || points.getText().toString().equals("")){
                ShowToast.show(this, "Есть пустые поля");
                return false;
            }
            else{
                for(int j=i+1; j<examsLayout.getChildCount() - 1; ++j){
                    Spinner exam1 = examsLayout.getChildAt(j).findViewById(R.id.exam);
                    if(exam.getSelectedItemPosition() == exam1.getSelectedItemPosition()){
                        ShowToast.show(this, "Одинаковые экзамены не могут быть");
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private void initComponents(){
        examsLayout = findViewById(R.id.exams_layout);
        new Thread(()->{
            Connection connect = new Database().connect();
            try {
                exams = new ArrayList<>();
                exams.add("Выберите экзамен");
                ResultSet res = connect.createStatement().executeQuery("select name from exams where id<12");
                while (res.next()){
                    exams.add(res.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }).start();
    }


}