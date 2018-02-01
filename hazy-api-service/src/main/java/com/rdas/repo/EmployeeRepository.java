package com.rdas.repo;

import com.rdas.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findByPersonId(Integer personId);
    List<Employee> findByCompany(String company);
}
