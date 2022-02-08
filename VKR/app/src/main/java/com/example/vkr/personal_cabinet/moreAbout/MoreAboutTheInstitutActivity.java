package com.example.vkr.personal_cabinet.moreAbout;

import static java.util.Arrays.asList;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.personal_cabinet.PersonalCabinetActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoreAboutTheInstitutActivity extends AppCompatActivity {
    private int id;
    private TextView nameOfInstitut;
    private TextView directorOfInstitut;
    private TextView contactPhoneOfInstitut;
    private TextView discriptionOfInstitut;
    private LinearLayout layoutSpecialityInfo;
    private LinearLayout layoutSpeciality;
    private List<List<String>> specialitys;
    private boolean isPressed = false;
    private int sizeOfSpeciality = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_about_the_institut);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initComponents();
        applyEvents();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    private void applyEvents(){
        layoutSpeciality.setOnClickListener(view -> {
            if(specialitys == null) {
                layoutSpeciality.setEnabled(false);
                new Thread(() -> {
                    Connection connect = new Database().connect();
                    try {
                        PreparedStatement statement = connect.prepareStatement("select distinct id, name from speciality " +
                                "where id_institut=? and (" + PersonalCabinetActivity.filter + ") order by id");
                        statement.setInt(1, id);
                        ResultSet res = statement.executeQuery();
                        specialitys = new ArrayList<>();

                        while (res.next())
                            specialitys.add(asList(res.getString("id"), res.getString("name")));
                        sizeOfSpeciality = specialitys.size();
                        connect.close();
                        res.close();
                        statement.close();

                        new Handler(Looper.getMainLooper()).post(()->{
                            isPressed = true;
                            fillSpeciality(isPressed, findViewById(R.id.arrow_downward4));
                            layoutSpeciality.setEnabled(true);
                        });

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }).start();
            }
            else {
                fillSpeciality(isPressed, findViewById(R.id.arrow_downward4));
                isPressed = !isPressed;
            }
        });
    }
    private void fillSpeciality(boolean isPress, ImageView status) {
        if(isPress) {
            for (int i = 0; i < specialitys.size(); i++) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.field_for_questions, null);
                TextView text = rowView.findViewById(R.id.text_question);
                text.setText(String.format("%s %s", specialitys.get(i).get(0), specialitys.get(i).get(1)));
                layoutSpecialityInfo.addView(rowView);
            }
            status.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24));

            if(layoutSpecialityInfo != null && layoutSpecialityInfo.getChildCount() > sizeOfSpeciality){ //защита от двойного нажатия
                for(int i = layoutSpecialityInfo.getChildCount() - 1; i >= sizeOfSpeciality; --i)
                    layoutSpecialityInfo.removeViewAt(i);
            }
        }
        else {
            layoutSpecialityInfo.removeAllViews();
            status.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_downward_24));
        }
    }


    private void initComponents() {

        nameOfInstitut = findViewById(R.id.name_of_institut);
        directorOfInstitut = findViewById(R.id.director_of_institut);
        contactPhoneOfInstitut = findViewById(R.id.contact_phone_of_institut);
        discriptionOfInstitut = findViewById(R.id.discription_of_institut);
        layoutSpeciality = findViewById(R.id.layout_of_specialitys);
        layoutSpecialityInfo = findViewById(R.id.layout_of_specialitys_info);

        new Thread(()-> {
            Connection connect = new Database().connect();
            try {
                PreparedStatement statement = connect.prepareStatement("select id, description, \n" +
                                                "(select (family || ' ' || name || ' ' || patronymic) from employees where id=id_director) as director,\n" +
                                                "\tcontact_phone\n" +
                                                "\tfrom institutions where name = ?");
                statement.setString(1, getIntent().getStringExtra("name_institut"));
                ResultSet res = statement.executeQuery();
                if(!res.next()) return;
                String descriptionStr = res.getString("description");
                String directorStr = res.getString("director");
                String phoneStr = res.getString("contact_phone");
                id = Integer.parseInt(res.getString("id"));
                new Handler(Looper.getMainLooper()).post(() -> {
                    nameOfInstitut.setText(getIntent().getStringExtra("name_institut"));
                    discriptionOfInstitut.setText(descriptionStr);
                    contactPhoneOfInstitut.setText(phoneStr);
                    directorOfInstitut.setText(String.format("Директор института: %s", directorStr));
                    switch (id){
                        case 1:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient1));
                            break;
                        case 2:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient2));
                            break;
                        case 3:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient3));
                            break;
                        case 4:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient4));
                            break;
                        case 5:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient5));
                            break;
                        case 6:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient6));
                            break;
                        case 7:
                            findViewById(R.id.more_about_the_institut_main_layout).setBackground(ContextCompat.getDrawable(this, R.drawable.cradient7));
                            break;
                    }
                });

                connect.close();
                statement.close();
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();
    }
}