package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    List<Employee> findAll();

    Employee findById(long id);

    Page<Employee> finAll(Pageable pageable);

    List<Employee> findByGender(String gender);

    Employee save(Employee employee);

    int update(long id, String name, Integer age, String gender, Integer salary, long companyId);

    void deleteByName(String name);

}
