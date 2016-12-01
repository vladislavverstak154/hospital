package com.vvs.training.hospital.datamodel;

import com.vvs.training.hospital.annotations.Id;

public class AbstractModel {
	
	@Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
