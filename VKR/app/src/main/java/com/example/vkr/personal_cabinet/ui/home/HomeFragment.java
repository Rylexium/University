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
    private static String departamentCode;
    private static String dateOfIssingPassport;
    private static String constAddress;
    private static String actualAddress;
    private static String idEducation;
    private static String numberEducation;
    private static String regNumberEducation;
    private static String dateOfIssingEducation;
    private static String dateOfBirthday;


    private static HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        initHomeViewModel();
        initComponents();

        return binding.getRoot();
    }

    public static HomeViewModel getHomeViewModel(){ return homeViewModel; }
    private void setLogin(String text){ loginString = text; }
    private void setSnills(String text){ snillsString = text; }
    private void setSex(String text){ sexString = text; }
    private void setPassport(String text){ passportString = text; }
    private void setNationality(String text){ nationalityString = text; }
    private void setDepartamentCode(String text){ departamentCode = text; }
    private void setConstAddress(String text){ constAddress = text; }
    private void setActualAddress(String text){ actualAddress = text; }
    private void setIdEducation(String text){ idEducation = text; }
    private void setNumberEducation(String text){ numberEducation = text; }
    private void setRegNumberEducation(String text){ regNumberEducation = text; }
    private void setDateOfIssingEducation(String text){ dateOfIssingEducation = text; }
    private void setDateOfIssingPassport(String text){ dateOfIssingPassport = text; }
    private void setDateOfBirthday(String text){ dateOfBirthday = text; }


    private void initHomeViewModel(){
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
        homeViewModel.getDepartamentCode().observe(getViewLifecycleOwner(), s ->{
            binding.textviewDepartamentCode.setText(s);
            setDepartamentCode(s);
        });
        homeViewModel.getDateOfIssingPassport().observe(getViewLifecycleOwner(), s ->{
            binding.textviewDateOfIssingPassport.setText(s);
            setDateOfIssingPassport(s);
        });
        homeViewModel.getConstAddress().observe(getViewLifecycleOwner(), s ->{
            binding.textviewConstAddress.setText(s);
            setConstAddress(s);
        });
        homeViewModel.getActualAddress().observe(getViewLifecycleOwner(), s ->{
            binding.textviewActualAddress.setText(s);
            setActualAddress(s);
        });
        homeViewModel.getIdEducation().observe(getViewLifecycleOwner(), s ->{
            binding.textviewIdEducation.setText(s);
            setIdEducation(s);
        });
        homeViewModel.getNumberEducation().observe(getViewLifecycleOwner(), s ->{
            binding.textviewNumberEducation.setText(s);
            setNumberEducation(s);
        });
        homeViewModel.getRegNumberEducation().observe(getViewLifecycleOwner(), s ->{
            binding.textviewRegNumberEducation.setText(s);
            setRegNumberEducation(s);
        });
        homeViewModel.getDateOfIssingEducation().observe(getViewLifecycleOwner(), s ->{
            binding.textviewDateOfIssingEducation.setText(s);
            setDateOfIssingEducation(s);
        });
        homeViewModel.getDateOfBirthday().observe(getViewLifecycleOwner(), s ->{
            binding.textviewDateOfBirthday.setText(s);
            setDateOfBirthday(s);
        });
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}