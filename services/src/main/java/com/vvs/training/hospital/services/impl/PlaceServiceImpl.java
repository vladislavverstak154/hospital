package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IPlaceDao;
import com.vvs.training.hospital.datamodel.Place;
import com.vvs.training.hospital.services.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	@Inject
	private IPlaceDao placeDao;

	@Override
	public Place get(Long placeId) {
		try{
			return placeDao.getById(placeId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<Place> getAll() {
		return placeDao.getAll();}

}
