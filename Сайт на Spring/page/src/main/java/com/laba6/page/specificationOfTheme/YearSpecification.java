package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class YearSpecification implements SpecificationOfTheme {
    Specification<Theme> value = null;
    private Specification<Theme> idYear(List<Integer> year) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("year")).value(year);
    }
    public YearSpecification(String value){
        this.value = value == null ? null : idYear(Arrays.stream(value.split(", "))
                .map(Integer::parseInt).collect(Collectors.toList()));
    }
    @Override
    public Specification<Theme> caseOf() {
        return this.value;
    }
}
