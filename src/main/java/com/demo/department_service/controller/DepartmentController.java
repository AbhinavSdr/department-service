package com.demo.department_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.demo.department_service.dao.entity.Department;
import com.demo.department_service.pojo.DepartmentPojo;
import com.demo.department_service.pojo.EmployeePojo;
import com.demo.department_service.service.DepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentService deptService;

    // @GetMapping("/departments/emp/{eid}")
    // public ResponseEntity<Department> getDepartmentByEmployee(@PathVariable("eid") int empId)
    // {
    //     return new ResponseEntity<Department>(deptService.getDepartmentByEmployee(empId), HttpStatus.OK);
    // }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartment(){
        return new ResponseEntity(deptService.getAllDepartment(), HttpStatus.OK);
    }

    @CircuitBreaker(name="ciremp", fallbackMethod = "fallbackEmployee")
    @GetMapping("/departments/{did}")
    public ResponseEntity<DepartmentPojo> getADepartment(@PathVariable("did") int deptId)
    {
        RestClient restClient = RestClient.create();
        List<EmployeePojo> allEmps = restClient
            .get()
            .uri("http://employee-service:8082/api/employees/dept/"+deptId)
            .retrieve()
            .body(List.class);
        Optional<Department> department = deptService.getADepartment(deptId);
        DepartmentPojo deptEmpPojo = new DepartmentPojo();
        if(department.isPresent())
        {
            deptEmpPojo.setDepartmentId(department.get().getDepartmentId());
            deptEmpPojo.setDepartmentName(department.get().getDepartmentName());
            deptEmpPojo.setAllEmployees(allEmps);
        }
        return new ResponseEntity<DepartmentPojo>(deptEmpPojo, HttpStatus.OK);
    }

    public ResponseEntity<DepartmentPojo> fallbackEmployee(){
        return new ResponseEntity<DepartmentPojo>(new DepartmentPojo(-1, null, null), HttpStatus.REQUEST_TIMEOUT);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment(@RequestBody Department newDept)
    {
        return new ResponseEntity(deptService.addDepartment(newDept), HttpStatus.OK);
    }

    @PutMapping("/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department updateDept)
    {
        return new ResponseEntity(deptService.updateDepartment(updateDept), HttpStatus.OK);
    }

    @DeleteMapping("/departments/{did}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("did") int deptId)
    {
        deptService.deleteDepartment(deptId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
