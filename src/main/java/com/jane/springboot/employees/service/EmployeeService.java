package com.jane.springboot.employees.service;

import com.jane.springboot.employees.entity.Employee;
import com.jane.springboot.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee getById(long theId);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long theId, EmployeeRequest employeeRequest);

    Employee convertToEmployee(long theId, EmployeeRequest employeeRequest);

    void deleteById(long theId);
}
