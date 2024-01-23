package com.java.exam.controller;

import com.java.exam.model.AuthResponse;
import com.java.exam.model.EmployeePage;
import com.java.exam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.java.exam.model.Employee;
import com.java.exam.service.EmployeeService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin
public class EmployeeController {
	@Autowired
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@QueryMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@QueryMapping
	public EmployeePage getEmployees(@Argument int page, @Argument int size) {
		Page<Employee> employeePage = employeeService.getEmployees(page, size);

		return new EmployeePage(
				employeePage.getContent(),
				Math.toIntExact(employeePage.getTotalElements()),
				employeePage.getTotalPages()
		);
	}

	@QueryMapping
	public Employee getEmployee(@Argument Long id) {
		return employeeService.getEmployee(id);
	}

	@MutationMapping
	public Employee addEmployee(@Argument Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@MutationMapping
	public Employee updateEmployee(@Argument Long id, @Argument Employee employee) {
		return employeeService.updateEmployee(id, employee);
	}

	@MutationMapping
	public Boolean deleteEmployee(@Argument Long id) {
		employeeService.deleteEmployee(id);

		return true;
	}
}