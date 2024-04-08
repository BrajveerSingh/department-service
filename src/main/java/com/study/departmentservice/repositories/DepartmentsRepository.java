package com.study.departmentservice.repositories;

import com.study.departmentservice.entities.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DepartmentsRepository extends ReactiveCrudRepository<Department, Long> {
    Mono<Department> findByDepartmentCode(String departmentCode);
}
