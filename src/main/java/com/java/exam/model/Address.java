package com.java.exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "address_1")
    private String address1;
    
    @Column(name = "address_2")
    private String address2;
    
    @Column(name = "is_primary")
    private boolean isPrimary;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Address() {
		
	}

	public Address(String address1, String address2, boolean isPrimary) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.isPrimary = isPrimary;
	}
    
    
}
