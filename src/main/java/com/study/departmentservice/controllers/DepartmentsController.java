package com.study.departmentservice.controllers;

import com.study.departmentservice.model.DepartmentDto;
import com.study.departmentservice.services.DepartmentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/departments/v1")
@AllArgsConstructor
//@Observed
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    @PostMapping
    public ResponseEntity<Mono<DepartmentDto>> create(@RequestBody DepartmentDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentsService.create(request));
    }

    // Add the code that is needed to retrieve all departments
    @GetMapping
    public ResponseEntity<Flux<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentsService.getAllDepartments());
    }

    // Add the code that is needed to retrieve a department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Mono<DepartmentDto>> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentsService.getDepartmentById(id));
    }

    // Add the code that is needed to update a department
    @PutMapping("/{id}")
    public ResponseEntity<Mono<DepartmentDto>> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto request) {
        return ResponseEntity.ok(departmentsService.updateDepartment(id, request));
    }

    // Add the code that is needed to delete a department
    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> deleteDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentsService.deleteDepartment(id));
    }

    //Add the code that is needed to retrieve department for the given departmentCode
    @GetMapping("/code/{departmentCode}")
    public ResponseEntity<Mono<DepartmentDto>> getDepartmentByCode(@PathVariable String departmentCode) {
        return ResponseEntity.ok(departmentsService.getDepartmentByCode(departmentCode));
    }
}
