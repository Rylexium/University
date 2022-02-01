package com.example.vkr.personal_cabinet.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.vkr.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    private static String loginString;
    private static String snillsString;
    private static String sexString;
    private static String passportString;
    private static String nationalityString;

    public static HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        homeViewModel.getTextLogin().observe(getViewLifecycleOwner(), s -> {
            binding.textviewLogin.setText(s);
            setLogin(s);
        });
        homeViewModel.getTextSex().observe(getViewLifecycleOwner(), s ->{
            binding.textviewSex.setText(s);
            setSex(s);
        });
        homeViewModel.getTextSnills().observe(getViewLifecycleOwner(), s -> {
            binding.textviewSnills.setText(s);
            setSnills(s);
        });
        homeViewModel.getTextNationality().observe(getViewLifecycleOwner(), s ->{
            binding.textviewNationality.setText(s);
            setNationality(s);
        });
        homeViewModel.getTextPassport().observe(getViewLifecycleOwner(), s ->{
            binding.textviewPassport.setText(s);
            setPassport(s);
        });

        binding.textviewLogin.setText(loginString);
        binding.textviewSnills.setText(snillsString);
        binding.textviewSex.setText(sexString);
        binding.textviewNationality.setText(passportString);
        binding.textviewPassport.setText(nationalityString);
        return binding.getRoot();
    }

    public static void setLogin(String text){
        loginString = text;
    }

    public static void setSnills(String text){
        snillsString = text;
    }

    public static void setSex(String text){
        sexString = text;
    }

    public static void setPassport(String text){
        passportString = text;
    }

    public static void setNationality(String text){
        nationalityString = text;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}