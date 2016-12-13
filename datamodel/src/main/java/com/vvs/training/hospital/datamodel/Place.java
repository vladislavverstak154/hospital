package com.vvs.training.hospital.datamodel;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;


@Table(name="place")
public class Place extends AbstractModel {
	
	@Column(datatype = "setLong", name = "cure_id")
	private Long cureId;

}
