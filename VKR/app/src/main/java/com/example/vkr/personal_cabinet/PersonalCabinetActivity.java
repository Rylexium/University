package com.example.vkr.personal_cabinet;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import com.example.vkr.connectDB.Database;
import com.example.vkr.databinding.PersonalCabinetActivityBinding;
import com.example.vkr.personal_cabinet.ui.home.HomeFragment;
import com.example.vkr.personal_cabinet.ui.result_egu.ResultEguFragment;
import com.example.vkr.personal_cabinet.ui.speciality.SpecialityFragment;
import com.example.vkr.personal_cabinet.ui.statement.StatementFragment;
import com.example.vkr.utils.OpenActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalCabinetActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private TextView fio;
    private TextView emailPhone;

    private AppBarConfiguration mAppBarConfiguration;
    private PersonalCabinetActivityBinding binding;

    public static String idAbit;
    public static String filter;
    public static List<Map<String, String>> specialitysAbit; //кэш
    public static Map<String, String> typeOfStudy;
    public static Map<String, String> instituts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                return OpenActivity.openPageDeveloper(this);
            case R.id.action_faq:
                return OpenActivity.openPageWithQuestion(this);
            case R.id.action_we_on_maps:
                return OpenActivity.openMapsWhereWe(this);
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
                            sendSpeciality();
                            clearData();
                            HomeFragment.clearData();
                            ResultEguFragment.clearTable();
                            SpecialityFragment.clearTable();
                            StatementFragment.clearData();
                            dialog.dismiss();
                        })
                .setNegativeButton("Нет", (dialog, which) -> dialog.dismiss())
                .setOnDismissListener(dialogInterface -> navigationView.getMenu().findItem(R.id.nav_home).setChecked(true))
                .create()
                .show();
    }


    public static void sendSpeciality() {
        if(PersonalCabinetActivity.specialitysAbit == null) return;
        StringBuilder resQuery = new StringBuilder("");
        for(int i=0; i<specialitysAbit.size(); i++)
            resQuery.append("INSERT INTO public.abit_spec(" +
                    " id_abit, id_spec, type_of_study, priority, id_financing, date_filing)" +
                    " VALUES (" + specialitysAbit.get(i).get("id_abit") + ", '"
                            + specialitysAbit.get(i).get("id_spec") + "', "
                            + specialitysAbit.get(i).get("type_of_study") + ", "
                            + specialitysAbit.get(i).get("priority")+ ", "
                            + specialitysAbit.get(i).get("id_financing") + ", null);\n");

        new Thread(()->{
            Connection connection = new Database().connect();
            List<String> arr = Arrays.asList(resQuery.toString().split("\n"));
            try {
                for(int i=0; i<arr.size(); i++){
                    try{
                        connection.prepareStatement(arr.get(i)).execute();
                    } catch (SQLException throwables) {
                        Log.e("", throwables.getMessage());
                    }
                }
                connection.close();
            } catch (SQLException throwables) {
                Log.e("", throwables.getMessage());
            }
        }).start();
    }

    private void clearData(){
        idAbit = null;
        filter = null;
        if(specialitysAbit != null)specialitysAbit.clear();
        specialitysAbit = null;
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
                                            "number_education, reg_number_education, date_of_issing_education, date_of_birthday,\n" +
                                            "(select name from privileges where id=id_privileges) as privileges\n" +
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
                                                        res.getString("date_of_birthday"),
                                                        res.getString("privileges"));
                    idAbit = res.getString("id");

                    int idEducation = Integer.parseInt(res.getString("id_education"));
                    if(idEducation < 5)
                        filter = "id like '%.03.%' or id like '%.05.%'";
                    else if(idEducation < 7)
                        filter = "id like '%.04.%'";
                    else
                        filter = "id like '%.06.%'";


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

        downloadTypeOfStudy();
        downloadSpecialitysAbit();
        downloadInstituts();
    }

    private void sendDataToHomeFragment(String login, String snills, String sex, String nationality,
                                        String passport, String departament_code, String date_of_issing_education,
                                        String date_of_issing_passport, String const_address, String actual_address,
                                        String id_education, String number_education, String reg_number_education,
                                        String date_of_birthday, String privileges) {
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
        HomeFragment.getHomeViewModel().postPrivilege(privileges);

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

    private void downloadSpecialitysAbit(){
        new Thread(()->{
            Connection connection = new Database().connect();
            try {
                ResultSet res = connection.prepareStatement("SELECT id_abit,\n" +
                        "\tid_spec,\n" +
                        "\t(select name from institutions where id=(select id_institut from speciality where id=id_spec and type_of_study=abit_spec.type_of_study)) as institution,\n" +
                        "\t(select name from speciality where id=id_spec and type_of_study=abit_spec.type_of_study) as name_spec,\n" +
                        "\ttype_of_study,\n" +
                        "\tpriority,\n" +
                        "\tid_financing,\n" +
                        "\tdate_filing\n" +
                        "FROM public.abit_spec where id_abit=(select id_abit from users where login='" + getIntent().getStringExtra("login") + "');").executeQuery();
                specialitysAbit = new ArrayList<>();
                while (res.next()) {
                    specialitysAbit.add(new HashMap<String, String>() {
                        {
                            put("id_abit", res.getString("id_abit"));
                            put("id_spec", res.getString("id_spec"));
                            put("type_of_study", res.getString("type_of_study"));
                            put("priority", res.getString("priority"));
                            put("id_financing", res.getString("id_financing"));
                            put("date_filing", res.getString("date_filing"));
                            put("name_spec", res.getString("name_spec"));
                            put("institution", res.getString("institution"));
                        }
                    });
                }
                connection.close();
                res.close();

            } catch (SQLException throwables) {
                Log.e("", throwables.getMessage());
            }
        }).start();
    }

    private void downloadInstituts(){
        new Thread(()->{
            Connection connection = new Database().connect();
            try {
                ResultSet res = connection.prepareStatement("select id, name from institutions").executeQuery();
                instituts = new HashMap<>();
                while (res.next())
                    instituts.put(res.getString("name"), res.getString("id"));
                connection.close();
                res.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();
    }

    private void downloadTypeOfStudy(){
        new Thread(()->{
            Connection connection = new Database().connect();
            try {
                ResultSet res = connection.prepareStatement("select * from type_of_study").executeQuery();
                typeOfStudy = new HashMap<>();
                while (res.next())
                    typeOfStudy.put(res.getString("name"), res.getString("id"));
                connection.close();
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }).start();
    }
}