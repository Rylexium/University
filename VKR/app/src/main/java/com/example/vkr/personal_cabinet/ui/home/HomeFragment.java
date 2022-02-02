package com.example.vkr.personal_cabinet.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.databinding.FragmentHomeBinding;
import com.example.vkr.support_class.ConvertClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private static String loginString;
    private static String snillsString;
    private static String sexString;
    private static String passportString;
    private static String nationalityString;
    private static String departamentCode;
    private static String dateOfIssingPassport;
    private static String constAddress;
    private static String actualAddress;
    private static String idEducation;
    private static String numberEducation;
    private static String regNumberEducation;
    private static String dateOfIssingEducation;
    private static String dateOfBirthday;

    private static Bitmap bitmapPassport1, bitmapPassport2; //своеобразный кэш картинок
    private static Boolean isDownloadImagesPassport = null;

    private static Bitmap bitmapEducation1, bitmapEducation2; //своеобразный кэш картинок
    private static Boolean isDownloadImagesEducation = null;

    private static HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        homeViewModel =
                new ViewModelProvider(this,  new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        initHomeViewModel();
        initComponents();
        ApplyEvents();
        return binding.getRoot();
    }

    public static HomeViewModel getHomeViewModel(){ return homeViewModel; }

    private void setLogin(String text){
        loginString = text;
        binding.textviewLogin.setText(text);
    }
    private void setSnills(String text){
        snillsString = text;
        binding.textviewSnills.setText(text);
    }
    private void setSex(String text){
        sexString = text;
        binding.textviewSex.setText(text);
    }
    private void setPassport(String text){
        passportString = text;
        binding.textviewPassport.setText(text);
    }
    private void setNationality(String text){
        nationalityString = text;
        binding.textviewNationality.setText(text);
    }
    private void setDepartamentCode(String text){
        departamentCode = text;
        binding.textviewDepartamentCode.setText(text);
    }
    private void setConstAddress(String text){
        constAddress = text;
        binding.textviewConstAddress.setText(text);
    }
    private void setActualAddress(String text){
        actualAddress = text;
        binding.textviewActualAddress.setText(text);
    }
    private void setIdEducation(String text){
        idEducation = text;
        binding.textviewIdEducation.setText(text);
    }
    private void setNumberEducation(String text){
        numberEducation = text;
        binding.textviewNumberEducation.setText(text);
    }
    private void setRegNumberEducation(String text){
        regNumberEducation = text;
        binding.textviewRegNumberEducation.setText(text);
    }
    private void setDateOfIssingEducation(String text){
        dateOfIssingEducation = text;
        binding.textviewDateOfIssingEducation.setText(text);
    }
    private void setDateOfIssingPassport(String text){
        dateOfIssingPassport = text;
        binding.textviewDateOfIssingPassport.setText(text);
    }
    private void setDateOfBirthday(String text){
        dateOfBirthday = text;
        binding.textviewDateOfBirthday.setText(text);
    }

    private void ApplyEvents() {
        binding.buttonGetImagesPassport.setOnClickListener(view -> {
            if(isDownloadImagesPassport == null){
                binding.buttonGetImagesPassport.setEnabled(false);
                new Thread(()->{
                    Connection connect = new Database().connect();
                    try {
                        PreparedStatement statement = connect
                                .prepareStatement("SELECT passport1, passport2 FROM abit_passport where id_abit=?");
                        statement.setLong(1, Long.parseLong(snillsString));
                        ResultSet res = statement.executeQuery();
                        if(res.next()){
                            bitmapPassport1 = ConvertClass.convertStringToBitmap(res.getString("passport1"));
                            bitmapPassport2 = ConvertClass.convertStringToBitmap(res.getString("passport2"));
                        }
                        else{
                            new Handler(Looper.getMainLooper()).post(() -> {
                                Toast.makeText(binding.getRoot().getContext(), "Изображений нет", Toast.LENGTH_SHORT).show();
                            });
                        }
                        res.close();
                        statement.close();
                        connect.close();
                        new Handler(Looper.getMainLooper()).post(() -> {
                            binding.buttonGetImagesPassport.setEnabled(true); //данные загрузились кнопка включена
                            setImages(bitmapPassport1, bitmapPassport2, binding.layoutForPagesPassport);
                            isDownloadImagesPassport = false; //данные отобразились, поэтому след состояние будет не загружено на форму
                        });
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }).start();
            }
            else {
                if(isDownloadImagesPassport){
                    setImages(bitmapPassport1, bitmapPassport2, binding.layoutForPagesPassport);
                }
                else{
                    while(binding.layoutForPagesPassport.getChildCount() != 1){
                        binding.layoutForPagesPassport.removeViewAt(0);
                    }
                }
                isDownloadImagesPassport = !isDownloadImagesPassport;
            }

        });
        binding.buttonGetImagesEducation.setOnClickListener(view -> {
            if(isDownloadImagesEducation == null){
                binding.buttonGetImagesEducation.setEnabled(false);
                new Thread(()->{
                    Connection connect = new Database().connect();
                    try {
                        PreparedStatement statement = connect
                                .prepareStatement("SELECT education1, education2 FROM abit_education where id_abit=?");
                        statement.setLong(1, Long.parseLong(snillsString));
                        ResultSet res = statement.executeQuery();
                        if(res.next()){
                            bitmapEducation1 = ConvertClass.convertStringToBitmap(res.getString("education1"));
                            bitmapEducation2 = ConvertClass.convertStringToBitmap(res.getString("education2"));
                        }
                        else{
                            new Handler(Looper.getMainLooper()).post(() -> {
                                Toast.makeText(binding.getRoot().getContext(), "Изображений нет", Toast.LENGTH_SHORT).show();
                            });
                        }
                        res.close();
                        statement.close();
                        connect.close();
                        new Handler(Looper.getMainLooper()).post(() -> {
                            binding.buttonGetImagesEducation.setEnabled(true); //данные загрузились кнопка включена
                            setImages(bitmapEducation1, bitmapEducation2, binding.layoutForPagesEducation);
                            isDownloadImagesEducation = false; //данные отобразились, поэтому след состояние будет не загружено на форму
                        });
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }).start();
            }
            else {
                if(isDownloadImagesEducation){
                    setImages(bitmapEducation1, bitmapEducation2, binding.layoutForPagesPassport);
                }
                else{
                    while(binding.layoutForPagesEducation.getChildCount() != 1){
                        binding.layoutForPagesEducation.removeViewAt(0);
                    }
                }
                isDownloadImagesEducation = !isDownloadImagesEducation;
            }
        });
    }

    private void setImages(Bitmap bitmap1, Bitmap bitmap2, LinearLayout linearLayout){
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView1, rowView2;
        if(bitmap1 != null) {
            rowView1 = inflater.inflate(R.layout.field_for_image, null);
            ImageView image1 = rowView1.findViewById(R.id.field_image);
            image1.setImageBitmap(bitmap1);
            image1.setMinimumHeight(1000);
            image1.setMinimumWidth(1000);
            linearLayout.addView(rowView1, 0);
        }
        if(bitmap2 != null) {
            rowView2 = inflater.inflate(R.layout.field_for_image, null);
            ImageView image2 = rowView2.findViewById(R.id.field_image);
            image2.setImageBitmap(bitmap2);
            image2.setMinimumHeight(1000);
            image2.setMinimumWidth(1000);
            linearLayout.addView(rowView2, 1);
        }
    }

    private void initHomeViewModel(){
        homeViewModel.getTextLogin().observe(getViewLifecycleOwner(), this::setLogin);
        homeViewModel.getTextSex().observe(getViewLifecycleOwner(), this::setSex);
        homeViewModel.getTextSnills().observe(getViewLifecycleOwner(), this::setSnills);
        homeViewModel.getTextNationality().observe(getViewLifecycleOwner(), this::setNationality);
        homeViewModel.getTextPassport().observe(getViewLifecycleOwner(), this::setPassport);
        homeViewModel.getDepartamentCode().observe(getViewLifecycleOwner(), this::setDepartamentCode);
        homeViewModel.getDateOfIssingPassport().observe(getViewLifecycleOwner(), this::setDateOfIssingPassport);
        homeViewModel.getConstAddress().observe(getViewLifecycleOwner(), this::setConstAddress);
        homeViewModel.getActualAddress().observe(getViewLifecycleOwner(), this::setActualAddress);
        homeViewModel.getIdEducation().observe(getViewLifecycleOwner(), this::setIdEducation);
        homeViewModel.getNumberEducation().observe(getViewLifecycleOwner(), this::setNumberEducation);
        homeViewModel.getRegNumberEducation().observe(getViewLifecycleOwner(), this::setRegNumberEducation);
        homeViewModel.getDateOfIssingEducation().observe(getViewLifecycleOwner(), this::setDateOfIssingEducation);
        homeViewModel.getDateOfBirthday().observe(getViewLifecycleOwner(), this::setDateOfBirthday);
    }

    private void initComponents() {
        binding.textviewLogin.setText(loginString);
        binding.textviewSnills.setText(snillsString);
        binding.textviewSex.setText(sexString);
        binding.textviewNationality.setText(nationalityString);
        binding.textviewPassport.setText(passportString);
        binding.textviewDepartamentCode.setText(departamentCode);
        binding.textviewDateOfIssingPassport.setText(dateOfIssingPassport);
        binding.textviewConstAddress.setText(constAddress);
        binding.textviewActualAddress.setText(actualAddress);
        binding.textviewIdEducation.setText(idEducation);
        binding.textviewNumberEducation.setText(numberEducation);
        binding.textviewRegNumberEducation.setText(regNumberEducation);
        binding.textviewDateOfIssingEducation.setText(dateOfIssingEducation);
        binding.textviewDateOfBirthday.setText(dateOfBirthday);
        if(isDownloadImagesPassport != null) isDownloadImagesPassport = true;
        if(isDownloadImagesEducation != null) isDownloadImagesEducation = true;
    }
    public static void clearDate() {
        loginString = null;
        snillsString = null;
        sexString = null;
        passportString = null;
        nationalityString = null;
        departamentCode = null;
        dateOfIssingPassport = null;
        constAddress = null;
        actualAddress = null;
        idEducation = null;
        numberEducation = null;
        regNumberEducation = null;
        dateOfIssingEducation = null;
        dateOfBirthday = null;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}