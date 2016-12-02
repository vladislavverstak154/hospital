package com.vvs.training.hospital.daodb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.util.fileloader.XlsDataFileLoader;

import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;

/**
 * This class is suppose to do operations, connected with the preparation of
 * database and giving the DataSets for Asserts.
 * 
 * @author Владислав
 *
 */
public class DataPreparator {

	private SchemaNameAwareBasicDataSource dataSource;

	private IDatabaseTester databaseTester;

	private XlsDataFileLoader xlsDataFileLoader;

	public DataPreparator(SchemaNameAwareBasicDataSource dataSource, IDatabaseTester databaseTester) {
		this.dataSource = dataSource;
		this.databaseTester = databaseTester;
		this.xlsDataFileLoader = new XlsDataFileLoader();
	}

	/**
	 * This method loads data to the database, from a specified xls file
	 * 
	 * @param path
	 *            - the absolute path to the xls file (here may be some troubles
	 *            on other computers)
	 * @throws IOException 
	 * @throws DataSetException 
	 */
	public void loadDataToDb(String path) throws DataSetException, IOException {
		databaseTester.setDataSet(getDataSetFromXls(path));
	}
	/**
	 * This method returns an dataSet fromXlsFile;
	 * @param path
	 * @return IDataSet
	 * @throws DataSetException
	 * @throws IOException
	 */
	public IDataSet getDataSetFromXls(String path) throws DataSetException, IOException {
		FileInputStream file = new FileInputStream(path);
		IDataSet expectedData = new XlsDataSet(file);
		return expectedData;
	}
	
	/**
	 * This method returns dataSet of current state of the DataBase
	 * @return IDataSet
	 * @throws SQLException
	 * @throws Exception
	 */
	public IDataSet getDataSetFromDb() throws SQLException, Exception{
		return databaseTester.getConnection().createDataSet();
	}

	
	/**
	 * This method returns a dataset from dataBase in xls file "fullDB.xls"
	 * @throws IOException
	 * @throws SQLException
	 * @throws DatabaseUnitException
	 */
	public void writeDataSetFromDbXls() throws IOException, SQLException, DatabaseUnitException{
		//getting connection to the DataBase
		Connection jdbcConnection = this.dataSource.getConnection();
		//getting a representation of IDataBaseConnection Interface of DBunit
		IDatabaseConnection iConnection = new DatabaseConnection(jdbcConnection);
		//getting the order which tables will be inserted by
		ITableFilter filter = new DatabaseSequenceFilter(iConnection);
		//getting IDataSet representation according to the bounds of tables
		IDataSet fullDataSet = new FilteredDataSet(filter, iConnection.createDataSet());
		//Creating and opening file that data will be written to 
		FileOutputStream file = new FileOutputStream("fullDB.xls");
		XlsDataSet.write(fullDataSet, file);
	}
	
}
