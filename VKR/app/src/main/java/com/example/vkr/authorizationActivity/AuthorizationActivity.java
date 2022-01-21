package com.example.vkr.authorizationActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.personal_cabinet.ExamsResultActivity;
import com.example.vkr.registrationActivity.RegistrationActivity;
import com.example.vkr.registrationActivity.SnillsActivity;
import com.example.vkr.support_class.HideKeyboardClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorizationActivity extends AppCompatActivity {
    private AutoCompleteTextView textBoxLogin;
    private AutoCompleteTextView textBoxPassword;
    private Button singInBtn;
    private TextView labelQuestions;
    private TextView labelRememberPassword;
    private TextView labelRegistration;

    private final String KEY_LOGIN = "login";

    private Thread threadConnectToBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.authorization_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initComponents();
        comebackAfterOnBackPressed();
        ApplyEvents();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        getPreferences(MODE_PRIVATE)
                .edit()
                .putString(KEY_LOGIN, textBoxLogin.getText().toString())
                .apply();
        super.onBackPressed();
    }

    @Override
    public void onResume(){
        textBoxPassword.setText("");
        super.onResume();
    }

    private void comebackAfterOnBackPressed() {
        String restoredText = getPreferences(MODE_PRIVATE).getString("login", null);
        if (!TextUtils.isEmpty(restoredText)) {
            textBoxLogin.setText(restoredText);
        }
    }

    private void ApplyEvents(){

        labelRememberPassword.setOnClickListener(view -> System.out.println("Типа помогаем восстановить логин или пароль"));
        labelRegistration.setOnClickListener(view -> startActivity(new Intent(this, RegistrationActivity.class)));
        labelQuestions.setOnClickListener(view -> startActivity(new Intent(this, QuestionsActivity.class)));

        singInBtn.setOnClickListener(view -> {
            if(threadConnectToBD == null || !threadConnectToBD.isAlive()) {
                threadConnectToBD = new Thread(() -> { //в другом потоке коннект и проверяем есть ли юзер
                    try {
                        Connection connection = new Database().connect();
                        if(connection == null){
                            new Handler(Looper.getMainLooper()).post(() -> {
                                Toast.makeText(AuthorizationActivity.this, "Соединение с сервером не установлено", Toast.LENGTH_SHORT).show();
                            });
                            return;
                        }
                        //от sql-инъекций
                        PreparedStatement statement = connection.prepareStatement("SELECT login, password FROM users WHERE login=? AND password=?");
                        statement.setString(1, textBoxLogin.getText().toString());
                        statement.setString(2, textBoxPassword.getText().toString());
                        ResultSet res = statement.executeQuery();
                        if (res.next()) { //что-то пришло
                            Log.e("", res.getString("login") + " " + res.getString("password"));
                            new Handler(Looper.getMainLooper()).post(() -> { //в главном потоке что-то делаю
                                Toast.makeText(AuthorizationActivity.this, "Успешно", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, ExamsResultActivity.class));
                            });
                        } else {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                Toast.makeText(AuthorizationActivity.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                            });
                        }
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        new Handler(Looper.getMainLooper()).post(() -> {
                            Toast.makeText(AuthorizationActivity.this, "Что-то с сервером", Toast.LENGTH_SHORT).show();
                        });
                    }
                });
                threadConnectToBD.start();
            }
        });
    }


    private void initComponents() {
        textBoxLogin = findViewById(R.id.textbox_login);
        textBoxPassword = findViewById(R.id.textbox_password);
        singInBtn = findViewById(R.id.button_singIn);
        labelQuestions = findViewById(R.id.questions);
        labelRememberPassword = findViewById(R.id.remember_password);
        labelRegistration = findViewById(R.id.registration);
    }


}