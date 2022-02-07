package com.example.vkr.personal_cabinet.moreAbout;

import static java.util.Arrays.asList;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoreAboutTheSpecialityActivity extends AppCompatActivity {

    private String id;

    private TextView institutOfSpeciality;
    private TextView facultOfSpeciality;
    private TextView nameOfSpeciality;
    private TextView idOfSpeciality;
    private TextView fioCuratorOfSpeciality;
    private TextView phoneOfSpeciality;
    private TextView studyingTimeOfSpeciality;
    private TextView typeOfStudyOfSpeciality;
    private TextView budgetOfSpeciality;
    private TextView payPerYearOfSpeciality;
    private TextView payOfSpeciality;
    private TextView descriptionOfSpeciality;

    private LinearLayout mainLayout;
    private LinearLayout layoutOfExamsForSpeciality;

    private List<List<String>> exams;

    private LinearLayout competenciesOfSpeciality;
    private LinearLayout professionsOfSpeciality;
    private LinearLayout partnersOfSpeciality;

    private String competencies, professions, partners;
    private boolean isCompetencies = false, isProfessions = false, isPartners = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_about_the_speciality);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initComponents();
        applyEvents();
    }

    private void applyEvents() {
        competenciesOfSpeciality.setOnClickListener(view-> {
            setTextForQuestion(isCompetencies, competenciesOfSpeciality, competencies);
            isCompetencies = !isCompetencies;
        });

        professionsOfSpeciality.setOnClickListener(view-> {
            setTextForQuestion(isProfessions, professionsOfSpeciality, professions);
            isProfessions = !isProfessions;
        });

        partnersOfSpeciality.setOnClickListener(view-> {
            setTextForQuestion(isPartners, partnersOfSpeciality, partners);
            isPartners = !isPartners;
        });
    }

    private void setTextForQuestion(boolean isPressed, LinearLayout linearLayout, String text){
        if(!isPressed){
            LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView=inflater.inflate(R.layout.field_for_questions, null);
            TextView textQuestion = rowView.findViewById(R.id.text_question);
            textQuestion.setText(text);
            linearLayout.addView(rowView);
        }
        else linearLayout.removeViewAt(linearLayout.getChildCount() - 1);
    }

    private void initComponents() {
        id = getIntent().getStringExtra("id");
        institutOfSpeciality = findViewById(R.id.institut_of_speciality);
        facultOfSpeciality = findViewById(R.id.facult_of_speciality);
        nameOfSpeciality = findViewById(R.id.name_of_speciality);
        idOfSpeciality = findViewById(R.id.id_of_speciality);
        fioCuratorOfSpeciality = findViewById(R.id.fio_curator_of_speciality);
        phoneOfSpeciality = findViewById(R.id.phone_of_speciality);
        studyingTimeOfSpeciality = findViewById(R.id.studying_time_of_speciality);
        typeOfStudyOfSpeciality = findViewById(R.id.type_of_study_of_speciality);
        budgetOfSpeciality = findViewById(R.id.budget_of_speciality);
        payPerYearOfSpeciality = findViewById(R.id.pay_per_year_of_speciality);
        payOfSpeciality = findViewById(R.id.pay_of_speciality);
        descriptionOfSpeciality = findViewById(R.id.description_of_speciality);

        mainLayout = findViewById(R.id.layout_more_about_the_speciality);
        layoutOfExamsForSpeciality = findViewById(R.id.layout_of_exams_for_speciality);

        competenciesOfSpeciality = findViewById(R.id.competencies_of_speciality);
        professionsOfSpeciality = findViewById(R.id.professions_of_speciality);
        partnersOfSpeciality = findViewById(R.id.partners_of_speciality);

        new Thread(()->{
            Connection connection = new Database().connect();
            try {
                ResultSet res = connection.prepareStatement("SELECT \n" +
                        "\t(select name from type_of_study where id=type_of_study) as type_of_study, \n" +
                        "\tname, budget, pay, \n" +
                        "\t(select name from institutions where id=id_institut) as id_institut, \n" +
                        "\tdescription, \n" +
                        "\tcompetencies, professions, partners, direction, studying_time, \n" +
                        "\t(select (family || ' ' || name || ' ' || patronymic) from employees where id=curator) as curator, \n" +
                        "\tpay_per_year, contact_number, \n" +
                        "\t(select name from faculties where id=id_facult) as id_facult\n" +
                        "\tFROM public.speciality where id='" + id+ "' and " +
                        "type_of_study=" + getTypeOfStudy()).executeQuery();
                if(!res.next()) return;

                String idSpeciality = getIntent().getStringExtra("id");
                String institutSpeciality = res.getString("id_institut");
                String facultSpeciality = res.getString("id_facult");
                String nameSpeciality = res.getString("name");
                String fioCuratorSpeciality = res.getString("curator");
                String phoneSpeciality = res.getString("contact_number");
                String studyingTimeSpeciality = res.getString("studying_time");
                String typeOfStudySpeciality = res.getString("type_of_study");
                String budgetSpeciality = res.getString("budget");
                String payPerYearSpeciality = res.getString("pay_per_year");
                String paySpeciality = res.getString("pay");
                String descriptionSpeciality = res.getString("description");

                competencies = res.getString("competencies");
                professions = res.getString("professions");
                partners = res.getString("partners");

                new Handler(Looper.getMainLooper()).post(() -> {
                    idOfSpeciality.setText(String.format("Код: %s", idSpeciality));
                    nameOfSpeciality.setText(nameSpeciality);
                    institutOfSpeciality.setText(institutSpeciality);

                    if(facultSpeciality == null) mainLayout.removeView(facultOfSpeciality);
                    else facultOfSpeciality.setText(facultSpeciality);

                    fioCuratorOfSpeciality.setText(String.format("Куратор: %s", fioCuratorSpeciality));
                    phoneOfSpeciality.setText(String.format("Номер для связи: %s", phoneSpeciality));
                    studyingTimeOfSpeciality.setText(String.format("%s %s", studyingTimeSpeciality, Integer.parseInt(studyingTimeSpeciality) < 5 ? "года" : "лет"));
                    typeOfStudyOfSpeciality.setText(typeOfStudySpeciality);
                    budgetOfSpeciality.setText(budgetSpeciality);
                    payPerYearOfSpeciality.setText(payPerYearSpeciality.length() == 5? new StringBuilder(payPerYearSpeciality).insert(2, ' ').toString():
                                                                                       new StringBuilder(payPerYearSpeciality).insert(3, ' ').toString());
                    payOfSpeciality.setText(paySpeciality);
                    descriptionOfSpeciality.setText(descriptionSpeciality);
                });
                res.close();
                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            Connection connection = new Database().connect();
            try {
                PreparedStatement statement = connection.prepareStatement("select \n" +
                        "\t(select name from exams where id=id_exam) as exam,\n" +
                        "\tmin_points\n" +
                        "\tfrom speciality_exams where id_spec=? order by id_exam");
                Log.e("", id);
                statement.setString(1, id);
                ResultSet res = statement.executeQuery();

                exams = new ArrayList<>();

                while(res.next())
                    exams.add(asList(res.getString("exam"), res.getString("min_points")));

                statement.close();
                connection.close();
                res.close();
                new Handler(Looper.getMainLooper()).post(() -> {
                    for(int i=0; i<exams.size(); i++){
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View rowView = inflater.inflate(R.layout.field_for_min_exam, null);
                        TextView text = rowView.findViewById(R.id.name_exam);
                        TextView points = rowView.findViewById(R.id.min_points_exam);
                        text.setText(exams.get(i).get(0));
                        points.setText(exams.get(i).get(1));
                        layoutOfExamsForSpeciality.addView(rowView);
                    }
                });
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();
    }

    private String getTypeOfStudy() {
        if(getIntent().getStringExtra("type_of_study").equals("Очная")) return "1";
        else if (getIntent().getStringExtra("type_of_study").equals("Заочная")) return "3";
        else return "2";
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}