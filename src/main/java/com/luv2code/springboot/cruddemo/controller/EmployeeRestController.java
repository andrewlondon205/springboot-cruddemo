package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping("/employee/all")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee theEmployee = employeeService.findById(id);
        if (theEmployee == null)
            throw new RuntimeException("The desired resource does not exist");
        return theEmployee;
    }

    @PostMapping("/addemployee")
    public Employee addEmployee(Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/deleteemployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee tempEmployee = employeeService.findById(id);
        if(tempEmployee == null)
            throw new RuntimeException("Employee id not found - " + id);
        employeeService.deleteById(id);
        return "Deleted employee id - " + id;
    }

    @PutMapping("/updateemployee")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);
        return theEmployee;
    }
}
