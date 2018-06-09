package com.example.employee.restfulapi.repository;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
//import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    @Query("select e from Employee e")
    List<Employee> findAll();

    @Query("select e from Employee e where e.id=?1")
    Employee findById(long id);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findByGender(String gender);

    Employee save(Employee employee);

    @Modifying
    @Transactional
    @Query("update Employee e set e.name=?2,e.age=?3,e.gender=?4,e.companyId=?5,e.salary=?6 where e.id=?1")
    void update(Long id, String name, Integer age, String gender, Long companyId, Integer salary);

    @Transactional
    @Modifying
    void deleteById(long id);

}
