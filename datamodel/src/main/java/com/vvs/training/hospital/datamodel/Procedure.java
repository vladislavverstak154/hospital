package com.vvs.training.hospital.datamodel;

import java.sql.Date;

import com.vvs.training.hospital.annotations.Column;

public class Procedure extends AbstractModel {
	@Column(datatype = "setString", name = "title")
	private String title;
	@Column(datatype = "setDate", name = "date_end")
	private Date dateEnd;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
}
