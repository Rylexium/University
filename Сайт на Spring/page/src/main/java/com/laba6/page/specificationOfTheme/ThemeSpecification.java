package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

public class ThemeSpecification implements SpecificationOfTheme {
    Specification<Theme> value = null;
    private Specification<Theme> idTheme(Integer id_theme){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id_theme);
    }
    public ThemeSpecification(String value){
        this.value = value == null? null : idTheme(Integer.parseInt(value));
    }
    @Override
    public Specification<Theme> caseOf() {
        return this.value;
    }
}