package com.example.vkr.registrationActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.vkr.support_class.ConvertClass;
import com.example.vkr.support_class.EditLinearLayout;
import com.example.vkr.support_class.HideKeyboardClass;
import com.example.vkr.R;
import com.example.vkr.support_class.SelectImageClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DownloadAchievementsActivity extends AppCompatActivity {
    private Button buttonNext;
    private Button buttonPrevious;
    private Button buttonAddAchievements;
    private LinearLayout layoutForImagesAchievements;

    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_achievements_activity);
        if(getSupportActionBar() != null) getSupportActionBar().hide(); //убираем action bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue_500));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        }
        return super.dispatchTouchEvent(ev);
    }

    private void ApplyEvents(){
        buttonNext.setOnClickListener(view ->{
            saveLastState();
            startActivity(new Intent(this, DownloadPrivilegesActivity.class));
        });
        buttonPrevious.setOnClickListener(view -> onBackPressed());
        buttonAddAchievements.setOnClickListener(view -> SelectImageClass.showMenu(this, false));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
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
                EditLinearLayout.onAddField(bitmap, layoutForImagesAchievements, this);
            }
        }
    }

    private void comebackAfterOnBackPressed(){
        String restoredText = sharedPreferences.getString("bitmap0", null);
        for(int i=1; !TextUtils.isEmpty(restoredText); i++){
            EditLinearLayout.onAddField(ConvertClass.convertStringToBitmap(restoredText), layoutForImagesAchievements, this);
            restoredText = sharedPreferences.getString("bitmap" + i, null);
        }
        
    }

    private void saveLastState(){
        new Thread(()->
                new Handler(Looper.getMainLooper()).post(() -> {
                    sharedPreferences.edit().clear().apply();
                    for (int i = 0; i < layoutForImagesAchievements.getChildCount(); i++) {
                        ImageView v = layoutForImagesAchievements.getChildAt(i).findViewById(R.id.image_edit);
                        sharedPreferences.edit()
                                .putString("bitmap" + i, ConvertClass.convertBitmapToString(((BitmapDrawable) v.getDrawable()).getBitmap()))
                                .apply();
                    }
                })).start();
    }


    public void onDelete(View v) {
        EditLinearLayout.onDelete(v, layoutForImagesAchievements);
    }

    public static void clearComponents(){
        if(sharedPreferences == null) return;
        sharedPreferences.edit().clear().apply();
    }

    private void initComponents(){
        buttonNext = findViewById(R.id.button_achievements_next);
        buttonPrevious = findViewById(R.id.button_achievements_previous);
        buttonAddAchievements = findViewById(R.id.button_add_achievements);

        layoutForImagesAchievements = findViewById(R.id.layout_for_images_achievements);

        sharedPreferences = getPreferences(MODE_PRIVATE);
    }
}