package com.example.vkr.registrationActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.example.vkr.R;
import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.support_class.SelectImageClass;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class Passport3Activity extends AppCompatActivity {

    private AutoCompleteTextView postIndexReg;
    private AutoCompleteTextView subjectReg;
    private AutoCompleteTextView cityReg;
    private AutoCompleteTextView residenceStreetReg;

    private AutoCompleteTextView postIndexActual;
    private AutoCompleteTextView subjectActual;
    private AutoCompleteTextView cityActual;
    private AutoCompleteTextView residenceStreetActual;

    private ImageView imagePassport3;

    private String[] listOfSubject;

    private static Bitmap bitmap;
    public  static SharedPreferences sharedPreferences;
    private static boolean isYes = false;

    public final static String KEY_POST_INDEX_REG        = "postIndexReg";
    public final static String KEY_SUBJECT_REG           = "subjectReg";
    public final static String KEY_CITY_REG              = "cityReg";
    public final static String KEY_RESIDENCE_STREET_REG  = "residenceStreetReg";

    public final static String KEY_POST_INDEX_ACTUAL       = "postIndexActual";
    public final static String KEY_SUBJECT_ACTUAL          = "subjectActual";
    public final static String KEY_CITY_ACTUAL             = "cityActual";
    public final static String KEY_RESIDENCE_STREET_ACTUAL = "residenceStreetActual";
    private final static String KEY_IS_YES                 = "isYes";
    public final static String KEY_IMAGE_PASSPORT          = "imagePassport";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passport3_activity);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        initComponents();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

    @SuppressLint("ClickableViewAccessibility")
    private void ApplyEvents() {

        findViewById(R.id.button12).setOnClickListener(view -> {
            saveLastState();
            startActivity(new Intent(Passport3Activity.this, SnillsActivity.class));
        });
        findViewById(R.id.button13).setOnClickListener(view -> onBackPressed());
        findViewById(R.id.button11).setOnClickListener(view -> SelectImageClass.showMenu(this, false));
        postIndexActual.setOnTouchListener((view, motionEvent) -> showYesNoDialog(motionEvent));
        subjectActual.setOnTouchListener((view, motionEvent) -> showYesNoDialog(motionEvent));
        cityActual.setOnTouchListener((view, motionEvent) -> showYesNoDialog(motionEvent));
        residenceStreetActual.setOnTouchListener((view, motionEvent) -> showYesNoDialog(motionEvent));

    }


    @Override
    public void onBackPressed() {
        saveLastState();
        super.onBackPressed();
    }

    private void comebackAfterOnBackPressed() {
        wrapper(KEY_POST_INDEX_REG,       postIndexReg::setText);
        wrapper(KEY_SUBJECT_REG,          subjectReg::setText);
        wrapper(KEY_CITY_REG,             cityReg::setText);
        wrapper(KEY_RESIDENCE_STREET_REG, residenceStreetReg::setText);

        wrapper(KEY_POST_INDEX_ACTUAL,       postIndexActual::setText);
        wrapper(KEY_SUBJECT_ACTUAL,          subjectActual::setText);
        wrapper(KEY_CITY_ACTUAL,             cityActual::setText);
        wrapper(KEY_RESIDENCE_STREET_ACTUAL, residenceStreetActual::setText);

        String restoredText = getPreferences(MODE_PRIVATE).getString(KEY_IMAGE_PASSPORT, null);
        if(!TextUtils.isEmpty(restoredText)) {
            if(restoredText.equals("default")) imagePassport3.setImageResource(R.drawable.passport_registration);
            else imagePassport3.setImageBitmap(ConvertClass.convertStringToBitmap(restoredText));
        }
    }

    private void wrapper (String key, Consumer<String> editText) {
        Optional.ofNullable(sharedPreferences.getString(key, null))
                .ifPresent(editText);
    }

    private boolean showYesNoDialog(MotionEvent motionEvent) {
        //если не первый раз нажимаем на одну это же поля или уже сказали "Да"
        if (motionEvent.getAction() != MotionEvent.ACTION_UP || isYes) return false;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.yes_no_dialog, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                .setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.show();

        dialogView.findViewById(R.id.button_yes).setOnClickListener(view1 ->{
            subjectActual.setText(subjectReg.getText());
            postIndexActual.setText(postIndexReg.getText());
            cityActual.setText(cityReg.getText());
            residenceStreetActual.setText(residenceStreetReg.getText());
            alertDialog.dismiss();
        });
        dialogView.findViewById(R.id.button_no).setOnClickListener(view1 -> alertDialog.dismiss());
        isYes = true;
        return true;
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

            if(bitmap != null) imagePassport3.setImageBitmap(bitmap);
        }
    }
    private void saveLastState(){
        new Thread(()->
                new Handler(Looper.getMainLooper()).post(() -> {
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit().putString(KEY_POST_INDEX_REG, postIndexReg.getText().toString())
                            .putString(KEY_SUBJECT_REG, subjectReg.getText().toString())
                            .putString(KEY_CITY_REG, cityReg.getText().toString())
                            .putString(KEY_RESIDENCE_STREET_REG, residenceStreetReg.getText().toString())
                            .putString(KEY_POST_INDEX_ACTUAL, postIndexActual.getText().toString())
                            .putString(KEY_SUBJECT_ACTUAL, subjectActual.getText().toString())
                            .putString(KEY_CITY_ACTUAL, cityActual.getText().toString())
                            .putString(KEY_RESIDENCE_STREET_ACTUAL, residenceStreetActual.getText().toString())
                            .putString(KEY_IS_YES, String.valueOf(isYes))
                            .putString(KEY_IMAGE_PASSPORT, ConvertClass.convertBitmapToString(bitmap))
                            .apply();
                })).start();
    }
    
    public static void clearComponents() { //в shared resources очищаем поля
        if(sharedPreferences == null) return;
        bitmap = null;
        sharedPreferences.edit().clear().apply();
    }

    private void initComponents() {
        subjectReg = findViewById(R.id.textbox_subject_reg);
        postIndexReg = findViewById(R.id.textbox_post_index_reg);
        cityReg = findViewById(R.id.textbox_city_reg);
        residenceStreetReg = findViewById(R.id.textbox_residence_street_reg);

        subjectActual = findViewById(R.id.textbox_subject_actual);
        postIndexActual = findViewById(R.id.textbox_post_index_actual);
        cityActual = findViewById(R.id.textbox_city_actual);
        residenceStreetActual = findViewById(R.id.textbox_residence_street_actual);

        imagePassport3 = findViewById(R.id.imageViewPassport3);

        sharedPreferences = getPreferences(MODE_PRIVATE);

        listOfSubject = Arrays.stream(getResources().getString(R.string.subject_of_russia).split(";")) //получаем все субъекты
                        .map(e -> e.replaceFirst(" ", ""))  //получаем все субъекты
                        .toArray(String[]::new);                           //убираем пустой символ и преобразуем в лист

        subjectReg.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, listOfSubject));
        subjectActual.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, listOfSubject));
    }
}