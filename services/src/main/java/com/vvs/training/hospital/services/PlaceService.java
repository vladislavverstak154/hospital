package com.vvs.training.hospital.services;

import java.util.List;

import com.vvs.training.hospital.datamodel.Place;

public interface PlaceService {

	Place get(Long placeId);

	List<Place> getAll();
}
