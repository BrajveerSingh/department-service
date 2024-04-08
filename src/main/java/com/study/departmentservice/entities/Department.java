package com.study.departmentservice.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Department {
    @Id
    private Long id;
    @Column("department_name")
    private String departmentName;
    @Column("department_description")
    private String departmentDescription;
    @Column("department_code")
    private String departmentCode;
}
