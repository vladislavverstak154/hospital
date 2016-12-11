package com.vvs.training.hospital.daoapi;

import com.vvs.training.hospital.datamodel.Users;

public interface IUsersDao extends IGenericDao<Users> {

	void deleteByDoctorId(Long id);


}
