package com.vvs.training.hospital.services;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

/**
 * This class is copied from book Spring4 for Pro and was simply modified by me
 * 
 * @author Владислав
 *
 */
public class ServiceTestExecutionListener implements TestExecutionListener {
	
	/**
	 * This is class of DBunit, it should be specified in spring context XML
	 * file
	 */
	
	private IDatabaseTester databaseTester;

	@Override
	public void afterTestClass(TestContext arg0) throws Exception {
	}

	/**
	 * This method will call after test to clean up the data from the database
	 * 
	 * @param arg0
	 * @throws Exception
	 */
	@Override
	public void afterTestMethod(TestContext arg0) throws Exception {
		if (databaseTester != null) {
			databaseTester.onTearDown();
		}
	}

	@Override
	public void beforeTestClass(TestContext arg0) throws Exception {
	}

	/**
	 * This method is called before the test
	 * 
	 * @param testCtx
	 *            - the test context which was specified as a parameter in
	 *            annotation /@ContextConfiguration
	 * @throws Exception
	 */
	@Override
	public void beforeTestMethod(TestContext testCtx) throws Exception {
		// checks for the existence of the @DataSets
		DataSets dataSetAnnotation = testCtx.getTestMethod().getAnnotation(DataSets.class);
		if (dataSetAnnotation == null) {
			return;
		}
		String dataSetName = dataSetAnnotation.setUpDataSet();
		if (!dataSetName.equals("")) {
			/*
			 * obtaining the IDatabaseTester interface (with the implementation
			 * class org.dbunit.DataSourceDatabaseTester
			 */
			this.databaseTester = (IDatabaseTester) testCtx.getApplicationContext().getBean("databaseTester");
			/*
			 * loading XlsDataFileLoader from testSpring context(where the
			 * datasource was specified as constructor argument
			 */
			XlsDataFileLoader xlsDataFileLoader = (XlsDataFileLoader) testCtx.getApplicationContext()
					.getBean("xlsDataFileLoader");
			// loading dataset from excel file specified in the parameter name
			// of annotation DataSets
			IDataSet dataSet = xlsDataFileLoader.load(dataSetName);
			/*
			 * Before loading data to the DataBase we switch off the foreign key
			 * cheks because the DBunit don't takes into account the bounds
			 * among the tables when choose the order of inserting data
			 */
			databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
			databaseTester.setDataSet(dataSet);
			//inserting data to the Database
			databaseTester.onSetup();
		}
	}
	
	
	@Override
	public void prepareTestInstance(TestContext arg0) throws Exception {
	}
	
	/*
	private void fkcheckOff(String[] tableNames){
		for(String table:tableNames){
		this.jdbcTemplate.execute(String.format("ALTER TABLE %s DISABLE TRIGGER ALL", table));
		}
	}
	
	
	private void fkchekToOn(String[] tableNames){
		for(String table:tableNames){
		this.jdbcTemplate.execute(String.format("ALTER TABLE %s ENABLE TRIGGER ALL", table));
		}
	} */

}
