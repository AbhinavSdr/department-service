package com.demo.department_service.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentPojo {

    private int departmentId;
    private String departmentName;
    List<EmployeePojo> allEmployees;
}
