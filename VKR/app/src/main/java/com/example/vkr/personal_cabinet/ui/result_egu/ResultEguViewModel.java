package com.example.vkr.personal_cabinet.ui.result_egu;

import static java.util.Arrays.asList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.vkr.connectDB.Database;
import com.example.vkr.personal_cabinet.PersonalCabinetActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResultEguViewModel extends ViewModel {
    public static List<List<String>> exams;
    public static Map<String, String> minPointsExams;

    public ResultEguViewModel() {
        if(exams == null) {
            new Thread(() -> {
                Connection connect = new Database().connect();
                try {
                    PreparedStatement statement = connect
                            .prepareStatement("SELECT (select name from exams where id=id_exam) as name_exam, points, year\n" +
                                    "\tFROM abit_exams where id_abit=? order by id_exam desc");
                    statement.setLong(1, Long.parseLong(PersonalCabinetActivity.idAbit));
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
            }).start();
        }
    }

    public List<List<String>> getExams(){
        return exams;
    }

    public static void clearExams(){
        if(exams == null) return;
        exams.clear();
        exams = null;
    }

    public Map<String, String> getMinPointsExams(){
        return minPointsExams;
    }


}
