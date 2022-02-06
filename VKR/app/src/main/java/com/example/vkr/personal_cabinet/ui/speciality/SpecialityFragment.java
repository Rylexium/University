package com.example.vkr.personal_cabinet.ui.speciality;

import static java.util.Arrays.asList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vkr.R;
import com.example.vkr.connectDB.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecialityFragment extends Fragment {

    private View binding;
    private LinearLayout specialityLayout;
    private ScrollView scrollView;
    private static List<List<String>> speciality;
    private static Integer start = 0;
    private final Integer next = 15;
    private static boolean isBottom = false; // дошли до конца
    private static boolean isAllSpecialityDownload = false;

    private static Integer scrollY = 0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = inflater.inflate(R.layout.fragment_speciality, container, false);
        initComponents();

        return binding.getRootView();
    }

    private void onAddField(String nameSpeciality, String nameInstitut,
                            String nameTypeOfStudy, String valueGeneralCompetition,
                            String valueContract) {
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_for_speciality, null);

        TextView name = rowView.findViewById(R.id.textview_speciality); //02.03.03 МОАИС
        TextView institut = rowView.findViewById(R.id.textview_institut); //Естественнонаучный институт
        TextView typeOfStudy = rowView.findViewById(R.id.textview_type_of_study); //Очная
        TextView generalCompetition = rowView.findViewById(R.id.textview_general_competition); // 253 / 25
        TextView contract = rowView.findViewById(R.id.textview_сontract); // 123 / 75

        name.setText(nameSpeciality);
        institut.setText(nameInstitut);
        typeOfStudy.setText(nameTypeOfStudy);
        generalCompetition.setText(valueGeneralCompetition);
        contract.setText(valueContract);


        specialityLayout.addView(rowView);

    }

    private void downloadSpeciality(){
        if(isAllSpecialityDownload) return;
        new Thread(()->{
            Connection connect = new Database().connect();
            try {
                ResultSet res = connect.prepareStatement("SELECT id, \n" +
                        "\t(select name from type_of_study where id=type_of_study) as type_of_study, \n" +
                        "\tname, budget, pay, \n" +
                        "\t(select name from institutions where id=id_institut) as id_institut\n" +
                        "\tFROM speciality where (id like '%.03.%') or (id like '%.05.%') order by id\n" +
                        " OFFSET " + start  +" ROWS FETCH NEXT " + next + " ROWS ONLY;").executeQuery();

                int previousSize = speciality.size();
                while (res.next()){
                    speciality.add(asList(res.getString("id") + " " + res.getString("name"),
                            res.getString("id_institut"),
                            res.getString("type_of_study"),
                            res.getString("budget"),
                            res.getString("pay")));
                }

                if(previousSize == speciality.size()) isAllSpecialityDownload = true; //размер не изменился, значит всё загружено

                res.close();
                connect.close();

                new Handler(Looper.getMainLooper()).post(() -> {
                    for(int i = start; i < speciality.size(); i++)
                        onAddField(speciality.get(i).get(0), speciality.get(i).get(1), speciality.get(i).get(2), speciality.get(i).get(3), speciality.get(i).get(4));

                    start += next;
                });
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }).start();
    }


    private void initComponents() {
        scrollView = binding.findViewById(R.id.scrollview_speciality_fragment);
        specialityLayout = binding.findViewById(R.id.layout_speciality);

        if(speciality == null) { //первый раз зашли сюда
            speciality = new ArrayList<>(); //инициализация
            downloadSpeciality(); //подгружаем
        }
        else {
            for(int i = 0; i < speciality.size(); i++) //уже были данные
                onAddField(speciality.get(i).get(0), speciality.get(i).get(1), speciality.get(i).get(2), speciality.get(i).get(3), speciality.get(i).get(4));
        }
        scrollView.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            int bottom = (scrollView.getChildAt(scrollView.getChildCount() - 1)).getHeight() - scrollView.getHeight() - scrollY;
            if (bottom == 0 && !isBottom && !isAllSpecialityDownload) {
                Toast.makeText(binding.getContext(), "Подождите, идёт загрузка...", Toast.LENGTH_LONG).show();
                isBottom = true;
                downloadSpeciality();
            } else{
                isBottom = false;
                SpecialityFragment.scrollY = scrollView.getScrollY();
            }
        });

        scrollView.post(() -> scrollView.scrollTo(0, scrollY)); //возвращаем предыдущую позицию в scrollView
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}