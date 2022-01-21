package com.example.vkr.registrationActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import com.example.vkr.R;
import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.support_class.SelectDateClass;
import com.example.vkr.support_class.SelectImageClass;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class Passport1Activity extends AppCompatActivity {
    private EditText  name;
    private EditText  family;
    private EditText  patronymic;
    private EditText  date_of_birth;
    private Switch    sex;
    private Spinner   nationality;
    private ImageView imagePassport1;

    private static Bitmap bitmap;
    public static SharedPreferences sharedPreferences;

    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonMakePhoto;

    public final static String KEY_NAME            = "name";
    public final static String KEY_FAMILY          = "family";
    public final static String KEY_PATRONYMIC      = "patronymic";
    public final static String KEY_SEX             = "sex";
    public final static String KEY_DATE_OF_BIRTH   = "date_of_birth";
    public final static String KEY_NATIONALITY     = "nationality";
    public final static String KEY_IMAGE_PASSPORT  = "imagePassport";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.passport1_activity);
        initComponents();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        comebackAfterOnBackPressed();
        ApplyEvents();
    }


    @Override
    public void onBackPressed(){
        saveLastState();
        super.onBackPressed();
    }

    private void saveLastState(){
        new Thread(()->
                new Handler(Looper.getMainLooper()).post(() -> {
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit()
                            .putString(KEY_NAME, name.getText().toString())
                            .putString(KEY_FAMILY, family.getText().toString())
                            .putString(KEY_PATRONYMIC, patronymic.getText().toString())
                            .putString(KEY_SEX, sex.getText().toString())
                            .putString(KEY_DATE_OF_BIRTH, date_of_birth.getText().toString())
                            .putString(KEY_NATIONALITY, String.valueOf(nationality.getSelectedItemPosition()))
                            .putString(KEY_IMAGE_PASSPORT, ConvertClass.convertBitmapToString(bitmap))
                            .apply();
                })).start();
    }

    private void comebackAfterOnBackPressed(){
        wrapper(KEY_NAME,       name::setText);
        wrapper(KEY_FAMILY,     family::setText);
        wrapper(KEY_PATRONYMIC, patronymic::setText);
        wrapper(KEY_SEX, (str) -> {
            sex.setText(str);
            sex.setChecked(sex.getText().equals("Пол: Мужской"));
        });
        wrapper(KEY_DATE_OF_BIRTH,  date_of_birth::setText);

        String restoredText = getPreferences(MODE_PRIVATE).getString(KEY_IMAGE_PASSPORT, null);
        if(!TextUtils.isEmpty(restoredText)) {
            bitmap = ConvertClass.convertStringToBitmap(restoredText);
            imagePassport1.setImageBitmap(bitmap);
        }
        restoredText = getPreferences(MODE_PRIVATE).getString(KEY_NATIONALITY, null);
        if(!TextUtils.isEmpty(restoredText)) {
            nationality.setSelection(Integer.parseInt(restoredText));
        }
    }

    private void wrapper (String key, Consumer<String> editText) {
        Optional.ofNullable(sharedPreferences.getString(key, null))
                .ifPresent(editText);
    }

    public static void clearComponents(){ //в shared resources очищаем поля
        if(sharedPreferences == null) return;
        bitmap = null;
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents(){
        sex.setOnCheckedChangeListener((buttonView, isChecked) -> sex.setText(sex.isChecked() ? "Пол: Мужской" : "Пол: Женский"));

        buttonPrevious.setOnClickListener(view -> onBackPressed());
        buttonNext.setOnClickListener(view ->{
            saveLastState();
            startActivity(new Intent(Passport1Activity.this, Passport2Activity.class));
        });
        buttonMakePhoto.setOnClickListener(view -> SelectImageClass.showMenu(this, false));
        date_of_birth.setOnClickListener(view -> SelectDateClass.showDatePickerDialogForBirthday(this, date_of_birth));

        nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { //выбран какой
                    TextView text = (TextView) view;
                    Optional.ofNullable(text).ifPresent(e -> text.setTextColor(Color.BLACK));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

            if(bitmap != null) imagePassport1.setImageBitmap(bitmap);
        }
    }

    private void initComponents(){
        name           = findViewById(R.id.textbox_name_reg);
        family         = findViewById(R.id.textbox_family_reg);
        patronymic     = findViewById(R.id.textbox_patronymic_reg);
        sex            = findViewById(R.id.radiobutton_sex);
        date_of_birth  = findViewById(R.id.textbox_date_of_birth);
        nationality = findViewById(R.id.listbox_nationality);
        imagePassport1 = findViewById(R.id.imageViewPassport1);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        buttonPrevious  = findViewById(R.id.button5);
        buttonNext      = findViewById(R.id.button4);
        buttonMakePhoto = findViewById(R.id.button8);


        nationality.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_item,
                Arrays.stream(getResources().getString(R.string.nationality).split(";"))
                        .toArray(String[]::new)){
            @Override
            public boolean isEnabled(int position) {
                // Отключаем первый итем у спиннера
                // Делаем его как hint
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0)// Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                else
                    tv.setTextColor(Color.BLACK);
                return view;
            }
        });
    }
}