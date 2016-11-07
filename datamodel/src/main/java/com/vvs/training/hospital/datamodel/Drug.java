package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;

public class Drug extends AbstractModel {
	@Column(datatype = "setString", name = "recipe")
	private String recipe;
	@Column(datatype = "setDate", name = "date_end")
	private Date dateEnd;
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
