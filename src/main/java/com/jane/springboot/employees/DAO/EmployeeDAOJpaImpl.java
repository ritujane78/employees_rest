package com.jane.springboot.employees.DAO;

import com.jane.springboot.employees.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {

//        Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

//        Execute query and get results list
        List<Employee> employees = theQuery.getResultList();

//        Return the list
        return  employees;
    }

    @Override
    public Employee findById(long theId) {
        Employee theEmployee = entityManager.find(Employee.class, theId);

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);

        return dbEmployee;
    }

    @Override
    public void deleteById(long theId) {
        Employee toDelete = findById(theId);

        entityManager.remove(toDelete);
    }
}
