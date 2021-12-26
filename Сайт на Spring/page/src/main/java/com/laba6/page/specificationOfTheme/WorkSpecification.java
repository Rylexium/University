package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

public class WorkSpecification implements SpecificationOfTheme {
    Specification<Theme> value = null;
    private Specification<Theme> idWork(Integer type_work) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type_work"), type_work);
    }
    public WorkSpecification(String value) {
        this.value = value == null ? null : idWork(Integer.parseInt(value));
    }
    @Override
    public Specification<Theme> caseOf() {
        return this.value;
    }
}
