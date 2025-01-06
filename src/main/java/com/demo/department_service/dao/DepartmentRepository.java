package com.demo.department_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.department_service.dao.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

        //Department findByDeptEmpId(int empId);

}
