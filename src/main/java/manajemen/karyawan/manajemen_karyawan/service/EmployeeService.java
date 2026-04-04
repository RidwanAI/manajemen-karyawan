package manajemen.karyawan.manajemen_karyawan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manajemen.karyawan.manajemen_karyawan.entitiy.EmployeeEntity;
import manajemen.karyawan.manajemen_karyawan.exception.BadRequestException;
import manajemen.karyawan.manajemen_karyawan.exception.DataNotFoundException;
import manajemen.karyawan.manajemen_karyawan.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new BadRequestException("Email sudah digunakan");
        }
        return employeeRepository.save(employee);
    }

    public List<EmployeeEntity> searchEmployees(String id, String name, String email, String position, String salary, String minSalary, String maxSalary) {
        
        boolean noParam =
            id == null &&
            name == null &&
            email == null &&
            position == null &&
            salary == null &&
            minSalary == null &&
            maxSalary == null;

        if (noParam) {
            throw new BadRequestException("Mohon masukkan setidaknya satu parameter pencarian");
        }

        boolean invalidParam =
            (id != null && id.trim().isEmpty()) ||
            (name != null && name.trim().isEmpty()) ||
            (email != null && email.trim().isEmpty()) ||
            (position != null && position.trim().isEmpty()) ||
            (salary != null && salary.trim().isEmpty()) ||
            (minSalary != null && minSalary.trim().isEmpty()) ||
            (maxSalary != null && maxSalary.trim().isEmpty());

        if (invalidParam) {
            throw new BadRequestException("Parameter pencarian tidak valid");
        }

        Long parsedId = null;
        Double parsedSalary = null;
        Double parsedMinSalary = null;
        Double parsedMaxSalary = null;

        try {
            if (id != null) parsedId = Long.parseLong(id);
            if (salary != null) parsedSalary = Double.parseDouble(salary);
            if (minSalary != null) parsedMinSalary = Double.parseDouble(minSalary);
            if (maxSalary != null) parsedMaxSalary = Double.parseDouble(maxSalary);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Format angka tidak valid");
        }

        List<EmployeeEntity> result;
        
        if (id != null) {
            result = employeeRepository.findById(parsedId)
                    .map(List::of)
                    .orElseThrow(() -> new DataNotFoundException("Data karyawan tidak ditemukan"));
        } else if (name != null && !name.trim().isEmpty()) {
            result = employeeRepository.findByNameContainingIgnoreCase(name);
        } else if (email != null && !email.trim().isEmpty()) {
            result = employeeRepository.findByEmailContainingIgnoreCase(email);
        } else if (position != null && !position.trim().isEmpty()) {
            result = employeeRepository.findByPositionContainingIgnoreCase(position);
        } else if (salary != null) {
            result = employeeRepository.findBySalary(parsedSalary);
        } else if (minSalary != null && maxSalary != null) {
            result = employeeRepository.findBySalaryBetween(parsedMinSalary, parsedMaxSalary);
        } else if (minSalary != null) {
            result = employeeRepository.findBySalaryGreaterThan(parsedMinSalary);
        } else if (maxSalary != null) {
            result = employeeRepository.findBySalaryLessThan(parsedMaxSalary);
        } else {
            throw new BadRequestException("Parameter pencarian tidak valid");
        }

        if (result.isEmpty()) {
            throw new DataNotFoundException("Data karyawan tidak ditemukan");
        }
        return result;
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeEntity updatedEmployee) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data karyawan tidak ditemukan"));

        if (employeeRepository.existsByEmailAndIdNot(updatedEmployee.getEmail(), id)) {
            throw new BadRequestException("Email sudah digunakan");
        }

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Data karyawan tidak ditemukan"));
        employeeRepository.delete(employee);
    }
}
