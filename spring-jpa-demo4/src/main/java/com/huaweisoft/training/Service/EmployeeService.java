package com.huaweisoft.training.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huaweisoft.training.entity.Employee;
import com.huaweisoft.training.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    public String addEmployee(Employee employee){
        employeeRepository.save(employee);
        return "员工信息添加成功!";
    }
    
    public Employee getOneEmployee(Integer id){
        return employeeRepository.findOne(id);
    }
    
    
    
}
