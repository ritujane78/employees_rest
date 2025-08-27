package com.jane.springboot.employees.service;

import com.jane.springboot.employees.DAO.EmployeeRepository;
import com.jane.springboot.employees.entity.Employee;
import com.jane.springboot.employees.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(long theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee;
        if(result.isPresent()){
            theEmployee = result.get();
        } else {
            throw new RuntimeException("didn't find the employee with id - " + theId);
        }

        return theEmployee;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(0, employeeRequest);

        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee update(long theId, EmployeeRequest employeeRequest) {
        Employee employee = convertToEmployee(theId, employeeRequest);

        return employeeRepository.save(employee);
    }

    @Override
    public Employee convertToEmployee(long theId, EmployeeRequest employeeRequest) {
        Employee employee = new Employee(theId, employeeRequest.getFirstName(), employeeRequest.getFirstName(),employeeRequest.getEmail());
        return employee;
    }

    @Transactional
    @Override
    public void deleteById(long theId) {
        employeeRepository.deleteById(theId);
    }
}
