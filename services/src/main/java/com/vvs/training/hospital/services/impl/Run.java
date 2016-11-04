package com.vvs.training.hospital.services.impl;

import com.vvs.training.hospital.services.DoctorService;

public class Run {

	public static void main(String[] args) {
		
		DoctorService doctorService=new DoctorServiceImpl();
		System.out.println(doctorService.get(1l).toString());
		
	}

}
