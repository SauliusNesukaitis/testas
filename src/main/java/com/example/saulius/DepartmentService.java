package com.example.saulius;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentService {

	private DepartmentRepository departmentRepository;
	private EmployeeRepository employeeRepository;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	public Department createDepartment(Department department) {
		Long managerId = department.getManager().getEmployee_id();
		Employee manager = employeeRepository.findById(managerId).orElse(null);
		if (manager == null) {
			throw new IllegalArgumentException("Invalid manager_id. Employee not found.");
		}
		department.setManager(manager);

		return departmentRepository.save(department);
	}

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	public Department getDepartmentById(Long departmentId) {
		return departmentRepository.findById(departmentId).orElse(null);
	}

	public Department updateDepartment(Long departmentId, Department updatedDepartment) {
		Department department = departmentRepository.findById(departmentId).orElse(null);
		if (department != null) {
			department.setDepartmentName(updatedDepartment.getDepartmentName());
			department.setManager(updatedDepartment.getManager());
			department.setEmployees(updatedDepartment.getEmployees());
			return departmentRepository.save(department);
		}
		return null;
	}

	public boolean deleteDepartment(Long departmentId) {
		if (departmentRepository.existsById(departmentId)) {
			departmentRepository.deleteById(departmentId);
			return true;
		}
		return false;
	}

	public double getAverageSalaryByDepartmentId(Long departmentId) {
		return departmentRepository.getAverageSalaryByDepartmentId(departmentId);
	}
}
