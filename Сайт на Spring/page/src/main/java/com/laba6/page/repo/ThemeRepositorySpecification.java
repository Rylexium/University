package com.laba6.page.repo;

import com.laba6.page.models.Theme;
import com.laba6.page.specificationOfTheme.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ThemeRepositorySpecification {
    private final ThemeRepository themeRepository;

    public List<Theme> findAllByWorkAndTeacherAndStudentAndTheme (Integer Work, Integer Teacher,
                                                                  Integer Student, Integer Theme,
                                                                  List<Integer> years, List<Integer> grades){
        List<Specification<Theme>> predicates = new ArrayList<>();
        List<SpecificationOfTheme> specification = new ArrayList<SpecificationOfTheme>(Arrays.asList(
                    new WorkSpecification(Work == null? null: Work.toString()),
                    new TeacherSpecification(Teacher == null ? null : Teacher.toString()),
                    new StudentSpecification(Student == null ? null : Student.toString()),
                    new ThemeSpecification(Theme == null ? null : Theme.toString()),
                    new YearSpecification(years == null? null : years.toString().substring(1, years.toString().length() - 1)),
                    new GradeSpecification(grades == null ? null : grades.toString().substring(1, grades.toString().length() - 1))));
        specification.forEach(elem -> {
            if(Objects.nonNull(elem.caseOf()))
                predicates.add(elem.caseOf());
        });


        return predicates.stream()
                .reduce(Specification::and)
                .map(themeSpecification -> themeRepository.findAll(Specification.where(themeSpecification)))
                .orElse(themeRepository.findAll());
    }
}
