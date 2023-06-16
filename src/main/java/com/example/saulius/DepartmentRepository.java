package com.example.saulius;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.departmentId = :departmentId")
    double getAverageSalaryByDepartmentId(Long departmentId);

}
