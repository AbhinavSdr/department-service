package com.demo.department_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.department_service.dao.DepartmentRepository;
import com.demo.department_service.dao.entity.Department;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository deptRepo;

    public List<Department> getAllDepartment(){
        return deptRepo.findAll();
    }

    // public Department getDepartmentByEmployee(int empId)
    // {
    //     return deptRepo.findByDeptEmpId(empId);
    // }

    public Optional<Department> getADepartment(int deptId)
    {
        return deptRepo.findById(deptId);
    }

    public Department addDepartment(Department newDept)
    {
        return deptRepo.saveAndFlush(newDept);
    }

    public Department updateDepartment(Department updateDept)
    {
        return deptRepo.save(updateDept);
    }

    public void deleteDepartment(int deptId)
    {
        deptRepo.deleteById(deptId);
    }
}
