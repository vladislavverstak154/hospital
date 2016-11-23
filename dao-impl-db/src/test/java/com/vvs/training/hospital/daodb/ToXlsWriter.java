package com.vvs.training.hospital.daodb;

import javax.inject.Inject;

import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;

public class ToXlsWriter {
	
	@Inject
	private 
	
	// экспортируем часть базы данных
	QueryDataSet partialDataSet = new QueryDataSet(iConnection);
	// экспорт таблицы, но не всей, а только определенных записей
	partialDataSet.addTable("users", "SELECT * FROM users where sex = ‘m’ ");
	// экспорт всей таблицы
	partialDataSet.addTable("articles");
	// сохраняем изменения в файл
	FlatXmlDataSet.write(partialDataSet, new FileOutputStream("users-and-articles-dataset.xml"));
	// экспорт всей базы данных полностью
	IDataSet fullDataSet = iConnection.createDataSet();
	FlatXmlDataSet.write(fullDataSet, new FileOutputStream("all-tables-dataset.xml"));
}
