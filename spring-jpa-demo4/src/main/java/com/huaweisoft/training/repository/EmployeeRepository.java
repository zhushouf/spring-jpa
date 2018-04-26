package com.huaweisoft.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huaweisoft.training.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
 