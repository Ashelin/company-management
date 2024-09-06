package com.ashelin.controller;

import com.ashelin.dto.CompanyRequest;
import com.ashelin.dto.DepartmentDto;
import com.ashelin.dto.SearchCompany;
import com.ashelin.model.Company;
import com.ashelin.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCompany() throws Exception {
        CompanyRequest company = new CompanyRequest("test companyName");
        String companyJson = objectMapper.writeValueAsString(company);
        mockMvc.perform(post("/api/company/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(companyJson))
                .andExpect(status().isOk());
        verify(companyService, times(1)).createCompany(company);
    }

    @Test
    void searchCompany() throws Exception {
        SearchCompany searchCompany = new SearchCompany(1L, "test companyName");
        mockMvc.perform(get("/api/company/search")
                        .param("id", searchCompany.getId().toString())
                        .param("companyName", searchCompany.getCompanyName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(companyService, times(1)).searchCompanies(searchCompany);
    }

    @Test
    void updateCompany() throws Exception {
        Long companyId = 1L;
        CompanyRequest updatedCompanyRequest = new CompanyRequest("Updated Company");
        String updatedCompanyJson = objectMapper.writeValueAsString(updatedCompanyRequest);
        mockMvc.perform(put("/api/company/update/{id}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCompanyJson))
                .andExpect(status().isOk());
        verify(companyService, times(1)).updateCompany(eq(companyId), eq(updatedCompanyRequest));
    }

    @Test
    void deleteCompany() throws Exception {
        Company companyToDelete = Company.builder().companyName("Company to delete").build();
        companyToDelete.setId(1L);
        mockMvc.perform(delete("/api/company/delete/{id}", companyToDelete.getId()))
                .andExpect(status().isOk());

        verify(companyService, times(1)).deleteCompany(eq(companyToDelete.getId()));
    }
}