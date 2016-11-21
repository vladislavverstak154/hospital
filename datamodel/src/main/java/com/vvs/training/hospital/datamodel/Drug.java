package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;

@Table(name="drug")
public class Drug extends AbstractModel {
	@Column(datatype = "setString", name = "recipe")
	private String recipe;
	@Column(datatype = "setDate", name = "date_end")
	private Date dateEnd;
	@Column(datatype = "setLong", name = "doctor_id")
	private Long doctorId;
	@Column(datatype = "setLong", name = "nurse_id")
	private Long nurseId;
	@Column(datatype = "setLong", name = "cure_id")
	private Long cureId;
	
	
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public Date getEndDate() {
		return dateEnd;
	}
	public void setEndDate(Date endDate) {
		this.dateEnd = dateEnd;
	}
	
	
}
