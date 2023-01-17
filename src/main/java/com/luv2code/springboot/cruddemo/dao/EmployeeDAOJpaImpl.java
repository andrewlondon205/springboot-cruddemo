package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    @Override
    public List<Employee> findAll() {

        //create a query
        Query theQuery = entityManager.createQuery("from Employee");
        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        // return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee theEmployee = entityManager.find(Employee.class,id);
        return theEmployee;
    }

    @Override
    public void save(Employee employee) {
        //save or delete the employee
        Employee dbEmployee = entityManager.merge(employee);
        //update with id from db ... so we can get generated id for save/insert
        employee.setId(dbEmployee.getId());

    }

    @Override
    public void deleteById(int id) {
        //delete object with primary key
        Query theQuery = entityManager.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId",id);
        theQuery.executeUpdate();
    }
}
