package com.vvs.training.hospital.datamodel;

import java.sql.Date;

public class Drug extends AbstractModel {
	private String recipe;
	private Date endDate;
	public String getRecipe() {
		return recipe;
	}
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
