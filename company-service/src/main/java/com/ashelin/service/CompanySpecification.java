package com.ashelin.service;

import com.ashelin.model.Company;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {

    public static Specification<Company> hasCompanyName(String companyName) {
        return (root, query, criteriaBuilder) ->
                companyName == null || companyName.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("companyName"), "%" + companyName + "%");
    }

    public static Specification<Company> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("id"), id);
    }
}