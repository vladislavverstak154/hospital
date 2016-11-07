package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daodb.OperationDao;
import com.vvs.training.hospital.datamodel.Operation;

@Repository
public class OperationDaoImpl extends GenericDaoImpl<Operation> implements OperationDao {

}
