package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IPlaceDao;
import com.vvs.training.hospital.datamodel.Place;

@Repository
public class PlaceDaoImpl extends GenericDaoImpl<Place> implements IPlaceDao{

	@Override
	public void setCureId(Long placeId, Long cureId) {
		String sql=String.format("update place set patient_id=%d where id=%d", cureId, placeId);
		this.jdbcTemplate.update(sql);
	}
	
}
