package com.renatomateusx.rediscache;

import com.renatomateusx.rediscache.model.Company;
import com.renatomateusx.rediscache.repository.CompanyRepository;
import com.renatomateusx.rediscache.service.CompanyService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyService subject;

    @Test
    public void WhenCompanyIsCreated(){
        Company comp = new Company();
        comp.setName("Azure");
        comp.setIdentifier(UUID.randomUUID().toString());
        Mockito.when(companyRepository.save(comp)).thenReturn(comp);
        subject.create(comp);
        Assertions.assertNotNull(comp);
    }
}
