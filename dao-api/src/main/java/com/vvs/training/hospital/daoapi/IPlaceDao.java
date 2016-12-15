package com.vvs.training.hospital.daoapi;

import com.vvs.training.hospital.datamodel.Place;

public interface IPlaceDao extends IGenericDao<Place> {
	
	int setCureId(Long placeId, Long cureId);
	
	int freePlace(Long cureId);
	
}
