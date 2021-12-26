package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GradeSpecification implements SpecificationOfTheme {
    Specification<Theme> value = null;
    private Specification<Theme> idGrade(List<Integer> grade) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("grade")).value(grade);
    }

    public GradeSpecification(String value){
        this.value = (value == null) ? null : idGrade(Arrays.stream(value.split(", "))
                .map(Integer::parseInt).collect(Collectors.toList()));
    }
    @Override
    public Specification<Theme> caseOf() {
        return this.value;
    }
}
