package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daodb.DrugDao;
import com.vvs.training.hospital.datamodel.Drug;

@Repository
public class DrugDaoImpl extends GenericDaoImpl<Drug> implements DrugDao {

}
