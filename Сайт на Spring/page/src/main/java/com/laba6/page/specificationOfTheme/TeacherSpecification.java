package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpecification implements SpecificationOfTheme {
    Specification<Theme> value = null;
    private Specification<Theme> idTeacher(Integer id_teacher) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id_teacher"), id_teacher);
    }
    public TeacherSpecification(String value){
        this.value = value == null ? null : idTeacher(Integer.parseInt(value));
    }
    @Override
    public Specification<Theme> caseOf() {
        return this.value;
    }
}
