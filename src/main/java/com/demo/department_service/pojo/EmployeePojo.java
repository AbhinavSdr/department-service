package com.demo.department_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePojo {
    private int employeeId;
    private String employeeName;
    private String employeeDesignation;
    private int empDeptId;
}
