package com.example.saulius;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee employee) {
        String[] nameParts = employee.getName().split("\\s+");
        String firstName = nameParts[0];
        if (nameParts.length > 1) {
            String lastNameInitial = String.valueOf(nameParts[1].charAt(0));
            employee.setName(firstName + " " + lastNameInitial);
        }

        if (employee.getSalary() < 22000) {
            employee.setSalary(22000);
        }
		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(Long employeeId) {
		return employeeRepository.findById(employeeId).orElse(null);

	}

	public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
		Employee employee = employeeRepository.findById(employeeId).orElse(null);
		if (employee != null) {
			employee.setName(updatedEmployee.getName());
			employee.setDepartment(updatedEmployee.getDepartment());
			employee.setProjects(updatedEmployee.getProjects());
			employee.setRole(updatedEmployee.getRole());
			employee.setSalary(updatedEmployee.getSalary());
			return employeeRepository.save(employee);
		}
		return null;
	}

	public boolean deleteEmployee(Long employeeId) {
		if (employeeRepository.existsById(employeeId)) {
			employeeRepository.deleteById(employeeId);
			return true;
		}
		return false;
	}

}
