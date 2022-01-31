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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.vkr.R;
import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.CorrectText;
import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.support_class.SelectDateClass;
import com.example.vkr.support_class.SelectImageClass;
import com.example.vkr.support_class.EditLinearLayout;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Passport2Activity extends AppCompatActivity {
    private EditText passport_series;
    private EditText date_issuing;
    private EditText passport_number;
    private EditText code_unit;
    private LinearLayout layoutForImagePassport2;

    private static Bitmap bitmap;
    public static SharedPreferences sharedPreferences;

    private Button buttonPrevious;
    private Button buttonNext;
    private Button buttonPhoto;

    public final static String KEY_PASSPORT_SERIES = "passport_series";
    public final static String KEY_DATE_ISSUING    = "date_issuing";
    public final static String KEY_PASSPORT_NUMBER = "passport_number";
    public final static String KEY_CODE_UNIT       = "code_unit";
    public final static String KEY_IMAGE_PASSPORT  = "imagePassport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passport2_activity);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        initComponents();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        comebackAfterOnBackPressed();
        ApplyEvents();
    }

    @Override
    public void onBackPressed() {
        saveLastState();
        super.onBackPressed();
    }

    private void comebackAfterOnBackPressed() {
        wrapper(KEY_PASSPORT_SERIES, passport_series::setText);
        wrapper(KEY_DATE_ISSUING,    date_issuing::setText);
        wrapper(KEY_PASSPORT_NUMBER, passport_number::setText);
        wrapper(KEY_CODE_UNIT,       code_unit::setText);

        String restoredText = getPreferences(MODE_PRIVATE).getString(KEY_IMAGE_PASSPORT, null);
        if(!TextUtils.isEmpty(restoredText)) {
            EditLinearLayout.onAddField(ConvertClass.convertStringToBitmap(restoredText), layoutForImagePassport2, this);
        }


        if(passport_series.getText().length() < 5) passport_series.setTextColor(Color.RED);
        else passport_series.setTextColor(ContextCompat.getColor(this, R.color.white));

        if(passport_number.getText().length() < 6) passport_number.setTextColor(Color.RED);
        else passport_number.setTextColor(ContextCompat.getColor(this, R.color.white));


        if(code_unit.getText().length() < 7) code_unit.setTextColor(Color.RED);
        else code_unit.setTextColor(ContextCompat.getColor(this, R.color.white));


    }

    private void wrapper (String key, Consumer<String> editText) {
        Optional.ofNullable(sharedPreferences.getString(key, null))
                .ifPresent(editText);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
            LinearLayout ll = findViewById(R.id.passport2_layout);
            ll.requestFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents() {

        buttonNext.setOnClickListener(view ->{
            saveLastState();
            startActivity(new Intent(Passport2Activity.this, Passport3Activity.class));
        });
        buttonPrevious.setOnClickListener(view -> onBackPressed());
        buttonPhoto.setOnClickListener(view -> SelectImageClass.showMenu(this, false));

        date_issuing.setOnClickListener(view -> SelectDateClass.showDatePickerDialogForDateIssuing(this, date_issuing));

        passport_series.addTextChangedListener(new CorrectText(passport_series, "## ##"));
        code_unit.addTextChangedListener(new CorrectText(code_unit, "###-###"));


        passport_series.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus && passport_series.getText().length() < 5)
                passport_series.setTextColor(Color.RED);
            else passport_series.setTextColor(ContextCompat.getColor(this, R.color.white));
        });

        passport_number.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus && passport_number.getText().length() < 6)
                passport_number.setTextColor(Color.RED);
            else passport_number.setTextColor(ContextCompat.getColor(this, R.color.white));
        });


        code_unit.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus && code_unit.getText().length() < 7)
                code_unit.setTextColor(Color.RED);
            else code_unit.setTextColor(ContextCompat.getColor(this, R.color.white));
        });

    }

    private void saveLastState(){
        new Thread(()->
                new Handler(Looper.getMainLooper()).post(() -> {
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit()
                            .putString(KEY_PASSPORT_SERIES, passport_series.getText().toString())
                            .putString(KEY_DATE_ISSUING, date_issuing.getText().toString())
                            .putString(KEY_PASSPORT_NUMBER, passport_number.getText().toString())
                            .putString(KEY_CODE_UNIT, code_unit.getText().toString())
                            .putString(KEY_IMAGE_PASSPORT, ConvertClass.convertBitmapToString(bitmap))
                            .apply();
                })).start();
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
            if(bitmap != null){
                if(layoutForImagePassport2.getChildCount() != 0)
                    EditLinearLayout.clearAll(layoutForImagePassport2);
                EditLinearLayout.onAddField(bitmap, layoutForImagePassport2, this);

            }
        }
    }

    public void onDelete(View v) {
        EditLinearLayout.onDelete(v, layoutForImagePassport2);
    }

    public static void clearComponents(){ //в shared resources очищаем поля
        if(sharedPreferences == null) return;
        bitmap = null;
        sharedPreferences.edit().clear().apply();
    }
    private void initComponents() {
        passport_series = findViewById(R.id.textbox_passport_series);
        date_issuing = findViewById(R.id.textbox_date_issuing_of_passport);
        passport_number = findViewById(R.id.textbox_passport_number);
        code_unit = findViewById(R.id.textbox_code_unit);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        buttonPrevious = findViewById(R.id.button7);
        buttonNext = findViewById(R.id.button3);
        buttonPhoto = findViewById(R.id.button10);

        layoutForImagePassport2 = findViewById(R.id.layout_for_image_passport2);
    }
}