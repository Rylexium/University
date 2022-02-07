package com.example.vkr.activity.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.vkr.R;
import com.example.vkr.utils.ConvertClass;
import com.example.vkr.utils.CorrectText;
import com.example.vkr.utils.HideKeyboardClass;
import com.example.vkr.utils.SelectImageClass;

import java.io.IOException;
import java.util.regex.Pattern;

public class SnillsActivity extends AppCompatActivity {

    private EditText snills;
    private ImageView imageSnills;
    private Button nextButton;
    private Button previousButton;
    private Button buttonMakePhoto;

    private static Bitmap bitmap;
    public static SharedPreferences sharedPreferences;

    public static final String KEY_SNILLS       = "snills";
    public static final String KEY_PHOTO_SNILLS = "photoSnills";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setContentView(R.layout.snills_activity);
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
            LinearLayout ll = findViewById(R.id.snills_layout);
            ll.requestFocus();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents(){

        previousButton.setOnClickListener(view -> onBackPressed());
        nextButton.setOnClickListener(view -> {
            saveLastState();
            startActivity(new Intent(SnillsActivity.this, EducationDocumentActivity.class));
        });
        buttonMakePhoto.setOnClickListener(view -> SelectImageClass.showMenu(this, false));

        snills.addTextChangedListener(new CorrectText(snills, "###-###-### ##"));

        snills.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus && !isCorrectSnills(snills.getText().toString())) snills.setTextColor(Color.RED);
            else snills.setTextColor(ContextCompat.getColor(this, R.color.white));
        });
    }

    private void comebackAfterOnBackPressed(){
        String restoredText = sharedPreferences.getString(KEY_SNILLS, null);
        if(!TextUtils.isEmpty(restoredText)){
            snills.setText(restoredText);
        }
        restoredText = sharedPreferences.getString(KEY_PHOTO_SNILLS, null);
        if(!TextUtils.isEmpty(restoredText)) {
            imageSnills.setImageBitmap(ConvertClass.convertStringToBitmap(restoredText));
        }
        snills.setTextColor(!isCorrectSnills(snills.getText().toString())? Color.RED : ContextCompat.getColor(this, R.color.white));
    }

    public static void clearComponents() {
        if(sharedPreferences == null) return;
        bitmap = null;
        sharedPreferences.edit().clear().apply();
    }

    private void saveLastState(){
        sharedPreferences.edit().clear().apply();
        sharedPreferences.edit()
                .putString(KEY_SNILLS, snills.getText().toString())
                .putString(KEY_PHOTO_SNILLS, ConvertClass.convertBitmapToString(bitmap))
                .apply();
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

            if(bitmap != null) imageSnills.setImageBitmap(bitmap);
        }
    }
    public static boolean isCorrectSnills(String text){
        return text.length() == 14 && Pattern.matches("^\\d{3}-\\d{3}-\\d{3} \\d{2}$", text);
    }
    private void initComponents(){
        snills = findViewById(R.id.textbox_snills);
        imageSnills = findViewById(R.id.imageViewSnills);
        imageSnills.setImageBitmap(ConvertClass.decodeSampledBitmapFromResource(getResources(), R.drawable.snills, 100, 100));

        sharedPreferences = getPreferences(MODE_PRIVATE);

        nextButton = findViewById(R.id.button);
        nextButton.setBackground(ConvertClass.convertBitmapToDrawable(getResources(),
                ConvertClass.decodeSampledBitmapFromResource(getResources(), R.drawable.image_next_btn, 100, 100)));
        previousButton = findViewById(R.id.button2);
        previousButton.setBackground(ConvertClass.convertBitmapToDrawable(getResources(),
                ConvertClass.decodeSampledBitmapFromResource(getResources(), R.drawable.image_previous_btn, 100, 100)));
        buttonMakePhoto = findViewById(R.id.button9);
    }
}