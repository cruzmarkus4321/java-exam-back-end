package com.java.exam.service;

import com.java.exam.repository.AddressRepository;
import com.java.exam.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.exam.model.Address;
import com.java.exam.model.Contact;
import com.java.exam.model.Employee;
import com.java.exam.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private AddressRepository addressRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Page<Employee> getEmployees(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAll(pageable);
	}

	public Employee getEmployee(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}

	public long getEmployeeCount() {
		return employeeRepository.count();
	}

	public Employee addEmployee(Employee employee) {
		for (Contact contact : employee.getContacts()) {
            contact.setEmployee(employee);
        }

        for (Address address : employee.getAddresses()) {
            address.setEmployee(employee);
        }
        
        employee.setContacts(employee.getContacts());
        employee.setAddresses(employee.getAddresses());
		employee.setCreatedAt(LocalDateTime.now());

		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = getEmployee(id);

		if (existingEmployee != null) {
			employee.setId(existingEmployee.getId());

			updateContacts(existingEmployee, employee.getContacts());
			updateAddresses(existingEmployee, employee.getAddresses());

			employee.setUpdatedAt(LocalDateTime.now());

			return employeeRepository.save(employee);
		}

		return null;
	}

	private void updateContacts(Employee employee, List<Contact> updatedContacts) {
		// Save new or updated contacts
		updatedContacts.forEach(contact -> {
			contact.setEmployee(employee);
			contactRepository.save(contact);
		});
	}

	private void updateAddresses(Employee employee, List<Address> updatedAddresses) {
		// Save new or updated addresses
		updatedAddresses.forEach(address -> {
			address.setEmployee(employee);
			addressRepository.save(address);
		});
	}

	public void deleteEmployee(Long id) {
		Employee employee = getEmployee(id);

		if (employee != null) {
			employee.setDeleted(true);
			employee.setDeletedAt(LocalDateTime.now());

			employeeRepository.save(employee);
		}
	}
}
