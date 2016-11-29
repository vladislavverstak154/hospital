package com.vvs.training.hospital.datamodel;

import com.vvs.training.hospital.annotations.Column;

public class Role extends AbstractModel {
	@Column(datatype = "setString", name = "title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
