package com.vvs.training.hospital.daodb;

import java.io.FileOutputStream;
import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.util.fileloader.XlsDataFileLoader;

public class ToXlsWriter {

	@Inject
	private static IDatabaseTester databaseTester;
	private static Connection jdbcConnection;
	private static IDatabaseConnection iConnection;
	private static IDataSet fullDataSet;
	private static FileOutputStream file;

	
	public ToXlsWriter(DataSource datasourse) throws Exception {
		//getting connection to the DataBase
		this.jdbcConnection = datasourse.getConnection();
		//getting a representation of IDataBaseConnection Interface of DBunit
		this.iConnection = new DatabaseConnection(jdbcConnection);
		//getting the order which tables will be inserted by
		ITableFilter filter = new DatabaseSequenceFilter(iConnection);
		//getting IDataSet representation according to the bounds of tables
		this.fullDataSet = new FilteredDataSet(filter, iConnection.createDataSet());
		//Creating and opening file that data will be written to 
		this.file = new FileOutputStream("ALL.xls");
		XlsDataSet.write(fullDataSet, file);
	}
	
   /* This code maybe will be needed by me later
	public static IDataSet getDataSet(String path){
		XlsDataFileLoader xlsDataFileLoader = new XlsDataFileLoader();
		IDataSet dataSet = xlsDataFileLoader.load(path);
		return dataSet;
	}
	*/ 
}
