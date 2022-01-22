package com.example.vkr.registrationActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.authorizationActivity.AuthorizationActivity;
import com.example.vkr.connectDB.Database;
import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.EditLinearLayout;
import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.support_class.SelectImageClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DownloadPrivilegesActivity extends AppCompatActivity {
    private Button buttonNext;
    private Button buttonPrevious;
    private Button buttonAddPrivileges;
    private static Bitmap bitmap;

    private LinearLayout layoutForImagesPrivileges;

    public static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_privileges_activity);
        if (getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initComponents();
        comebackAfterOnBackPressed();
        ApplyEvents();
    }

    @Override
    public void onBackPressed () {
        saveLastState();
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent (MotionEvent ev){
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents () {

        buttonNext.setOnClickListener(view -> {
            //вывод данных проверка
            new Thread(this::sendToBdRegistration).start();
            new Thread(this::sendToBdPassport1).start();
            new Thread(this::sendToBdPassport2).start();
            new Thread(this::sendToBdPassport3).start();
            new Thread(this::sendToBdSnills).start();
            new Thread(this::sendToBdEducationDocument).start();

            System.out.println(generateSalt());

            Toast.makeText(this, "Регистрация произошла успешна!!!", Toast.LENGTH_SHORT).show();
            this.finish();
            startActivity(new Intent(this, AuthorizationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        });

        buttonPrevious.setOnClickListener(view -> onBackPressed());
        buttonAddPrivileges.setOnClickListener(view -> SelectImageClass.showMenu(this, false));
    }

    private void sendToBdRegistration(){
        String login = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_LOGIN, null);
        String pass = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_PASS, null);
        String pass2 = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_PASS2, null);
        String email = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_EMAIL, null);
        String phone = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_PHONE, null);

        String[] items = new String[]{phone, email, login, pass, pass2};
        for(String item: items)
            System.out.println(item);
        System.out.println("----------------------------");
    }

    private void sendToBdPassport1(){
        String family = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_FAMILY, null);
        String name = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_NAME, null);
        String patronymic = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_PATRONYMIC, null);
        String date = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_DATE_OF_BIRTH, null);
        String nationality = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_NATIONALITY, null);
        String sex = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_SEX, null);
        String imagePassport1 = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_IMAGE_PASSPORT, null);

        System.out.println(imagePassport1);
        try {
            Connection connection = new Database().connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE public.abit SET image_passport1=? WHERE login=?;");
            statement.setString(2, "rylexium");
            statement.setString(1, imagePassport1);
            statement.execute();
        }
        catch (Exception ingored){}

        String[] items = new String[]{family, name, patronymic, date, nationality, sex};
        for(String item : items)
            System.out.println(item);
        System.out.println("----------------------------");
    }

    private void sendToBdPassport2(){
        String passportSeries = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_PASSPORT_SERIES, null);
        String passportNumber = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_PASSPORT_NUMBER, null);
        String codeUnit = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_CODE_UNIT, null);
        String dateIssuing = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_DATE_ISSUING, null);
        String issuedBy = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_ISSUED_BY, null);
        String imagePassport2 = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_IMAGE_PASSPORT, null);

        String[] items = new String[]{passportSeries, passportNumber, codeUnit, dateIssuing, issuedBy};
        for(String item : items)
            System.out.println(item);
        System.out.println("----------------------------");
    }

    private void sendToBdPassport3(){
        String postIndexReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_POST_INDEX_REG, null);
        String subjectReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_SUBJECT_REG, null);
        String cityReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_CITY_REG, null);
        String residenceStreetReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_RESIDENCE_STREET_REG, null);

        String postIndexActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_POST_INDEX_ACTUAL, null);
        String subjectActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_SUBJECT_ACTUAL, null);
        String cityActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_CITY_ACTUAL, null);
        String residenceStreetActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_RESIDENCE_STREET_ACTUAL, null);

        String[] items = new String[]{postIndexReg, subjectReg, cityReg, residenceStreetReg, postIndexActual, subjectActual, cityActual, residenceStreetActual};
        for(String item : items)
            System.out.println(item);
        System.out.println("----------------------------");
    }

    private void sendToBdSnills(){
        String snills = SnillsActivity.sharedPreferences.getString(SnillsActivity.KEY_SNILLS, null);
        String imageSnills = SnillsActivity.sharedPreferences.getString(SnillsActivity.KEY_PHOTO_SNILLS, null);

        String[] items = new String[]{snills};
        for(String item : items)
            System.out.println(item);

        System.out.println("----------------------------");
    }

    private void sendToBdEducationDocument(){
        String idEducation = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_ID_EDUCATION, null);
        String registrationNumber = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_REGISTRATION_NUMBER, null);
        String typeEducationPosition = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_TYPE_EDUCATION_POSITION, null);
        String dateOfIssueOfEducation = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_DATE_OF_ISSUE_OF_EDUCATION, null);
        String withHonors = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_WITH_HONORS, null);
        String imageEducation1 = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_EDUCATION_PICTURE1, null);
        String imageEducation2 = EducationdocumentActivity.sharedPreferences.getString(EducationdocumentActivity.KEY_EDUCATION_PICTURE2, null);

        String[] items = new String[]{idEducation, registrationNumber, typeEducationPosition, dateOfIssueOfEducation, withHonors};
        for(String item : items)
            System.out.println(item);
    }

    private static StringBuilder generateSalt(){
        String alfavit = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ<,.>/?;:]}[{-=+*!1@2#3$4%5^6&78(9)0";
        StringBuilder res = new StringBuilder();
        for(int i=0; i<32; i++)
            res.append(alfavit.charAt( (int) (Math.random() * alfavit.length()) ));
        return res;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SelectImageClass.CAMERA:
                    bitmap = (Bitmap) data.getExtras().get("data");
                    break;
                case SelectImageClass.GALLERY:
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    } catch (IOException e) {
                        return;
                    }
                    break;
            }
            if (bitmap != null) {
                EditLinearLayout.onAddField(bitmap, layoutForImagesPrivileges, this);
            }
        }
    }

    private void comebackAfterOnBackPressed () {
        String restoredText = getPreferences(MODE_PRIVATE).getString("bitmap0", null);

        for (int i = 1; !TextUtils.isEmpty(restoredText); i++) {
            EditLinearLayout.onAddField(ConvertClass.convertStringToBitmap(restoredText), layoutForImagesPrivileges, this);
            restoredText = getPreferences(MODE_PRIVATE).getString("bitmap" + i, null);
        }

    }

    private void saveLastState(){
        new Thread(()->
                new Handler(Looper.getMainLooper()).post(() -> {
                    sharedPreferences.edit().clear().apply();
                    for (int i = 0; i < layoutForImagesPrivileges.getChildCount(); i++) {
                        ImageView v = layoutForImagesPrivileges.getChildAt(i).findViewById(R.id.image_edit);
                        sharedPreferences.edit()
                                .putString("bitmap" + i, ConvertClass.convertBitmapToString(((BitmapDrawable) v.getDrawable()).getBitmap()))
                                .apply();
                    }
                    sharedPreferences.edit().apply();
                })).start();
    }
    public void onDelete(View v){
        EditLinearLayout.onDelete(v, layoutForImagesPrivileges);
    }

    public static void clearComponents () {
        if (sharedPreferences == null) return;
        sharedPreferences.edit().clear().apply();
    }

    private void initComponents() {
        buttonNext = findViewById(R.id.button_next_privileges);
        buttonPrevious = findViewById(R.id.button_previous_privileges);
        buttonAddPrivileges = findViewById(R.id.button_add_privileges);

        layoutForImagesPrivileges = findViewById(R.id.layout_for_images_privileges);

        sharedPreferences = getPreferences(MODE_PRIVATE);
    }
}