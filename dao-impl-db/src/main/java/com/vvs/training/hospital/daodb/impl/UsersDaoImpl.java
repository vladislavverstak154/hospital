package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IUsersDao;
import com.vvs.training.hospital.datamodel.Users;

@Repository
public class UsersDaoImpl extends GenericDaoImpl<Users> implements IUsersDao {

	@Override
	public void deleteByDoctorId(Long id) {
		// TODO Auto-generated method stub	
	}

	/*@Override
	public int deleteByEmail(String email){
		String sql = String.format("DELETE FROM %s WHERE email=%s", this.getClazz().getSimpleName(), email);
		int rowAffected = jdbcTemplate.update(sql);
		return rowAffected;
	}
	*/
}
