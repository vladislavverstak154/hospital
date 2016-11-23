package com.vvs.training.hospital.daodb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;

import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;

public class ToXlsWriter {

	@Inject
	private DataSource dataSource;
	private Connection jdbcConnection;
	private IDatabaseConnection iConnection;
	private IDataSet fullDataSet;
	private FileOutputStream file;
	
	public ToXlsWriter(DataSource datasourse) throws Exception{
		this.dataSource=datasourse;
		this.jdbcConnection = this.dataSource.getConnection();
		this.iConnection = new DatabaseConnection(jdbcConnection);
		this.fullDataSet = iConnection.createDataSet();
		this.file = new FileOutputStream("all.xls");
		XlsDataSet.write(fullDataSet, file);
	}
}
				
	
		
		
	
	



