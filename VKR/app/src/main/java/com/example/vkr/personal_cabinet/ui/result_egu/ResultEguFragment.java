package com.example.vkr.personal_cabinet.ui.result_egu;

import static java.util.Arrays.asList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResultEguFragment extends Fragment {

    private View binding;
    private LinearLayout layoutOfExams;
    private static String idAbit;
    private static List<List<String>> exams;
    private static Map<String, String> minPointsExams;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = inflater.inflate(R.layout.fragment_result_egu, container, false);
        layoutOfExams = binding.findViewById(R.id.layout_of_exams);
        if(exams == null) {
            new Thread(() -> {
                Connection connect = new Database().connect();
                try {
                    PreparedStatement statement = connect
                            .prepareStatement("SELECT (select name from exams where id=id_exam) as name_exam, points, year\n" +
                                    "\tFROM abit_exams where id_abit=? order by id_exam desc");
                    statement.setLong(1, Long.parseLong(idAbit));
                    ResultSet res = statement.executeQuery();
                    exams = new ArrayList<>();
                    while (res.next())
                        exams.add(asList(res.getString("name_exam"), res.getString("points"), res.getString("year")));


                    statement.close();
                    res.close();

                    PreparedStatement statMinPointsExams = connect.prepareStatement("select distinct \n" +
                                                        "\t(select name from exams where id=id_exam) as exam, min_points\n" +
                                                        "\tfrom speciality_exams order by min_points desc;");
                    ResultSet resMinPointsExams = statMinPointsExams.executeQuery();
                    minPointsExams = new HashMap<>();
                    while(resMinPointsExams.next())
                        minPointsExams.put(resMinPointsExams.getString("exam"), resMinPointsExams.getString("min_points"));


                    resMinPointsExams.close();
                    statMinPointsExams.close();

                    connect.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(this::fillTable);
            }).start();
        }
        else fillTable();

        return binding.getRootView();
    }
    private void onAddField(String nameExam, String pointsExam, String yearExam) {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_for_exam_noedit, null);

        EditText exam = rowView.findViewById(R.id.exam_noedit);
        EditText points = rowView.findViewById(R.id.text_points_of_exam_noedit);
        EditText year = rowView.findViewById(R.id.spinner_date_exam_noedit);

        exam.setText(nameExam);
        points.setText(pointsExam);
        if(Integer.parseInt(pointsExam) < Integer.parseInt(Objects.requireNonNull(minPointsExams.get(nameExam))))
            points.setTextColor(Color.RED);
        else
            points.setTextColor(Color.BLACK);
        //if pointsExams < 39 color.red
        //else color.black
        year.setText(yearExam);

        layoutOfExams.addView(rowView, 0);

    }

    private void addFieldSumExams() {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_for_exam_noedit, null);
        EditText exam = rowView.findViewById(R.id.exam_noedit);
        EditText points = rowView.findViewById(R.id.text_points_of_exam_noedit);

        exam.setText("Сумма баллов ЕГЭ");
        points.setText(String.valueOf(exams.stream().mapToInt(e -> Integer.parseInt(e.get(1))).sum()));

        LinearLayout ll = rowView.findViewById(R.id.field_for_exam_noedit_main_layout);
        LinearLayout ll1 = rowView.findViewById(R.id.field_for_exam_noedit_layout2);
        ll.removeView(ll1);

        layoutOfExams.addView(rowView, 0);
    }

    private void fillTable(){
        for(int i=0; i < exams.size(); ++i)
            onAddField(exams.get(i).get(0), exams.get(i).get(1), exams.get(i).get(2));
        addFieldSumExams();
    }
    public static void clearTable(){
        if(exams == null) return;
        exams.clear();
        exams = null;
    }
    public static void setIdAbit(String snills){
        idAbit = snills;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}