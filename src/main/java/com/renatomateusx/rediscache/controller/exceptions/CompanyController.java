package com.renatomateusx.rediscache.controller.exceptions;

import com.renatomateusx.rediscache.model.Company;
import com.renatomateusx.rediscache.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @PostMapping
    public Company create(@RequestBody final Company company) {
        return companyService.create(company);
    }

    @PutMapping
    public Company update(@RequestBody final Company company) {
        return companyService.update(company);
    }

    @DeleteMapping("/{identifier}")
    public void delete(@PathVariable("identifier") final String identifier) {
        companyService.delete(identifier);
    }
}
