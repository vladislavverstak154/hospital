package com.vvs.training.hospital.daodb.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IUsersDao;
import com.vvs.training.hospital.datamodel.Users;

@Repository
public class UsersDaoImpl extends GenericDaoImpl<Users> implements IUsersDao {

	@Override
	public Long insert(Users users){
		// Check if the SimpleInsert was already compiled for this table
				if (!this.tableSet) {
					this.insertEntity = this.insertEntity.withTableName(this.getClazz().getSimpleName());
					this.tableSet = true;
				}
				SqlParameterSource parameters = new BeanPropertySqlParameterSource(users);

				Number newId = insertEntity.execute(parameters);
				return newId.longValue();
	}

}
