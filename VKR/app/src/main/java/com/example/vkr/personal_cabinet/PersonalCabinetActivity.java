package com.example.vkr.personal_cabinet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.activity.authorization.AuthorizationActivity;
import com.example.vkr.activity.authorization.QuestionsActivity;
import com.example.vkr.connectDB.Database;
import com.example.vkr.databinding.PersonalCabinetActivityBinding;
import com.example.vkr.personal_cabinet.moreAbout.MoreAboutTheInstitutActivity;
import com.example.vkr.personal_cabinet.ui.home.HomeFragment;
import com.example.vkr.personal_cabinet.ui.result_egu.ResultEguFragment;
import com.example.vkr.personal_cabinet.ui.speciality.SpecialityFragment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalCabinetActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private TextView fio;
    private TextView emailPhone;
    private AppBarConfiguration mAppBarConfiguration;
    private PersonalCabinetActivityBinding binding;

    public static String filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PersonalCabinetActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPersonalCabinet.toolbar);
        binding.appBarPersonalCabinet.fab.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW)
                                                                                .setData(Uri.parse("https://vk.com/moais_samara"))));
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_achievement,
                R.id.nav_agreement,
                R.id.nav_egu,
                R.id.nav_speciality,
                R.id.nav_statement,
                R.id.nav_exit) //перечислить layout
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_personal_cabinet);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        initComponents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.personal_cabinet, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contact_with_developer:
                startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://vk.com/rylexium")));
                return true;
            case R.id.action_faq:
                startActivity(new Intent(this, QuestionsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_personal_cabinet);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this).setTitle("Выйти")
                .setMessage("Вы действительно хотите выйти?")
                .setPositiveButton("Да",
                        (dialog, which) -> {
                            startActivity(new Intent(this, AuthorizationActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            HomeFragment.clearDate();
                            ResultEguFragment.clearTable();
                            SpecialityFragment.clearTable();
                            dialog.dismiss();
                        })
                .setNegativeButton("Нет", (dialog, which) -> dialog.dismiss())
                .setOnDismissListener(dialogInterface -> navigationView.getMenu().findItem(R.id.nav_home).setChecked(true))
                .create()
                .show();
    }

    private void initComponents(){
        navigationView = findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_exit).setOnMenuItemClickListener(menuItem -> {
            onBackPressed();
            return true;
        });
        View headerView = navigationView.getHeaderView(0);
        fio = headerView.findViewById(R.id.header_textView_FIO);
        emailPhone = headerView.findViewById(R.id.header_textView_email_phone);
        new Thread(()->{
            Connection connect = new Database().connect();
            try {
                PreparedStatement statement = connect
                        .prepareStatement("SELECT id, phone, email, \n" +
                                            "family, name, patronymic, \n" +
                                            "(select name from sex where id=abit.sex) as sex, \n" +
                                            "(select name from nationality where id=abit.id_nationality) as nationality, \n" +
                                            "passport, departament_code, date_of_issing_passport, const_address, actual_address, \n" +
                                            "(select name from education where id=id_education) as education, \n" +
                                            "id_education, \n" +
                                            "number_education, reg_number_education, date_of_issing_education, date_of_birthday\n" +
                                            "\tFROM abit, users where id_abit=id and login=?");
                statement.setString(1, getIntent().getStringExtra("login"));
                ResultSet res = statement.executeQuery();


                if(res.next()){
                    String resFio = res.getString("family")
                            + " " + res.getString("name")
                            + " " + (res.getString("patronymic") != null ? res.getString("patronymic") : "");
                    String resEmailPhone = "Почта: " + (res.getString("email") != null ? res.getString("email") : "")
                                + '\n' + "Телефон: " + (res.getString("phone") != null ? doNicePhone(res.getString("phone")) : "");

                    sendDataToHomeFragment(getIntent().getStringExtra("login"),
                                                        res.getString("id"),
                                                        res.getString("sex"),
                                                        res.getString("nationality"),
                                                        res.getString("passport"),
                                                        res.getString("departament_code"),
                                                        res.getString("date_of_issing_education"),
                                                        res.getString("date_of_issing_passport"),
                                                        res.getString("const_address"),
                                                        res.getString("actual_address"),
                                                        res.getString("education"),
                                                        res.getString("number_education"),
                                                        res.getString("reg_number_education"),
                                                        res.getString("date_of_birthday"));
                    sendIdAbitToResultEguFragment(res.getString("id"));

                    switch (Integer.parseInt(res.getString("id_education"))){
                        case 1:
                        case 2:
                            filter = "id like '%.03.%' or id like '%.05.%'";
                            break;
                        case 5:
                        case 6:
                            filter = "id like '%.04.%'";
                            break;
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            filter = "id like '%.06.%'";
                            break;
                    }


                    new Handler(Looper.getMainLooper()).post(() -> {
                                fio.setText(resFio);
                                emailPhone.setText(resEmailPhone);
                            });
                }
                res.close();
                statement.close();
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();
    }

    private void sendDataToHomeFragment(String login, String snills, String sex, String nationality,
                                        String passport, String departament_code, String date_of_issing_education,
                                        String date_of_issing_passport, String const_address, String actual_address,
                                        String id_education, String number_education, String reg_number_education,
                                        String date_of_birthday) {
        HomeFragment.getHomeViewModel().postTextLogin(login == null? "-" : login);
        HomeFragment.getHomeViewModel().postTextSnills(snills == null? "-" : snills);
        HomeFragment.getHomeViewModel().postTextSex(sex == null? "-" : sex);
        HomeFragment.getHomeViewModel().postTextNationality(nationality == null? "-" : nationality);
        HomeFragment.getHomeViewModel().postTextPassport(passport == null? "-" : passport);
        HomeFragment.getHomeViewModel().postDepartamentCode(departament_code == null? "-" : departament_code);
        HomeFragment.getHomeViewModel().postDateOfIssingEducation(date_of_issing_education == null? "-" : date_of_issing_education);
        HomeFragment.getHomeViewModel().postDateOfIssingPassport(date_of_issing_passport == null? "-" : date_of_issing_passport);
        HomeFragment.getHomeViewModel().postConstAddress(const_address == null? "-" : const_address);
        HomeFragment.getHomeViewModel().postActualAddress(actual_address == null? "-" : actual_address);
        HomeFragment.getHomeViewModel().postIdEducation(id_education == null? "-" : id_education);
        HomeFragment.getHomeViewModel().postNumberEducation(number_education == null? "-" : number_education);
        HomeFragment.getHomeViewModel().postRegNumberEducation(reg_number_education == null ? "-" : reg_number_education);
        HomeFragment.getHomeViewModel().postDateOfBirthday(date_of_birthday == null? "-" : date_of_birthday);

    }

    private void sendIdAbitToResultEguFragment(String snills){
        ResultEguFragment.setIdAbit(snills);
    }


    private StringBuilder doNicePhone(String phone){ // 89371727345 -> 8 (937) 17-27-345
        StringBuilder res = new StringBuilder();
        for(int i = 0; i<phone.length(); ++i){
            if (i == 1) res.append(" (");
            else if (i == 4) res.append(") ");
            else if (i == 6 || i == 8) res.append("-");
            res.append(phone.charAt(i));
        }
        return res;
    }
}