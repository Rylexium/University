package com.laba6.page.specificationOfTheme;

import com.laba6.page.models.Theme;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationOfTheme {
    Specification<Theme> value = null;
    public Specification<Theme> caseOf();
}
