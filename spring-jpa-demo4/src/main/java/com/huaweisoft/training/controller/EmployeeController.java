package com.huaweisoft.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huaweisoft.training.Service.EmployeeService;
import com.huaweisoft.training.entity.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

   @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!Hello spring jpa!!!!!!!!!!!!!";
    }
    
    @RequestMapping(value = "/saveEmployee" , method = RequestMethod.POST)
    public String saveEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return "add"; 
    }
    
    @RequestMapping(value = "/getEmployee/{id}",method = RequestMethod.GET)
    public Employee test(@PathVariable Integer id){
        return employeeService.getOneEmployee(id);
    }
    
}
