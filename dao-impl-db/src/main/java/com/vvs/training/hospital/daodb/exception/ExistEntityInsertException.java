package com.vvs.training.hospital.daodb.exception;

import org.springframework.dao.DuplicateKeyException;

public class ExistEntityInsertException extends DuplicateKeyException {

	public ExistEntityInsertException(String msg) {
		super(msg);
	}

}
