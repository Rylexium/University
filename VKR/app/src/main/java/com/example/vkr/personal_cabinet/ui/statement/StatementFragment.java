package com.example.vkr.personal_cabinet.ui.statement;

import static com.example.vkr.personal_cabinet.PersonalCabinetActivity.specialitysAbit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;
import com.example.vkr.personal_cabinet.PersonalCabinetActivity;
import com.example.vkr.personal_cabinet.moreAbout.MoreAboutTheInstitutActivity;
import com.example.vkr.personal_cabinet.moreAbout.MoreAboutTheSpecialityActivity;
import com.example.vkr.utils.ShowToast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StatementFragment extends Fragment {

    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private View binding;
    private FloatingActionButton fab;
    private static List<String> listFinancing;
    private static Map<String, String> dictStudy;
    private Button buttonSubmitStatement;
    private TextView supportTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = inflater.inflate(R.layout.fragment_statement, container, false);
        initComponents();
        applyEvents();
        buttonSubmitStatement.setEnabled(false);
        return binding.getRootView();
    }


    private void applyEvents(){
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == 0 || (scrollY < oldScrollY && !fab.isShown()))
                fab.show();
            else if (scrollY > oldScrollY && fab.isShown())
                fab.hide();
        });

        buttonSubmitStatement.setOnClickListener(view -> {
            if(linearLayout.getChildCount() == 0 && supportTextView == null){
                supportTextView = new TextView(getActivity());
                supportTextView.setText("Необходимо перейти во вкладку \"Направления подготовки\" и выбрать направления");
                supportTextView.setTypeface(supportTextView.getTypeface(), Typeface.BOLD);
                supportTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
                supportTextView.setGravity(Gravity.CENTER);
                supportTextView.setTextColor(Color.RED);
                linearLayout.addView(supportTextView);
            }
            else if(isValidPriority()) {
                supportTextView = null;
                buttonSubmitStatement.setEnabled(false);
                new Thread(()->{
                    Connection connection = new Database().connect();
                    for(int i=0; i<linearLayout.getChildCount(); ++i){
                        TextView name = linearLayout.getChildAt(i).findViewById(R.id.textview_speciality);
                        TextView typeOfStudy = linearLayout.getChildAt(i).findViewById(R.id.textview_type_of_study);
                        Spinner spinnerPriority = linearLayout.getChildAt(i).findViewById(R.id.spinner_priority);
                        Spinner spinnerFinancing = linearLayout.getChildAt(i).findViewById(R.id.spinner_financing);
                        try {
                            connection.prepareStatement("UPDATE abit_spec " +
                                    " SET priority=" + (spinnerPriority.getSelectedItemId() + 1)
                                    + ", id_financing= " + (spinnerFinancing.getSelectedItemId() + 1)
                                    + ", date_filing='" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + "'" +
                                    " WHERE id_abit=" + PersonalCabinetActivity.idAbit
                                    + " and id_spec='" + name.getText().toString().split(" ")[0] + "'"
                                    + " and type_of_study="+ PersonalCabinetActivity.typeOfStudy.get(typeOfStudy.getText().toString())).execute();
                            specialitysAbit.get(i).put("date_filing", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                            specialitysAbit.get(i).put("id_financing", String.valueOf((spinnerFinancing.getSelectedItemId() + 1)));
                            specialitysAbit.get(i).put("priority", String.valueOf((spinnerPriority.getSelectedItemId() + 1)));

                        } catch (SQLException throwables) {
                            Log.e("", throwables.getMessage());
                        }
                    }
                    new Handler(Looper.getMainLooper()).post(()->{
                        onPause();
                        onResume();
                        buttonSubmitStatement.setEnabled(true);
                    });
                }).start();
                ShowToast.show(getActivity(), "Успешно");
            }
        });
    }

    private void initComponents(){
        scrollView = binding.findViewById(R.id.scrollview_statement_fragment);
        linearLayout = binding.findViewById(R.id.layout_of_statement);
        fab = Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        buttonSubmitStatement = binding.findViewById(R.id.button_submit_statement);
    }

    private boolean isValidPriority(){
        if(linearLayout.getChildCount() == 0) return false;
        try {
            for (int i = 0; i < linearLayout.getChildCount(); ++i) {
                Spinner spinnerPriority = linearLayout.getChildAt(i).findViewById(R.id.spinner_priority);
                spinnerPriority.getSelectedItemId();
                for (int j = i + 1; j < linearLayout.getChildCount(); ++j) {
                    Spinner spinnerPriority2 = linearLayout.getChildAt(j).findViewById(R.id.spinner_priority);
                    if (spinnerPriority.getSelectedItemId() == spinnerPriority2.getSelectedItemId()) {
                        ShowToast.show(getActivity(), "Одинаковых приоритетов быть не может");
                        return false;
                    }
                }
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private void onAddField(String idSpeciality, String nameSpeciality,
                            String nameInstitut, String nameTypeOfStudy,
                            String valueOfDateOfStatement, String valueFinancing, String valuePriority) {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_for_statement, null);

        TextView name = rowView.findViewById(R.id.textview_speciality); //02.03.03 МОАИС
        TextView institut = rowView.findViewById(R.id.textview_institut); //Естественнонаучный институт
        TextView typeOfStudy = rowView.findViewById(R.id.textview_type_of_study); //Очная
        TextView dateOfStatement = rowView.findViewById(R.id.date_of_statement); //22.04.2022

        Spinner spinnerFinancing = rowView.findViewById(R.id.spinner_financing);
        Spinner spinnerPriority = rowView.findViewById(R.id.spinner_priority);

        name.setOnClickListener(view-> {
            name.setEnabled(false);
            new Handler().postDelayed(() -> name.setEnabled(true),2000); //иначе 2-й клик будет доступен и откроется сразу 2 окна
            startActivity(new Intent(binding.getContext(), MoreAboutTheSpecialityActivity.class)
                    .putExtra("id", idSpeciality)
                    .putExtra("type_of_study", nameTypeOfStudy));
        });

        institut.setOnClickListener(view ->{
            institut.setEnabled(false);
            new Handler().postDelayed(() -> institut.setEnabled(true),2000);
            startActivity(new Intent(binding.getContext(), MoreAboutTheInstitutActivity.class)
                    .putExtra("name_institut", nameInstitut)
                    .putExtra("id", PersonalCabinetActivity.instituts.get(nameInstitut)));
        });

        spinnerFinancing.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listFinancing));
        spinnerFinancing.setSelection(valueFinancing!=null ? Integer.parseInt(valueFinancing) - 1: 0);

        spinnerPriority.setSelection(valuePriority != null? Integer.parseInt(valuePriority) - 1 : 0);

        name.setText(String.format("%s %s", idSpeciality, nameSpeciality));
        institut.setText(nameInstitut);
        typeOfStudy.setText(nameTypeOfStudy);
        dateOfStatement.setText(valueOfDateOfStatement == null? "-" : valueOfDateOfStatement);
        linearLayout.addView(rowView);
    }

    @Override
    public void onResume(){
        linearLayout.removeAllViews();
        fillSpeciality();
        super.onResume();
    }

    public static void clearData(){
        if(dictStudy != null) dictStudy.clear();
        dictStudy = null;
        if(listFinancing != null) listFinancing.clear();
        listFinancing = null;
    }

    private void fillSpeciality(){
        new Thread(()->{
            Connection connection = new Database().connect();
            if(dictStudy == null) dictStudy = new HashMap<>();
            if(listFinancing == null) listFinancing = new ArrayList<>();
            try {
                if(dictStudy.size() == 0) {
                    ResultSet res = connection.prepareStatement("select * from type_of_study").executeQuery();
                    while (res.next())
                        dictStudy.put(res.getString("id"), res.getString("name"));
                    res.close();
                }
                if(listFinancing.size() == 0) {
                    ResultSet res1 = connection.prepareStatement("select * from type_of_financing").executeQuery();
                    while (res1.next())
                        listFinancing.add(res1.getString("name"));
                    res1.close();
                }
                connection.close();
                //сортируемо по приоритету
                specialitysAbit.sort(Comparator.comparing(map-> Integer.parseInt(map.get("priority") == null? "0" : map.get("priority"))));
                new Handler(Looper.getMainLooper()).post(()->{
                    for(int i=0; i<specialitysAbit.size(); ++i)
                        onAddField(specialitysAbit.get(i).get("id_spec"), specialitysAbit.get(i).get("name_spec"),
                                specialitysAbit.get(i).get("institution"), dictStudy.get(specialitysAbit.get(i).get("type_of_study")),
                                specialitysAbit.get(i).get("date_filing"), specialitysAbit.get(i).get("id_financing"),
                                specialitysAbit.get(i).get("priority"));
                    buttonSubmitStatement.setEnabled(true);
                });
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}