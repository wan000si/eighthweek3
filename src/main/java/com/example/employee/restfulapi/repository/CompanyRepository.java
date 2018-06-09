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
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Override
    @Query("select c from Company c")
    List<Company> findAll();

    @Query("select c from Company c where c.id=?1")
    Company findById(Long id);

    @Query("select e from Employee e where e.companyId=?1")
    List<Employee> getEmployeesByCompanyId(long companyId);

    Page<Company> findAll(Pageable pageable);


    Company save(Company company);

    @Transactional
    @Modifying
    @Query("update Company c set c.companyName=?2,c.employeesNumber=?3 where c.id=?1")
    void updateCompany(Long id, String companyName, int employeesNumber);

    @Transactional
    @Modifying
    void deleteById(Long id);

}
