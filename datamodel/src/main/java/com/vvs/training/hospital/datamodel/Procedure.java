package com.vvs.training.hospital.datamodel;

import java.sql.Date;

public class Procedure extends AbstractModel {
	private String title;
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
