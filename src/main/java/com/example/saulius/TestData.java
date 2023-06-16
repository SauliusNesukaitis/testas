package com.example.saulius;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestData implements CommandLineRunner {

	private EmployeeService employeeService;
	private DepartmentService departmentService;
	private ProjectService projectService;

	@Autowired
	public TestData(EmployeeService employeeService, DepartmentService departmentService,
			ProjectService projectService) {
		this.employeeService = employeeService;
		this.departmentService = departmentService;
		this.projectService = projectService;
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee1 = new Employee();
		employee1.setName("Saulius Nesukaitis");
		employee1.setRole("Programuotojas");
		employee1.setSalary(29000);
		employeeService.createEmployee(employee1);

		Employee employee2 = new Employee();
		employee2.setName("Jonas Jonaitis");
		employee2.setRole("Manager");
		employee2.setSalary(39000);
		employeeService.createEmployee(employee2);

		
		Department department1 = new Department();
		department1.setDepartmentName("Engineering");
		department1.setManager(employee1);
		departmentService.createDepartment(department1);

		Department department2 = new Department();
		department2.setDepartmentName("Sales");
		department2.setManager(employee2);
		departmentService.createDepartment(department2);


		Project project1 = new Project();
		project1.setProjectName("Project A");
		project1.setProjectStartDate(LocalDate.now());
		project1.setProjectDueDate(LocalDate.now().plusMonths(6));
		project1.getEmployees().add(employee1);
		project1.getEmployees().add(employee2);
		projectService.createProject(project1);
	}
}
