package com.example.vkr.personal_cabinet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.authorizationActivity.AuthorizationActivity;
import com.example.vkr.connectDB.Database;
import com.example.vkr.databinding.PersonalCabinetActivityBinding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class PersonalCabinetActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private TextView fio;
    private TextView emailPhone;
    private AppBarConfiguration mAppBarConfiguration;
    private PersonalCabinetActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = PersonalCabinetActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPersonalCabinet.toolbar);
        binding.appBarPersonalCabinet.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_achievement,
                R.id.nav_agreement,
                R.id.nav_education,
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.personal_cabinet, menu);
        return true;
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
                            dialog.dismiss();
                        })
                .setNegativeButton("Нет", (dialog, which) -> {
                    dialog.dismiss();
                    navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                })
                .create()
                .show();
    }

    private void initComponents(){
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.nav_exit).setOnMenuItemClickListener(menuItem -> {
            onBackPressed();
            return true;
        });
        View headerView = navigationView.getHeaderView(0);
        fio = (TextView) headerView.findViewById(R.id.header_textView_FIO);
        emailPhone = (TextView) headerView.findViewById(R.id.header_textView_email_phone);

        new Thread(()->{
            Connection connect = new Database().connect();
            try {
                PreparedStatement statement = connect.prepareStatement("SELECT * FROM abit, users where id_abit = id and login=?");
                statement.setString(1, getIntent().getStringExtra("login"));
                ResultSet res = statement.executeQuery();
                if(res.next()){
                    String resFio = res.getString("family")
                            + " " + res.getString("name")
                            + " " + (res.getString("patronymic") != null ? res.getString("patronymic") : "");
                    String resEmailPhone = "Почта: " + (res.getString("email") != null ? res.getString("email") : "")
                                + '\n' + "Телефон: " + (res.getString("phone") != null ? res.getString("phone") : "");
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
}