package com.example.vkr.personal_cabinet.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> login;
    private MutableLiveData<String> sex;
    private MutableLiveData<String> snills;
    private MutableLiveData<String> nationality;
    private MutableLiveData<String> passport;

    public HomeViewModel() {
        login = new MutableLiveData<>();
        sex = new MutableLiveData<>();
        snills = new MutableLiveData<>();
        nationality = new MutableLiveData<>();
        passport = new MutableLiveData<>();
    }

    public LiveData<String> getTextLogin() {
        return login;
    }
    public LiveData<String> getTextSex() {
        return sex;
    }
    public LiveData<String> getTextSnills() {
        return snills;
    }
    public LiveData<String> getTextNationality() {
        return nationality;
    }
    public LiveData<String> getTextPassport() {
        return passport;
    }

    public void setTextLogin(String text) {
        login.setValue(text);
    }
    public void setTextSex(String text) {
        sex.setValue(text);
    }
    public void setTextSnills(String text) {
        snills.setValue(text);
    }
    public void setTextNationality(String text) {
        nationality.setValue(text);
    }
    public void setTextPassport(String text) {
        StringBuilder textBuilder = new StringBuilder(text);
        textBuilder.insert(2, ' ');
        textBuilder.insert(5, ' ');
        passport.setValue(textBuilder.toString());
    }
}
