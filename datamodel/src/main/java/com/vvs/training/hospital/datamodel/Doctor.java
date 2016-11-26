package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;

@Table(name="doctor")
public class Doctor extends Person {
	@Column(datatype = "setDate", name = "date_hire")
	private Date dateHire;
	@Column(datatype = "setDate", name = "date_end_holiday")
	private Date dateEndHoliday;
	@Column(datatype = "setLong", name = "patient_amount")
	private Long patientAmount;

	public Date getDateHire() {
		return dateHire;
	}

	public Long getPatientAmount() {
		return patientAmount;
	}

	public void setPatientAmount(Long patientAmount) {
		this.patientAmount = patientAmount;
	}

	public void setDateHire(Date dateHire) {
		this.dateHire = dateHire;
	}

	public Date getDateEndHoliday() {
		return this.dateEndHoliday;
	}

	public void setDateEndHoliday(Date date) {
		this.dateEndHoliday = date;
	}
	
	
}
