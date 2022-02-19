package com.example.vkr.personal_cabinet.moreAbout;

import static com.example.vkr.personal_cabinet.PersonalCabinetActivity.specialitysAbit;
import static java.util.Arrays.asList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.personal_cabinet.PersonalCabinetActivity;
import com.example.vkr.utils.OpenActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MoreAboutTheSpecialityActivity extends AppCompatActivity {

    private String idSpec;

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
    private TextView passingScoreOfSpeciality;

    private LinearLayout mainLayout;
    private LinearLayout layoutOfExamsForSpeciality;

    private List<List<String>> exams;

    private LinearLayout competenciesOfSpeciality;
    private LinearLayout professionsOfSpeciality;
    private LinearLayout partnersOfSpeciality;

    private boolean favorite = false;
    public static String typeOfStudy;

    private String competencies, professions, partners;
    private boolean isCompetencies = false, isProfessions = false, isPartners = false;
    private MenuItem favoriteItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_about_the_speciality);
        setSupportActionBar(findViewById(R.id.toolbar_speciality));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initComponents();
        applyEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_speciality, menu);
        favoriteItem = menu.findItem(R.id.add_speciality);
        favoriteItem.setEnabled(false);
        favoriteItem.setIcon(DrawableCompat.wrap(Objects.requireNonNull(favorite ? ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24) :
                                                                               ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))));
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contact_with_developer:
                return OpenActivity.openPageDeveloper(this);
            case R.id.action_faq:
                return OpenActivity.openPageWithQuestion(this);
            case R.id.action_we_on_maps:
                return OpenActivity.openMapsWhereWe(this);
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_speciality:
                ActionMenuItemView it = findViewById(R.id.add_speciality);
                if(favorite){
                    favorite = false;
                    for(int i=0; i<specialitysAbit.size(); ++i) {
                        if (Objects.equals(specialitysAbit.get(i).get("id_spec"), idSpec)
                                && Objects.equals(specialitysAbit.get(i).get("id_abit"), PersonalCabinetActivity.idAbit)
                                && Objects.equals(specialitysAbit.get(i).get("type_of_study"), typeOfStudy)) {
                            specialitysAbit.remove(i);
                            break;
                        }
                    }
                    it.setIcon(DrawableCompat.wrap(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))));
                    new Thread(()->{
                        Connection connection = new Database().connect();
                        try {
                            connection.prepareStatement("DELETE FROM public.abit_spec " +
                                    "WHERE id_abit=" + PersonalCabinetActivity.idAbit + " and id_spec='" + idSpec + "' and type_of_study=" + typeOfStudy).execute();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }).start();
                }
                else if(specialitysAbit.size() < 3) {
                    favorite = true;
                    specialitysAbit.add(new HashMap<String, String>(){
                        {
                            put("id_abit", PersonalCabinetActivity.idAbit);
                            put("id_spec", idSpec);
                            put("type_of_study", typeOfStudy);
                            put("priority", null);
                            put("id_financing", null);
                            put("date_filing", null);
                            put("name_spec", nameOfSpeciality.getText().toString());
                            put("institution", institutOfSpeciality.getText().toString());
                        }});
                    it.setIcon(DrawableCompat.wrap(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))));
                }
                else {
                    Snackbar.make(it, "Вы не можете выбрать больше 3 специальностей...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void applyEvents() {
        competenciesOfSpeciality.setOnClickListener(view-> {
            setTextForTitle(isCompetencies, findViewById(R.id.competencies_of_speciality_info),
                    competencies, view.findViewById(R.id.arrow_downward1));
            isCompetencies = !isCompetencies;
        });

        professionsOfSpeciality.setOnClickListener(view-> {
            setTextForTitle(isProfessions, findViewById(R.id.professions_of_speciality_info),
                    professions, view.findViewById(R.id.arrow_downward2));
            isProfessions = !isProfessions;
        });

        partnersOfSpeciality.setOnClickListener(view-> {
            setTextForTitle(isPartners, findViewById(R.id.partners_of_speciality_info),
                    partners, view.findViewById(R.id.arrow_downward3));
            isPartners = !isPartners;
        });
    }

    private void setTextForTitle(boolean isPressed, LinearLayout linearLayout, String text, ImageView status){
        if(!isPressed){
            LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView=inflater.inflate(R.layout.field_for_questions, null);
            TextView textQuestion = rowView.findViewById(R.id.text_question);
            textQuestion.setText(text);
            linearLayout.addView(rowView);
            status.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24));
        }
        else {
            linearLayout.removeViewAt(linearLayout.getChildCount() - 1);
            status.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_downward_24));
        }
    }

    @SuppressLint("RestrictedApi")
    private void initComponents() {
        idSpec = getIntent().getStringExtra("id");
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
        passingScoreOfSpeciality = findViewById(R.id.passing_score_number);

        mainLayout = findViewById(R.id.layout_more_about_the_speciality);
        layoutOfExamsForSpeciality = findViewById(R.id.layout_of_exams_for_speciality);

        competenciesOfSpeciality = findViewById(R.id.competencies_of_speciality);
        professionsOfSpeciality = findViewById(R.id.professions_of_speciality);
        partnersOfSpeciality = findViewById(R.id.partners_of_speciality);

        typeOfStudy = PersonalCabinetActivity.typeOfStudy.get(getIntent().getStringExtra("type_of_study"));

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
                        "\t(select name from faculties where id=id_facult) as id_facult, \n" +
                        "\tpassing_score " +
                        "\tFROM public.speciality where id='" + idSpec + "' and " +
                        "type_of_study=" + typeOfStudy).executeQuery();
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
                String passingScoreSpeciality = res.getString("passing_score");
                competencies = res.getString("competencies");
                professions = res.getString("professions");
                partners = res.getString("partners");

                new Handler(Looper.getMainLooper()).post(() -> {
                    idOfSpeciality.setText(String.format("Код: %s", idSpeciality));
                    nameOfSpeciality.setText(nameSpeciality);
                    institutOfSpeciality.setText(institutSpeciality);

                    if(facultSpeciality == null) mainLayout.removeView(facultOfSpeciality);
                    else facultOfSpeciality.setText(facultSpeciality);

                    fioCuratorOfSpeciality.setText(String.format("Куратор: %s", fioCuratorSpeciality == null? '-' :  fioCuratorSpeciality));
                    phoneOfSpeciality.setText(String.format("Номер для связи: %s", phoneSpeciality == null? '-': phoneSpeciality));
                    if(studyingTimeSpeciality != null)
                            studyingTimeOfSpeciality.setText(String.format("%s %s", studyingTimeSpeciality, Integer.parseInt(studyingTimeSpeciality) < 5 ? "года" : "лет"));
                    typeOfStudyOfSpeciality.setText(typeOfStudySpeciality);
                    budgetOfSpeciality.setText(budgetSpeciality);

                    if(payPerYearSpeciality != null)
                        payPerYearOfSpeciality.setText(payPerYearSpeciality.length() == 5? new StringBuilder(payPerYearSpeciality).insert(2, ' ').toString():
                                                                                           new StringBuilder(payPerYearSpeciality).insert(3, ' ').toString());
                    payOfSpeciality.setText(paySpeciality);
                    descriptionOfSpeciality.setText(descriptionSpeciality);
                    passingScoreOfSpeciality.setText(passingScoreSpeciality == null? "-" : passingScoreSpeciality);

                    favoriteItem.setEnabled(true);
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
                statement.setString(1, idSpec);
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


        for(int i=0; i<specialitysAbit.size() && !favorite; ++i)
            if(Objects.equals(specialitysAbit.get(i).get("id_spec"), idSpec)
                    && Objects.equals(specialitysAbit.get(i).get("id_abit"), PersonalCabinetActivity.idAbit)
                    && Objects.equals(specialitysAbit.get(i).get("type_of_study"), typeOfStudy))
                favorite = true;
    }

}