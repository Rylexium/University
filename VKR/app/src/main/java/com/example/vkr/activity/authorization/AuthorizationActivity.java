package com.example.vkr.activity.authorization;

import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.utils.HashPass;
import com.example.vkr.utils.HideKeyboardClass;
import com.example.vkr.utils.OpenActivity;
import com.example.vkr.utils.ShowToast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicBoolean;

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
            LinearLayout ll = findViewById(R.id.authorization_layout);
            ll.requestFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        saveLastState();
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
        labelRegistration.setOnClickListener(view -> OpenActivity.openRegistration(this));
        labelQuestions.setOnClickListener(view -> OpenActivity.openPageWithQuestion(this));

        singInBtn.setOnClickListener(view -> {
            if(textBoxLogin.getText().length() == 0 || textBoxPassword.getText().length() == 0) return;
            if(threadConnectToBD == null || !threadConnectToBD.isAlive()) {
                authorization(textBoxLogin.getText().toString(), textBoxPassword.getText().toString());
                saveLastState();
            }
        });
    }

    private void saveLastState(){
        getPreferences(MODE_PRIVATE)
                .edit()
                .putString(KEY_LOGIN, textBoxLogin.getText().toString())
                .apply();
    }

    private void initComponents() {
        textBoxLogin = findViewById(R.id.textbox_login);
        textBoxPassword = findViewById(R.id.textbox_password);
        singInBtn = findViewById(R.id.button_singIn);
        labelQuestions = findViewById(R.id.questions);
        labelRememberPassword = findViewById(R.id.remember_password);
        labelRegistration = findViewById(R.id.registration);
    }

    private void authorization(String login, String password){
        AtomicBoolean isEntry = new AtomicBoolean(false);
        singInBtn.setEnabled(false);
        String previousText = (String) singInBtn.getText();
        singInBtn.setText("Входим...");
        threadConnectToBD = new Thread(() -> { //в другом потоке коннект и проверяем есть ли юзер
            try {
                Connection connection = new Database().connect();
                if(connection == null){
                    new Handler(Looper.getMainLooper()).post(() -> ShowToast.show(this, "Соединение с сервером не установлено"));
                    return;
                }

                PreparedStatement statement = connection.prepareStatement("select login, password, salt1, salt2, id_abit, is_entry, id_education " +
                        "from users, abit where login=? and id_abit=id;");
                statement.setString(1, login);
                ResultSet res = statement.executeQuery();
                if(!res.next()){ //ничего не пришло
                    new Handler(Looper.getMainLooper()).post(() -> ShowToast.show(this, "Неверный логин или пароль"));
                    res.close();
                    statement.close();
                    connection.close();
                    return;
                }

                //солим пароль
                String hashPass = HashPass.getHashSha256(password, res.getString("salt1"), res.getString("salt2"));

                if (res.getString("password").equals(hashPass)) { //что-то пришло
                    if( (res.getString("is_entry") != null && res.getString("is_entry").equals("t"))
                            || Integer.parseInt(res.getString("id_education")) > 4){
                        isEntry.set(true);
                        new Handler(Looper.getMainLooper()).post(() -> {
                            ShowToast.show(this, "Успешно");
                            OpenActivity.openPersonalCabinet(this, login);
                        });
                    }
                    else {
                        String id_abit = res.getString("id_abit");
                        new Handler(Looper.getMainLooper()).post(() -> { //в главном потоке что-то делаю
                            isEntry.set(true);
                            ShowToast.show(this, "Успешно");
                            OpenActivity.openExamsResult(this, id_abit, login);
                        });
                    }
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> ShowToast.show(this, "Неверный логин или пароль"));
                }
                statement.close();
                connection.close();
            }
            catch (Exception e) {
                Log.e("", e.getMessage());
                new Handler(Looper.getMainLooper()).post(() -> ShowToast.show(this, "Что-то с сервером"));
            }
            if(!isEntry.get()) new Handler(Looper.getMainLooper()).post(() -> {
                                    singInBtn.setEnabled(true);
                                    singInBtn.setText(previousText);
                                });
        });
        threadConnectToBD.start();
    }

}