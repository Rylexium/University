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
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.CorrectText;
import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.support_class.MySpinnerAdapter;
import com.example.vkr.support_class.SelectDateClass;
import com.example.vkr.support_class.SelectImageClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class EducationDocumentActivity extends AppCompatActivity {
    private Button nextButton;
    private Button previousButton;
    private Button buttonMakePhoto;
    private Spinner dropDownList;

    private EditText id_education;
    private EditText date_of_issue_of_education;
    private EditText registration_number;

    private ImageView educationPicture1;
    private ImageView educationPicture2;

    private CheckBox withHonors;

    private static Bitmap bitmap1;
    private static Bitmap bitmap2;
    public static SharedPreferences sharedPreferences;
    private static List<String> listRes;

    public static final String KEY_TYPE_EDUCATION_POSITION = "selected_position";
    public static final String KEY_ID_EDUCATION = "id_education";
    public static final String KEY_DATE_OF_ISSUE_OF_EDUCATION = "date_of_issue_of_education";
    public static final String KEY_REGISTRATION_NUMBER = "registration_number";
    public static final String KEY_EDUCATION_PICTURE1 = "educationPicture1";
    public static final String KEY_EDUCATION_PICTURE2 = "educationPicture2";
    public final static String KEY_WITH_HONORS = "with_honors";


    private static int PIC_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.education_document_activity);
        initComponents();
        comebackAfterOnBackPressed();
        ApplyEvents();
    }
    @Override
    public void onBackPressed(){
        saveLastState();
        super.onBackPressed();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            HideKeyboardClass.hideKeyboard(this);
            LinearLayout ll = findViewById(R.id.education_document_layout);
            ll.requestFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents() {
        TextWatcher id_education_listener = new CorrectText(id_education, "###### #######");
        buttonMakePhoto.setOnClickListener(view ->{
            Toast.makeText(getApplicationContext(), "Сфотографируйте или выберите " + PIC_CODE +" страницу документа", Toast.LENGTH_SHORT).show();
            SelectImageClass.showMenu(this, false);
        });

        nextButton.setOnClickListener(view -> {
            saveLastState();
            startActivity(new Intent(this, DownloadAchievementsActivity.class));
        });
        previousButton.setOnClickListener(view -> onBackPressed());

        dropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { //выбран какой
                    TextView text = (TextView) view;
                    registration_number.setEnabled(position != 1);
                    if(position == 1)
                        id_education.removeTextChangedListener(id_education_listener);
                    else
                        id_education.addTextChangedListener(id_education_listener);
                    Optional.ofNullable(text).ifPresent(e -> text.setTextColor(Color.BLACK));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        id_education.addTextChangedListener(id_education_listener);
        registration_number.addTextChangedListener(new CorrectText(registration_number, "##-###-###"));
        date_of_issue_of_education.setOnClickListener(view -> SelectDateClass.showDatePickerDialogForDateIssuing(this, date_of_issue_of_education));

    }

    public static void clearComponents() {
        if(sharedPreferences == null) return;
        bitmap1 = null;
        bitmap2 = null;
        sharedPreferences.edit().clear().apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(PIC_CODE == 1)
                Toast.makeText(getApplicationContext(), "Сфотографируйте или выберите 2 страницу документа", Toast.LENGTH_SHORT).show();
            switch (requestCode) {
                case SelectImageClass.CAMERA:
                    if(PIC_CODE == 1) {
                        bitmap1 = (Bitmap) data.getExtras().get("data");
                        SelectImageClass.showMenu(this, false);
                        PIC_CODE = 2;
                    }
                    else if(PIC_CODE == 2){
                        bitmap2 = (Bitmap) data.getExtras().get("data");
                        PIC_CODE = 1;
                    }
                    break;
                case SelectImageClass.GALLERY:
                    if(PIC_CODE == 1) {
                        try {
                            bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        SelectImageClass.showMenu(this, false);
                        PIC_CODE = 2;
                    }
                    else if(PIC_CODE == 2){
                        try {
                            bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        PIC_CODE = 1;
                    }
                    break;
            }
            if(bitmap1 != null) educationPicture1.setImageBitmap(bitmap1);
            if(bitmap2 != null) educationPicture2.setImageBitmap(bitmap2);
        }
    }

    private void saveLastState(){
        new Thread(()->
                new Handler(Looper.getMainLooper()).post(() -> {
                    sharedPreferences.edit().clear().apply();
                    sharedPreferences.edit()
                            .putString(KEY_TYPE_EDUCATION_POSITION, String.valueOf(dropDownList.getSelectedItemPosition()))
                            .putString(KEY_ID_EDUCATION, id_education.getText().toString())
                            .putString(KEY_DATE_OF_ISSUE_OF_EDUCATION, date_of_issue_of_education.getText().toString())
                            .putString(KEY_REGISTRATION_NUMBER, registration_number.getText().toString())
                            .putString(KEY_EDUCATION_PICTURE1, ConvertClass.convertBitmapToString(bitmap1))
                            .putString(KEY_EDUCATION_PICTURE2, ConvertClass.convertBitmapToString(bitmap2))
                            .putString(KEY_WITH_HONORS, String.valueOf(withHonors.isChecked()))
                            .apply();
                })).start();
    }

    private void comebackAfterOnBackPressed(){
        String restoredText = sharedPreferences.getString(KEY_TYPE_EDUCATION_POSITION, null);
        if(!TextUtils.isEmpty(restoredText)) {
            dropDownList.setSelection(Integer.parseInt(restoredText));
        }
        wrapper(KEY_ID_EDUCATION,               id_education::setText);
        wrapper(KEY_DATE_OF_ISSUE_OF_EDUCATION, date_of_issue_of_education::setText);
        wrapper(KEY_REGISTRATION_NUMBER,        registration_number::setText);
        restoredText = getPreferences(MODE_PRIVATE).getString(KEY_EDUCATION_PICTURE1, null);
        if(!TextUtils.isEmpty(restoredText)) {
            educationPicture1.setImageBitmap(ConvertClass.convertStringToBitmap(restoredText));
        }
        restoredText = sharedPreferences.getString(KEY_EDUCATION_PICTURE2, null);
        if(!TextUtils.isEmpty(restoredText)) {
            educationPicture2.setImageBitmap(ConvertClass.convertStringToBitmap(restoredText));
        }

        restoredText = sharedPreferences.getString(KEY_WITH_HONORS, null);
        if(!TextUtils.isEmpty(restoredText)) {
            withHonors.setChecked(restoredText.equals("true"));
        }

    }

    private void wrapper (String key, Consumer<String> editText) {
        Optional.ofNullable(sharedPreferences.getString(key, null))
                .ifPresent(editText);
    }

    private void initComponents() {
        sharedPreferences = getPreferences(MODE_PRIVATE);

        id_education = findViewById(R.id.textbox_id_education);
        date_of_issue_of_education = findViewById(R.id.textbox_date_of_issue_of_education);
        registration_number = findViewById(R.id.textbox_registration_number);

        educationPicture1 = findViewById(R.id.education_document1);
        educationPicture2 = findViewById(R.id.education_document2);

        nextButton = findViewById(R.id.button16);
        previousButton = findViewById(R.id.button15);
        buttonMakePhoto = findViewById(R.id.button14);
        dropDownList = findViewById(R.id.listbox_documents_of_education);

        withHonors = findViewById(R.id.checkBox_with_honors);


        nextButton.setEnabled(false);
        if(listRes == null) new Thread(()->{
                Connection connection = new Database().connect();
                try {
                    ResultSet res = connection.createStatement().executeQuery("select DISTINCT name from education");
                    listRes = new ArrayList<>();
                    listRes.add("Выберите образование");
                    while(res.next())
                        listRes.add(res.getString("name"));
                    new Handler(Looper.getMainLooper()).post(() -> {
                        dropDownList.setAdapter(new MySpinnerAdapter(this, R.layout.spinner_item, listRes));
                        String restoredText = sharedPreferences.getString(KEY_TYPE_EDUCATION_POSITION, null);
                        if(!TextUtils.isEmpty(restoredText)) {
                            dropDownList.setSelection(Integer.parseInt(restoredText));
                        }
                        nextButton.setEnabled(true);
                    });
                    res.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }).start();
        else{
            dropDownList.setAdapter(new MySpinnerAdapter(this, R.layout.spinner_item, listRes));
            nextButton.setEnabled(true);
        }

    }
}