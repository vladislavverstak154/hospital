package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Nurse;

public interface NurseService {

	@Transactional
	void saveAll(List<Nurse> nurses) throws Exception;

	void save(Nurse nurse) throws Exception;

	void delete(Long id);

	Nurse get(Long id);

	List<Nurse> getAll();
}
