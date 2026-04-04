package manajemen.karyawan.manajemen_karyawan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import manajemen.karyawan.manajemen_karyawan.entitiy.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByNameContainingIgnoreCase(String name);
    List<EmployeeEntity> findByEmailContainingIgnoreCase(String email);
    List<EmployeeEntity> findByPositionContainingIgnoreCase(String position);
    List<EmployeeEntity> findBySalary(Double salary);
    List<EmployeeEntity> findBySalaryBetween(Double min, Double max);
    List<EmployeeEntity> findBySalaryGreaterThan(Double salary);
    List<EmployeeEntity> findBySalaryLessThan(Double salary);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
