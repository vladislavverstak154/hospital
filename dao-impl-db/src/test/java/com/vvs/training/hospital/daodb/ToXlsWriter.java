package com.vvs.training.hospital.daodb;

import java.io.FileOutputStream;
import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.util.fileloader.XlsDataFileLoader;

public class ToXlsWriter {

	@Inject
	private DataSource dataSource;
	@Inject
	private XlsDataFileLoader xlsDataFileLoader;
	@Inject
	private static IDatabaseTester databaseTester;
	private static Connection jdbcConnection;
	private static IDatabaseConnection iConnection;
	private static IDataSet fullDataSet;
	private static FileOutputStream file;

	public ToXlsWriter(DataSource datasourse) throws Exception {
		this.dataSource = datasourse;
		this.jdbcConnection = this.dataSource.getConnection();
		this.iConnection = new DatabaseConnection(jdbcConnection);
		this.fullDataSet = iConnection.createDataSet();
		this.file = new FileOutputStream("ALL.xls");
		XlsDataSet.write(fullDataSet, file);
	}

	public static IDataSet getDataSet(String path){
		XlsDataFileLoader xlsDataFileLoader = new XlsDataFileLoader();
		IDataSet dataSet = xlsDataFileLoader.load(path);
		return dataSet;
	}
}
