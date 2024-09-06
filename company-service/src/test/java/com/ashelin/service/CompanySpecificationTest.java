package com.ashelin.service;

import com.ashelin.model.Company;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CompanySpecificationTest {

    @Test
    void hasCompanyName_shouldReturnConjunctionWhenCompanyNameIsNull() {
        Root<Company> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(criteriaBuilder.conjunction()).thenReturn(predicate);

        Specification<Company> specification = CompanySpecification.hasCompanyName(null);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).conjunction();
        verify(criteriaBuilder, never()).like(any(), anyString());
    }

    @Test
    void hasCompanyName_shouldReturnConjunctionWhenCompanyNameIsEmpty() {
        Root<Company> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(criteriaBuilder.conjunction()).thenReturn(predicate);

        Specification<Company> specification = CompanySpecification.hasCompanyName("");
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).conjunction();
        verify(criteriaBuilder, never()).like(any(), anyString());
    }

    @Test
    void hasCompanyName_shouldReturnLikePredicateWhenCompanyNameIsNotNullOrEmpty() {
        Root<Company> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(criteriaBuilder.like(any(), anyString())).thenReturn(predicate);

        Specification<Company> specification = CompanySpecification.hasCompanyName("Test Company");
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertEquals(predicate, result);
        verify(criteriaBuilder, never()).conjunction();
        verify(criteriaBuilder, times(1)).like(any(), eq("%Test Company%"));
    }

    @Test
    void hasId_shouldReturnConjunctionWhenIdIsNull() {
        Root<Company> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        when(criteriaBuilder.conjunction()).thenReturn(predicate);

        Specification<Company> specification = CompanySpecification.hasId(null);
        Predicate result = specification.toPredicate(root, query, criteriaBuilder);

        assertEquals(predicate, result);
        verify(criteriaBuilder, times(1)).conjunction();
        verify(criteriaBuilder, never()).equal(any(), any());
    }
}