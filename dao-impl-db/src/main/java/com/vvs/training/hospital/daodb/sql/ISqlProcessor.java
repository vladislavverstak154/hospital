package com.vvs.training.hospital.daodb.sql;

import java.util.Iterator;

import com.vvs.training.hospital.annotations.Column;

public interface ISqlProcessor<T> {
	
	public String namedParameterUpdateSql(T entity);
		
	
}
