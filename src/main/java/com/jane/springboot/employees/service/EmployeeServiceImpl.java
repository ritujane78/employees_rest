package com.jane.springboot.employees.service;

import com.jane.springboot.employees.DAO.EmployeeDAO;
import com.jane.springboot.employees.entity.Employee;
import com.jane.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
        employeeDAO = theEmployeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee getById(long theId) {
        return employeeDAO.findById(theId);
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);

        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public Employee update(long theId, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(theId, employeeRequest);

        return employeeDAO.save(employee);
    }

    @Override
    public Employee convertToEmployee(long theId, EmployeeRequest employeeRequest) {
        Employee employee = new Employee(theId, employeeRequest.getFirstName(), employeeRequest.getFirstName(),employeeRequest.getEmail());
        return employee;
    }

    @Transactional
    @Override
    public void deleteById(long theId) {
        employeeDAO.deleteById(theId);
    }
}
