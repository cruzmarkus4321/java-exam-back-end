package com.java.exam.service;

import com.java.exam.model.Address;
import com.java.exam.model.Contact;
import com.java.exam.model.Employee;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitializingBean implements ApplicationListener<ContextRefreshedEvent> {
	private final UserService userService;

	public InitializingBean(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (userService.findByUsername("super-admin").isEmpty()) {
			userService.register("super-admin", "12345678", 1);
		}

		if (userService.findByUsername("admin").isEmpty()) {
			userService.register("admin", "12345678", 2);
		}
	}
}