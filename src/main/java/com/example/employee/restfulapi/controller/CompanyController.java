package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/companies")
public class CompanyController {
    //在此处完成Company API
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Company findCompanyById(@PathVariable long id) {
        return companyRepository.findById(id);
    }

    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> getEmployeesByCompanyId(@PathVariable long id) {
        return companyRepository.getEmployeesByCompanyId(id);
    }

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Company> findByPage(@PathVariable int page, @PathVariable int pageSize) {
        return companyRepository.findAll(new PageRequest(page, pageSize));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company saveCompany(Company company) throws Exception {
        if (company.getCompanyName() == null || company.getEmployeesNumber() == null) {
            throw new Exception("Company Not Find!");
        }
        return companyRepository.save(company);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Company updateCompany(@PathVariable long id, @ModelAttribute Company company) throws Exception {
        if (findCompanyById(id) == null) {
            throw new Exception("Company Not Find!");
        }
        companyRepository.updateCompany(id, company.getCompanyName(), company.getEmployeesNumber());
        return company;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteCompany(@PathVariable long id) throws Exception{

        if (companyRepository.findById(id)==null) {
            throw new Exception("Company Not Find!");
        }
        companyRepository.deleteById(id);
        return true;
    }


}
