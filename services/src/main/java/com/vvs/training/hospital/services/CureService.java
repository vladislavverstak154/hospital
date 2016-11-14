package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Cure;

public interface CureService {
	@Transactional
    void saveAll(List<Cure> cures) throws Exception;

    void save(Cure cure) throws Exception;
    
    void delete(Long id);

    Cure get(Long id);

	List<Cure> getAll();
	
		
}
