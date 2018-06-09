package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/employees")
public class EmployeeController {
    //在此处完成Employee API
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee findById(@PathVariable long id) {
        return employeeRepository.findById(id);
    }

    @RequestMapping(value = "/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
    public Page<Employee> finByPage(@PathVariable int page,@PathVariable int pageSize) {
        return employeeRepository.findAll(new PageRequest(page, pageSize));
    }

    @RequestMapping(value = "/male", method = RequestMethod.GET)
    public List<Employee> findByMale() {
        return employeeRepository.findByGender("male");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee saveEmployee(Employee employee) throws Exception {
        if (employee.getName() == null) {
            throw new Exception("Employee Not Find!");
        }
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Employee updateEmployee(@PathVariable long id, @ModelAttribute Employee employee) throws Exception{
        if (employeeRepository.findById(id) == null) {
            throw new Exception("Employee Not Find!");
        }
        employeeRepository.update(id, employee.getName(), employee.getAge(), employee.getGender(), employee.getCompanyId(), employee.getSalary());
        return employeeRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteEmployee(@PathVariable long id) throws Exception{
        if (employeeRepository.findById(id) == null) {
            throw new Exception("Employee Not Find!");
        }
        employeeRepository.deleteById(id);

        return true;
    }

}
