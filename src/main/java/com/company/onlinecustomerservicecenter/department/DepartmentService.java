package com.company.onlinecustomerservicecenter.department;

import com.company.onlinecustomerservicecenter.operator.Operator;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department d)throws DepartmentException;

    Department getDepartmentById(Integer id) throws DepartmentException;

    List<Department> getAllDepartment() throws DepartmentException;

    Department deleteDepartmentById(Integer id) throws DepartmentException;


}
