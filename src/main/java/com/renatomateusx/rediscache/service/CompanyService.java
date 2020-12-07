package com.renatomateusx.rediscache.service;

import com.renatomateusx.rediscache.controller.exceptions.EntityNotFoundException;
import com.renatomateusx.rediscache.model.Company;
import com.renatomateusx.rediscache.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    //Toda vez que for cachear, é só utilizar a annotation abaixo.
    @Cacheable(cacheNames= "Company", key="#root.method.name")
    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    //Nem todo mundo precisa ser cacheado ou não deve ser cacheado, então temos:
    @CacheEvict(cacheNames= "Company", allEntries = true)
    public List<Company> listAllCompanies(){
        return companyRepository.findAll();
    }

    //Toda vez que um registro for alterado, poderemos expirar o cache dele,
    // para evitar que o registro antiga ainda venha.
    @CachePut(cacheNames = "Company", key="#company.getIdentifier()")
    public Company update(final Company company) {
        if(company.getIdentifier() == null) {
            throw new EntityNotFoundException("Identifier is empty");
        }
        return companyRepository.save(company);
    }

    //Quando um registro é removido do banco, precisaremos também efetuar a limpeza de cache dele.
    @CacheEvict(cacheNames = "Company", key="#identifier")
    public void delete(final String identifier) {
        if(identifier == null) {
            throw new EntityNotFoundException("Identifier is empty");
        }

        companyRepository.deleteById(identifier);
    }

    @CacheEvict(cacheNames = Company.CACHE_NAME, allEntries = true)
    public Company create(final Company company) {
        return companyRepository.save(company);
    }


}
