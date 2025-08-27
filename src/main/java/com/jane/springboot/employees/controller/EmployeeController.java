package com.jane.springboot.employees.controller;

import com.jane.springboot.employees.DAO.EmployeeDAO;
import com.jane.springboot.employees.entity.Employee;
import com.jane.springboot.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        List<Employee> employees = employeeService.findAll();

        return employees;
    }
}
