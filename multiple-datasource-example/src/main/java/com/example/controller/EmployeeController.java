package com.example.controller;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepo employeeRepo;

    @PostMapping
    public Employee save(@RequestBody com.example.entity.Employee employee) {
        return employeeRepo.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@RequestBody Long id) {
        return employeeRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@RequestBody Long id) {
        employeeRepo.deleteById(id);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody com.example.entity.Employee employee) {
        return employeeRepo.save(employee);
    }

    @GetMapping
    public Iterable<com.example.entity.Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
}
