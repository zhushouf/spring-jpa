package com.huaweisoft.training;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huaweisoft.training.Service.EmployeeService;
import com.huaweisoft.training.entity.Employee;
import com.huaweisoft.training.repository.EmployeeRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaDemoTest {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
	@Test
	public void contextLoads() {
	    System.out.println("进入contextLoads测试方法++++++++++++++++++++++++++");
	    Employee employee = new Employee(2, "李四", "男", "17918259286", new Date());
	    System.out.println("入参为employee===="+employee);
	    Employee emp = employeeRepository.save(employee);
	    System.out.println("成功插入的参数为====="+emp);
	    
	}

}
