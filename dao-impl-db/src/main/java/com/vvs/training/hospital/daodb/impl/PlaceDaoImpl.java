package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IPlaceDao;
import com.vvs.training.hospital.datamodel.Place;

@Repository
public class PlaceDaoImpl extends GenericDaoImpl<Place> implements IPlaceDao{

	@Override
	public int setCureId(Long placeId, Long cureId) {
		String sql=String.format("update place set cure_id=%d where id=%d", cureId, placeId);
		return this.jdbcTemplate.update(sql);
	}


	@Override
	public int freePlace(Long cureId) {
		String sql=String.format("update place set cure_id = null where cure_id=%d", cureId);
		return this.jdbcTemplate.update(sql);
	}
	
}
