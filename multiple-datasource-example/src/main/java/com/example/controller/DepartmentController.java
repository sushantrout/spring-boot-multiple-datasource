package com.example.controller;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.repo.DepartmentRepo;
import com.example.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentRepo departmentRepo;

    @PostMapping
    public Department save(@RequestBody Department employee) {
        return departmentRepo.save(employee);
    }

    @GetMapping("/{id}")
    public Department getEmployeeById(@RequestBody Long id) {
        return departmentRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@RequestBody Long id) {
        departmentRepo.deleteById(id);
    }

    @PutMapping
    public Department updateEmployee(@RequestBody Department employee) {
        return departmentRepo.save(employee);
    }

    @GetMapping
    public Iterable<Department> getAllEmployees() {
        return departmentRepo.findAll();
    }
}
