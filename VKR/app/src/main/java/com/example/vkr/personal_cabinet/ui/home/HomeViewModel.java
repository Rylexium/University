package com.example.vkr.personal_cabinet.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    private final MutableLiveData<String> login;
    private final MutableLiveData<String> sex;
    private final MutableLiveData<String> snills;
    private final MutableLiveData<String> nationality;
    private final MutableLiveData<String> passport;
    private final MutableLiveData<String> departament_code;
    private final MutableLiveData<String> date_of_issing_passport;
    private final MutableLiveData<String> const_address;
    private final MutableLiveData<String> actual_address;
    private final MutableLiveData<String> id_education;
    private final MutableLiveData<String> number_education;
    private final MutableLiveData<String> reg_number_education;
    private final MutableLiveData<String> date_of_issing_education;
    private final MutableLiveData<String> date_of_birthday;

    public HomeViewModel() {
        login = new MutableLiveData<>();
        sex = new MutableLiveData<>();
        snills = new MutableLiveData<>();
        nationality = new MutableLiveData<>();
        passport = new MutableLiveData<>();
        departament_code = new MutableLiveData<>();
        date_of_issing_passport = new MutableLiveData<>();
        const_address = new MutableLiveData<>();
        actual_address = new MutableLiveData<>();
        id_education = new MutableLiveData<>();
        number_education = new MutableLiveData<>();
        reg_number_education = new MutableLiveData<>();
        date_of_issing_education = new MutableLiveData<>();
        date_of_birthday = new MutableLiveData<>();
    }

    public LiveData<String> getTextLogin() { return login; }
    public LiveData<String> getTextSex() { return sex; }
    public LiveData<String> getTextSnills() { return snills; }
    public LiveData<String> getTextNationality() { return nationality; }
    public LiveData<String> getTextPassport() { return passport; }
    public LiveData<String> getDepartamentCode() { return departament_code; };
    public LiveData<String> getDateOfIssingPassport() { return date_of_issing_passport; };
    public LiveData<String> getConstAddress() { return const_address; };
    public LiveData<String> getActualAddress() {return actual_address; };
    public LiveData<String> getIdEducation() { return id_education; };
    public LiveData<String> getNumberEducation() { return number_education; };
    public LiveData<String> getRegNumberEducation() { return reg_number_education; };
    public LiveData<String> getDateOfIssingEducation() { return date_of_issing_education; };
    public LiveData<String> getDateOfBirthday() { return date_of_birthday; };

    public void setTextLogin(String text) { login.setValue(text); }
    public void setTextSex(String text) { sex.setValue(text);}
    public void setTextSnills(String text) { snills.setValue(text); }
    public void setTextNationality(String text) { nationality.setValue(text); }
    public void setTextPassport(String text) {
        StringBuilder textBuilder = new StringBuilder(text);
        textBuilder.insert(2, ' ');
        textBuilder.insert(5, ' ');
        passport.setValue(textBuilder.toString());
    }
    public void setDepartamentCode(String text) {
        StringBuilder textBuilder = new StringBuilder(text);
        textBuilder.insert(3, '-');
        departament_code.setValue(textBuilder.toString());
    }
    public void setDateOfIssingPassport(String text) { date_of_issing_passport.setValue(text);  }
    public void setConstAddress(String text) { const_address.setValue(text);  }
    public void setActualAddress(String text) { actual_address.setValue(text);  }
    public void setIdEducation(String text) {  id_education.setValue(text);  }
    public void setNumberEducation(String text) {  number_education.setValue(text);  }
    public void setRegNumberEducation(String text) {  reg_number_education.setValue(text);  }
    public void setDateOfIssingEducation(String text) {  date_of_issing_education.setValue(text);  }
    public void setDateOfBirthday(String text) {  date_of_birthday.setValue(text);  }


}
