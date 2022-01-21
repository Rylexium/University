package com.example.vkr.registrationActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.R;
import com.example.vkr.support_class.CorrectText;

import java.util.Optional;
import java.util.function.Consumer;

public class RegistrationActivity extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private EditText pass2;
    private EditText phone;
    private EditText email;

    private Button nextButton;
    private RadioButton radioButton_isAgree;
    private boolean isAgree = true;

    public static SharedPreferences sharedPreferences;

    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASS  = "pass";
    public static final String KEY_PASS2 = "pass2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.registration_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initComponents();
        comebackAfterOnBackPressed();
        ApplyEvents();
    }

    @Override
    public void onBackPressed(){
        //удалять все данные из других активити
        clearComponents();
        Passport1Activity.clearComponents();
        Passport2Activity.clearComponents();
        Passport3Activity.clearComponents();
        SnillsActivity.clearComponents();
        EducationdocumentActivity.clearComponents();
        DownloadAchievementsActivity.clearComponents();
        DownloadPrivilegesActivity.clearComponents();
        super.onBackPressed();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents(){

        radioButton_isAgree.setOnClickListener(view -> {
            isAgree = !isAgree;
            radioButton_isAgree.setChecked(isAgree);
        });

        nextButton.setOnClickListener(view -> {
            if(!isAgree) return; //нет согласия
            startActivity(new Intent(RegistrationActivity.this, Passport1Activity.class));
            saveLastState();
        });

        phone.addTextChangedListener(new CorrectText(phone, "8-###-##-##-###"));
    }

    private void saveLastState(){
        sharedPreferences.edit().clear().apply();
        sharedPreferences.edit()
                .putString(KEY_PHONE, phone.getText().toString()) //запоминаем введённые данные
                .putString(KEY_EMAIL, email.getText().toString())
                .putString(KEY_LOGIN, login.getText().toString())
                .putString(KEY_PASS,  pass.getText().toString())
                .putString(KEY_PASS2, pass2.getText().toString())
                .apply();
    }

    private void comebackAfterOnBackPressed() {
        wrapper(KEY_PHONE, phone::setText);
        wrapper(KEY_EMAIL, email::setText);
        wrapper(KEY_LOGIN, login::setText);
        wrapper(KEY_PASS,  pass::setText);
        wrapper(KEY_PASS2, pass2::setText);
        radioButton_isAgree.setChecked(isAgree);
    }
    private void wrapper (String key, Consumer<String> editText) {
        Optional.ofNullable(sharedPreferences.getString(key, null))
                .ifPresent(editText);
    }
    public static void clearComponents(){
        sharedPreferences.edit().clear().apply();
    }
    private void initComponents(){
        login = findViewById(R.id.textbox_login_reg);
        pass  = findViewById(R.id.textbox_pass_reg);
        pass2 = findViewById(R.id.textbox_pass2_reg);
        phone = findViewById(R.id.textbox_phone);
        email = findViewById(R.id.textbox_email);

        nextButton = findViewById(R.id.button1);

        radioButton_isAgree = findViewById(R.id.radioButton_isAgree);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        radioButton_isAgree.setChecked(isAgree);
    }
}