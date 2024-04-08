package com.study.departmentservice.services;

import com.study.departmentservice.entities.Department;
import com.study.departmentservice.model.DepartmentDto;
import com.study.departmentservice.repositories.DepartmentsRepository;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.stereotype.Service;
//import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.function.Supplier;

@Service
public class DepartmentsService {
    private final DepartmentsRepository departmentsRepository;
    private final Supplier<Long> latency = () -> new Random().nextLong(500);

    private final ObservationRegistry registry;

    public DepartmentsService(DepartmentsRepository departmentsRepository, ObservationRegistry registry) {
        this.departmentsRepository = departmentsRepository;
        this.registry = registry;
    }

    public Mono<DepartmentDto> create(DepartmentDto request) {
        Mono<Department> departmentMono = departmentsRepository.save(
                new Department(null, request.departmentName(), request.departmentDescription(), request.departmentCode())
        );
        return departmentMono.map(
                department -> new DepartmentDto(
                        department.getId(),
                        department.getDepartmentName(),
                        department.getDepartmentDescription(),
                        department.getDepartmentCode()
                )
        );
    }

    public Flux<DepartmentDto> getAllDepartments() {
        return departmentsRepository.findAll().map(
                department -> new DepartmentDto(
                        department.getId(),
                        department.getDepartmentName(),
                        department.getDepartmentDescription(),
                        department.getDepartmentCode()
                )
        );
    }

    public Mono<DepartmentDto> getDepartmentById(Long id) {
        Long lat = latency.get();
        return departmentsRepository.findById(id).map(
                        department -> new DepartmentDto(
                                department.getId(),
                                department.getDepartmentName(),
                                department.getDepartmentDescription(),
                                department.getDepartmentCode()
                        )
                )/*.name("getDepartmentById.call")
                .tag("latency", lat > 250 ? "high" : "low")
                .tap(Micrometer.observation(registry))*/;
    }

    public Mono<DepartmentDto> updateDepartment(Long id, DepartmentDto request) {
        return departmentsRepository.findById(id).flatMap(
                department -> {
                    department.setDepartmentName(request.departmentName());
                    department.setDepartmentDescription(request.departmentDescription());
                    department.setDepartmentCode(request.departmentCode());
                    return departmentsRepository.save(department);
                }
        ).map(
                department -> new DepartmentDto(
                        department.getId(),
                        department.getDepartmentName(),
                        department.getDepartmentDescription(),
                        department.getDepartmentCode()
                )
        );
    }

    public Mono<Void> deleteDepartment(Long id) {
        return departmentsRepository.deleteById(id);
    }

    public Mono<DepartmentDto> getDepartmentByCode(String departmentCode) {
        return departmentsRepository.findByDepartmentCode(departmentCode).map(
                department -> new DepartmentDto(
                        department.getId(),
                        department.getDepartmentName(),
                        department.getDepartmentDescription(),
                        department.getDepartmentCode()
                )
        );
    }
}
