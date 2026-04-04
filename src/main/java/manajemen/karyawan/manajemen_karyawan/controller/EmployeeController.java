package manajemen.karyawan.manajemen_karyawan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import manajemen.karyawan.manajemen_karyawan.entitiy.EmployeeEntity;
import manajemen.karyawan.manajemen_karyawan.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeEntity createEmployees(@Valid @RequestBody EmployeeEntity employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/search")
    public List<EmployeeEntity> searchEmployees(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String salary,
            @RequestParam(required = false) String minSalary,
            @RequestParam(required = false) String maxSalary) {
        return employeeService.searchEmployees(id, name, email, position, salary, minSalary, maxSalary);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployees(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeEntity employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployees(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Data karyawan berhasil dihapus";
    }
}
