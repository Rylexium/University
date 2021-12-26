package com.laba6.page.controllers;

import com.laba6.page.models.Theme;
import com.laba6.page.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final TeacherRepository  teacherRepository;
    private final StudentRepository  studentRepository;
    private final WorkRepository     workRepository;
    private final YearRepository     yearRepository;
    private final ThemeRepository    themeRepository;
    private final ThemeRepositorySpecification themeRepositorySpecification;
    private final Key_wordRepository key_wordRepository;

    @RequestMapping(value= {"/",
                            "/{valueOfWork}/{valueOfTeacher}/{valueOfStudent}/{valuesOfKeyWords}/{valueOfTheme}/{valuesOfGrade}/{valuesOfYear}"},
                            method = {RequestMethod.GET})
    public String home(@RequestParam(value = "valueOfWork", defaultValue = "null")      String valueOfWork,
                       @RequestParam(value = "valueOfTeacher", defaultValue = "null")   String valueOfTeacher,
                       @RequestParam(value = "valueOfStudent", defaultValue = "null")   String valueOfStudent,
                       @RequestParam(value = "valuesOfKeyWords", defaultValue = "null") String[] valuesOfKeyWords,
                       @RequestParam(value = "valueOfTheme", defaultValue = "null")     String valueOfTheme,
                       @RequestParam(value = "valuesOfGrade", defaultValue = "null")    String[] valuesOfGrade,
                       @RequestParam(value = "valuesOfYear", defaultValue = "null")     String[] valuesOfYear,
                       Model model) {

        //Передача данных из БД

        model.addAttribute("teachers",  teacherRepository.findAll());
        model.addAttribute("students",  studentRepository.findAll());
        model.addAttribute("works",     workRepository.findAll());
        model.addAttribute("years",     yearRepository.findAll());
        model.addAttribute("key_words", key_wordRepository.findAll());

        List<Theme> result = themeRepositorySpecification.findAllByWorkAndTeacherAndStudentAndTheme(
                    valueOfWork.equals("null") ?                                   null : Integer.parseInt(valueOfWork),
                    valueOfTeacher.equals("null") ?                                null : Integer.parseInt(valueOfTeacher),
                    valueOfStudent.equals("null") ?                                null : Integer.parseInt(valueOfStudent),
                    valueOfTheme.equals("null") ?                                  null : Integer.parseInt(valueOfTheme),
                    valuesOfYear[0].equals("null") || valuesOfYear[0].equals("")?  null : Arrays.stream(valuesOfYear).map(Integer::parseInt).collect(Collectors.toList()),
                    valuesOfGrade[0].equals("null")|| valuesOfGrade[0].equals("")? null : Arrays.stream(valuesOfGrade).map(Integer::parseInt).collect(Collectors.toList()));

        if(!valuesOfKeyWords[0].equals("null") && !valuesOfKeyWords[0].equals("")){ //по ключевым словам причёсываем
            List<List<Theme>> arrOfAllKeyWords = new ArrayList<>(); //здесь весь список кортежей по словам
            Arrays.asList(valuesOfKeyWords).forEach(keyWord -> arrOfAllKeyWords.add(themeRepository.findAllByKeyWord(Integer.parseInt(keyWord)))); //добавляем все кортежи
            List<Theme> resArrOfAllKeyWords = new ArrayList<>(); //Объединение всех кортежей, которые нашли ранее
            arrOfAllKeyWords.forEach(elem -> resArrOfAllKeyWords.addAll(elem));
            result.retainAll(resArrOfAllKeyWords); //ищем пересечение
        }

        model.addAttribute("themes", result);
        model.addAttribute("valueOfWork",      valueOfWork.equals("null") ?                                              0 : valueOfWork);
        model.addAttribute("valueOfTeacher",   valueOfTeacher.equals("null") ?                                           0 : valueOfTeacher);
        model.addAttribute("valueOfStudent",   valueOfStudent.equals("null") ?                                           0 : valueOfStudent);
        model.addAttribute("valuesOfKeyWords", valuesOfKeyWords[0].equals("null")  || valuesOfKeyWords[0].equals("")? null : Arrays.toString(valuesOfKeyWords));
        model.addAttribute("valueOfTheme",     valueOfTheme.equals("null") ?                                             0 : valueOfTheme);
        model.addAttribute("valuesOfGrade",    valuesOfGrade[0].equals("null") ||valuesOfGrade[0].equals("") ?        null : Arrays.toString(valuesOfGrade));
        model.addAttribute("valuesOfYear",     valuesOfYear[0].equals("null") || valuesOfYear[0].equals("")?          null : Arrays.toString(valuesOfYear));

        return "home";
    }

}