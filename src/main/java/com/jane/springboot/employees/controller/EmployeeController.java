package com.jane.springboot.employees.controller;

import com.jane.springboot.employees.DAO.EmployeeDAO;
import com.jane.springboot.employees.entity.Employee;
import com.jane.springboot.employees.request.EmployeeRequest;
import com.jane.springboot.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees Rest API", description = "Operations related to employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Employee> getEmployees(){
        List<Employee> employees = employeeService.findAll();

        return employees;
    }

    @Operation(summary = "Fetch single employee", description = "Get single employee from database")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable @Min(1) int id){
        return employeeService.getById(id);
    }

    @Operation(summary = "Create a new employee", description = "Add a new employee to database.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        Employee employee = employeeService.save(employeeRequest);
        return employee;
    }

    @Operation(summary = "Update an employee", description = "Update the details of an existing employee")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable @Min(value=1) long employeeId,
                                   @Valid @RequestBody EmployeeRequest employeeRequest){
        Employee dbEmployee = employeeService.update(employeeId,employeeRequest);
        return dbEmployee;
    }

    @Operation(summary = "Delete an employee", description = "Delete an employee from the database")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable @Min(value = 1) long employeeId){
        employeeService.deleteById(employeeId);
    }
}
