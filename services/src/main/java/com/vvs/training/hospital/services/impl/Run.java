package com.vvs.training.hospital.services.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vvs.training.hospital.services.PatientService;

public class Run {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext("service-context.xml");

		String[] beanDefinitionNames = springContext.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			System.out.println(beanName);
		}


		PatientService patient = new PatientServiceImpl();

		

	}

}
