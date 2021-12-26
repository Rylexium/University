package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification implements SpecificationOfTheme {
    Specification<Theme> value = null;
    private Specification<Theme> idStudent(Integer id_student) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id_student"), id_student);
    }
    public StudentSpecification(String value){
        this.value = (value == null) ? null : idStudent(Integer.parseInt(value));
    }
    @Override
    public Specification<Theme> caseOf() {
        return this.value;
    }
}
