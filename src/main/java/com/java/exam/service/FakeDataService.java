package com.java.exam.service;

import com.github.javafaker.Faker;
import com.java.exam.model.Address;
import com.java.exam.model.Contact;
import com.java.exam.model.Employee;
import com.java.exam.repository.EmployeeRepository;
import com.java.exam.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FakeDataService {
    private final EmployeeService employeeService;
    private final Faker faker;

    @Autowired
    public FakeDataService(EmployeeService employeeService, Faker faker) {
        this.employeeService = employeeService;
        this.faker = faker;
    }

    @PostConstruct
    @Transactional
    public void generateFakeData() {
        if (employeeService.getEmployeeCount() < 10) {
            for (int i = 0; i < 50; i++) {
                Employee employee = createFakeEmployee();
                employeeService.addEmployee(employee);
            }
        }
    }

    private Employee createFakeEmployee() {
        Employee employee = new Employee();
        employee.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        LocalDate pastDate = LocalDate.now().minusDays(faker.number().numberBetween(1, 365));
        employee.setDateHired(pastDate.atStartOfDay(ZoneId.systemDefault()).toLocalDate());
        employee.setFirstName(faker.name().firstName());
        employee.setLastName(faker.name().lastName());
        employee.setMiddleName(faker.name().lastName());
        employee.setGender(faker.options().option("Male", "Female"));
        employee.setMaritalStatus(faker.options().option("Single", "Married", "Divorced", "Widowed"));
        employee.setPosition(faker.job().title());
        employee.setCreatedAt(LocalDateTime.now());

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(faker.phoneNumber().cellPhone(), true));
        contacts.add(new Contact(faker.phoneNumber().cellPhone(), false));
        employee.setContacts(contacts);

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(faker.address().streetAddress(), faker.address().secondaryAddress(), true));
        addresses.add(new Address(faker.address().streetAddress(), faker.address().secondaryAddress(), false));
        employee.setAddresses(addresses);

        return employee;
    }
}

