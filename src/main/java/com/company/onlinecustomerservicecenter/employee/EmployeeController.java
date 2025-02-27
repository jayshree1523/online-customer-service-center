package com.company.onlinecustomerservicecenter.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("employee")
    public Employee registerNewEmployee(@RequestBody Employee employee) throws EmployeeException{
        return this.employeeService.addNewEmployee(employee);
    }

    @GetMapping("employee/{cdsId}")
    public Employee getEmployeeById(@PathVariable Integer cdsId) throws EmployeeException{
        return this.employeeService.getEmployeeById(cdsId);
    }


}
