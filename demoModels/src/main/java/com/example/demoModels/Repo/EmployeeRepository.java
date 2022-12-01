package com.example.demoModels.Repo;

import com.example.demoModels.Models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

    List<Employee> findByFirstnameContains(String firstname);
    List<Employee> findByFirstname(String firstname);
}
