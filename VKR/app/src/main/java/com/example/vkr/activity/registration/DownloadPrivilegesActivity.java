package com.example.vkr.activity.registration;

import static com.example.vkr.activity.authorization.AuthorizationActivity.sha256;

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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.activity.authorization.AuthorizationActivity;
import com.example.vkr.connectDB.Database;
import com.example.vkr.utils.ConvertClass;
import com.example.vkr.utils.EditLinearLayout;
import com.example.vkr.utils.HideKeyboardClass;
import com.example.vkr.utils.SelectImageClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
            String login = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_LOGIN, null);
            String pass = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_PASS, null);
            String pass2 = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_PASS2, null);
            if(!pass.equals(pass2)) {
                Toast.makeText(this, "Пароли разные", Toast.LENGTH_SHORT).show();
                return;
            }
            String phone = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_PHONE, null);
            if(!RegistrationActivity.isCorrectPhone(phone)){
                Toast.makeText(this, "Номер телефона некорректен", Toast.LENGTH_SHORT).show();
                return;
            }

            String email = RegistrationActivity.sharedPreferences.getString(RegistrationActivity.KEY_EMAIL, null);
            if(!RegistrationActivity.isCorrectEmail(email)){
                Toast.makeText(this, "Почта некорректна", Toast.LENGTH_SHORT).show();
                return;
            }



            String family = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_FAMILY, null);
            String name = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_NAME, null);
            String patronymic = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_PATRONYMIC, null);
            String dateIssingBirthday = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_DATE_OF_BIRTH, null);
            String nationality = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_NATIONALITY, null);
            String sex = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_SEX, null);
            String imagePassport1 = Passport1Activity.sharedPreferences.getString(Passport1Activity.KEY_IMAGE_PASSPORT, null);

            String passportSeries = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_PASSPORT_SERIES, null);
            String passportNumber = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_PASSPORT_NUMBER, null);
            String codeUnit = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_CODE_UNIT, null);
            String dateIssuingPassport = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_DATE_ISSUING, null);
            String imagePassport2 = Passport2Activity.sharedPreferences.getString(Passport2Activity.KEY_IMAGE_PASSPORT, null);

            String postIndexReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_POST_INDEX_REG, null);
            String subjectReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_SUBJECT_REG, null);
            String cityReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_CITY_REG, null);
            String residenceStreetReg = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_RESIDENCE_STREET_REG, null);

            String postIndexActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_POST_INDEX_ACTUAL, null);
            String subjectActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_SUBJECT_ACTUAL, null);
            String cityActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_CITY_ACTUAL, null);
            String residenceStreetActual = Passport3Activity.sharedPreferences.getString(Passport3Activity.KEY_RESIDENCE_STREET_ACTUAL, null);

            String snills = SnillsActivity.sharedPreferences.getString(SnillsActivity.KEY_SNILLS, null);
            if(!SnillsActivity.isCorrectSnills(snills)){
                Toast.makeText(this, "СНИЛС некорректен", Toast.LENGTH_SHORT).show();
                return;
            }

            String imageSnills = SnillsActivity.sharedPreferences.getString(SnillsActivity.KEY_PHOTO_SNILLS, null);


            String typeEducationPosition = EducationDocumentActivity.sharedPreferences.getString(EducationDocumentActivity.KEY_TYPE_EDUCATION_POSITION, null);
            String idEducation = EducationDocumentActivity.sharedPreferences.getString(EducationDocumentActivity.KEY_ID_EDUCATION, null);
            String registrationNumber = EducationDocumentActivity.sharedPreferences.getString(EducationDocumentActivity.KEY_REGISTRATION_NUMBER, null);
            String dateOfIssueOfEducation = EducationDocumentActivity.sharedPreferences.getString(EducationDocumentActivity.KEY_DATE_OF_ISSUE_OF_EDUCATION, null);
            String imageEducation1 = EducationDocumentActivity.sharedPreferences.getString(EducationDocumentActivity.KEY_EDUCATION_PICTURE1, null);
            String imageEducation2 = EducationDocumentActivity.sharedPreferences.getString(EducationDocumentActivity.KEY_EDUCATION_PICTURE2, null);

            if(family.equals("") || name.equals("") || patronymic.equals("") || dateIssingBirthday.equals("")
                    || sex.equals("Пол:") || nationality.equals("0") || nationality.equals("-1")
                    || passportSeries.equals("") || passportNumber.equals("") || codeUnit.equals("")
                    || dateIssuingPassport.equals("") || idEducation.equals("") || dateOfIssueOfEducation.equals("")){
                Toast.makeText(this, "Введите данные в пустые поля", Toast.LENGTH_SHORT).show();
                return;
            }


            new Thread(()->{

                Connection connection = new Database().connect();
                try {
                    PreparedStatement statement = connection.prepareStatement("SELECT id, phone, email, passport, number_education, login\n" +
                            "\tFROM abit, users where id_abit=id;");
                    ResultSet res = statement.executeQuery();
                    while(res.next()){
                        if(phone.replace("-", "").equals(res.getString("phone"))){
                            showToast("Такой телефон уже существует");
                            return;
                        }
                        else if(email.equals(res.getString("email"))){
                            showToast("Такой email уже существует");
                            return;
                        }
                        else if(login.equals(res.getString("login"))){
                            showToast("Такой логин уже существует");
                            return;
                        }
                        else if(Long.parseLong(passportNumber + passportSeries.replace(" ", "")) == res.getLong("passport")){
                            showToast("Такой паспорт уже существует");
                            return;
                        }
                        else if(snills.replace(" ", "").replace("-", "").equals(res.getString("id"))){
                            showToast("Такой СНИЛС уже существует");
                            return;
                        }
                        else if(Long.parseLong(idEducation.replace(" ", "")) == res.getLong("number_education")){
                            showToast("Такой номер документа уже существует");
                            return;
                        }
                    }
                    res.close();
                    statement.close();

                    new Thread(()->{
                        try {
                            Connection con1 = new Database().connect();
                            PreparedStatement insertToAbit = con1.prepareStatement("INSERT INTO public.abit(\n" +
                                    "\tid, " + //1
                                    "phone, " + //2
                                    "email, " + //3
                                    "family, name, patronymic, " + //4, 5, 6
                                    "sex, id_nationality, " + // 7, 8
                                    "passport, departament_code, \n" + //9, 10
                                    "\tconst_address, actual_address, " + //11, 12
                                    "id_education, number_education, reg_number_education, \n" + //13, 14, 15
                                    "\tdate_of_issing_passport, date_of_issing_education, date_of_birthday)\n" + //16, 17, 18
                                    "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                            insertToAbit.setLong(1, Long.parseLong(snills.replace(" ", "").replace("-", "")));
                            insertToAbit.setString(2, phone);
                            insertToAbit.setString(3, email);

                            insertToAbit.setString(4, family);
                            insertToAbit.setString(5, name);
                            insertToAbit.setString(6, patronymic);

                            insertToAbit.setBoolean(7, sex.equals("Пол: Мужской"));
                            insertToAbit.setInt(8, Integer.parseInt(nationality));

                            insertToAbit.setLong(9, Long.parseLong(passportNumber + passportSeries.replace(" ", "")));
                            insertToAbit.setInt(10, Integer.parseInt(codeUnit.replace("-", "")));

                            insertToAbit.setString(11, postIndexReg + ", " + subjectReg + ", " + cityReg + ", " + residenceStreetReg);
                            insertToAbit.setString(12, postIndexActual + ", " + subjectActual + ", " + cityActual + ", " + residenceStreetActual);

                            insertToAbit.setInt(13,  Integer.parseInt(typeEducationPosition));
                            insertToAbit.setLong(14, Long.parseLong(idEducation.replace(" ", "")));
                            insertToAbit.setLong(15, Long.parseLong(registrationNumber.equals("")? "0" : registrationNumber.replace("-", "")));

                            insertToAbit.setDate(16, new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(dateIssuingPassport).getTime()));
                            insertToAbit.setDate(17, new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(dateOfIssueOfEducation).getTime()));
                            insertToAbit.setDate(18, new java.sql.Date(new SimpleDateFormat("dd.MM.yyyy").parse(dateIssingBirthday).getTime()));

                            insertToAbit.execute();
                            insertToAbit.close();
                            con1.close();


                            try {
                                Connection con2 = new Database().connect();
                                PreparedStatement insertToUsers = con2.prepareStatement("INSERT INTO public.users(\n" +
                                        "\tlogin, password, salt1, salt2, id_abit, is_entry)\n" +
                                        "\tVALUES (?, ?, ?, ?, ?, false);");
                                insertToUsers.setString(1, login);
                                String salt1 = generateSalt().toString();
                                String salt2 = generateSalt().toString();
                                insertToUsers.setString(2, sha256(sha256(   sha256(pass) + salt1)+ salt2));
                                insertToUsers.setString(3, salt1);
                                insertToUsers.setString(4, salt2);
                                insertToUsers.setLong(5, Long.parseLong(snills.replace("-", "").replace(" ", "")));

                                insertToUsers.execute();
                                insertToUsers.close();
                                con2.close();
                            } catch (SQLException throwables) {
                                Log.e("", throwables.getMessage());
                            }


                            try{
                                Connection con3 = new Database().connect();
                                PreparedStatement insertToAbitPassport = con3.prepareStatement("INSERT INTO public.abit_passport(\n" +
                                        "\tid_abit, passport1, passport2, snills)\n" +
                                        "\tVALUES (?, ?, ?, ?);");
                                insertToAbitPassport.setLong(1, Long.parseLong(snills.replace("-", "").replace(" ", "")));
                                insertToAbitPassport.setString(2, imagePassport1);
                                insertToAbitPassport.setString(3, imagePassport2);
                                insertToAbitPassport.setString(4, imageSnills);

                                insertToAbitPassport.execute();
                                insertToAbitPassport.close();
                                con3.close();
                            }
                            catch (SQLException e){
                                Log.e("", e.getMessage());
                                showToast("Что-то пошло не так, проверьте корректность введённых данных");
                            }

                        } catch (Exception e) {
                            Log.e("", e.getMessage());
                            showToast("Что-то пошло не так, проверьте корректность введённых данных");
                        }
                    }).start();
                    connection.close();
                } catch (SQLException e) {
                    Log.e("", e.getMessage());
                    showToast("Что-то пошло не так, проверьте корректность введённых данных");
                }
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(this, "Регистрация произошла успешна!!!", Toast.LENGTH_SHORT).show();
                    finish();
                    allActivityClear();
                    startActivity(new Intent(this, AuthorizationActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                });
            }).start();

        });

        buttonPrevious.setOnClickListener(view -> onBackPressed());
        buttonAddPrivileges.setOnClickListener(view -> SelectImageClass.showMenu(this, false));
    }

    private void showToast(String text){
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        });
    }

    private static void allActivityClear(){
        RegistrationActivity.clearComponents();;
        SnillsActivity.clearComponents();
        Passport1Activity.clearComponents();
        Passport2Activity.clearComponents();
        Passport3Activity.clearComponents();
        DownloadAchievementsActivity.clearComponents();
        DownloadPrivilegesActivity.clearComponents();
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

        buttonPrevious.setBackground(ConvertClass.convertBitmapToDrawable(getResources(),
                ConvertClass.decodeSampledBitmapFromResource(getResources(), R.drawable.image_previous_btn, 100, 100)));
        buttonNext.setBackground(ConvertClass.convertBitmapToDrawable(getResources(),
                ConvertClass.decodeSampledBitmapFromResource(getResources(), R.drawable.image_next_btn, 100, 100)));

        layoutForImagesPrivileges = findViewById(R.id.layout_for_images_privileges);

        sharedPreferences = getPreferences(MODE_PRIVATE);
    }
}