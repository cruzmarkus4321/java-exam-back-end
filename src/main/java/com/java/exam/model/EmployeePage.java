package com.java.exam.model;

import java.util.List;

public class EmployeePage {
	private List<Employee> employees;

	private int totalCount;
	private int totalPages;

	public EmployeePage(List<Employee> employees, int totalCount, int totalPages) {
		this.employees = employees;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}
}