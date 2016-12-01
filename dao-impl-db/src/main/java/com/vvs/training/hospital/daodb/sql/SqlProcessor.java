package com.vvs.training.hospital.daodb.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.vvs.training.hospital.annotations.Column;

/**
 * This class is made for generating a simple SQL queries, the entity which is
 * passed to the constructor of this class should have all it's fields annotated
 * by annotations from com.vvs.training.hospital.daodb.annotations package
 * 
 * @author Владислав
 *
 * @param <T>
 *            this parameter will be automatically determined by the
 *            constructor, depending on the entity, passed to the constructor
 */
public class SqlProcessor {

	/**
	 * This parameter represents an entity, from which will be taken a class
	 */
	private Class clazz;
	/**
	 * This field represents a Map<ColumnName, Field> of some entity
	 */
	private Map<String, Field> nameField = new TreeMap();
	/**
	 * This is the set of columnNames, for getting the fields from Map<>
	 * nameField
	 */
	private Set<String> columnNames;
	/**
	 * This is the id of entity
	 */
	private Long id;

	/**
	 * This constructor creates new SqlCreator which will creates SQL queries
	 * for some entity
	 * 
	 * @param entity
	 *            the entity for which SQL queries should be created
	 */
	public SqlProcessor(Class clazz) {
		this.clazz = clazz;
		this.getAnnotatedFields();
		this.columnNames = this.nameField.keySet();
	}

	public <K> String getByColumnSql(String column) {

		String sql = String.format("SELECT * FROM"
				+ " %s WHERE %s = :%s",
				this.clazz.getSimpleName(),this.nameField.get(column).getAnnotation(Column.class).name(),
				this.nameField.get(column).getAnnotation(Column.class).name());
				return sql;
	}

	/**
	 * Returns an update SQL for all fields of entire entity which will be
	 * passed to the JDBC NamedParameter
	 * 
	 * @return String updateSQL
	 */

	public String updateSql(Long id) {
		// getting an iterator to run thru the all column names of a table of an
		// entity
		Iterator<String> iterator = this.columnNames.iterator();
		// the values string buffer
		StringBuffer values = new StringBuffer();
		/*
		 * in this while loop we get a string that will look like
		 * column_name=:fieldName of an entity
		 */
		while (iterator.hasNext()) {
			String currentField = iterator.next();
			values.append(currentField + " =" + " :" + this.nameField.get(currentField).getName());
			if (iterator.hasNext()) {
				values.append(", ");
			}

		}
		String sql = new String(
				"UPDATE " + this.clazz.getSimpleName() + " SET " + values.toString() + " " + " WHERE id=" + id);
		return sql;
	}

	/*
	 * This method select only annotated fields that are related to the database
	 * and put them into map<columnName,fieldName>
	 */
	private void getAnnotatedFields() {
		// Taking all fields including inherited from current entity class
		List<Field> fields = getInheritedFields(this.clazz);
		for (Field field : fields) {
			putInMap(field);
		}
	}

	private void putInMap(Field field) {
		if (field != null) {
			if (field.isAnnotationPresent(Column.class)) {
				this.nameField.put(field.getAnnotation(Column.class).name(), field);
			}
		}
	}

	/*
	 * This method returns a list of all fields including inherited fields of an
	 * entity and is used as help method to the getAnnotatedFields() method.
	 */
	private List<Field> getInheritedFields(Class<?> clazz) {
		ArrayList<Field> fields = new ArrayList<Field>(1);
		for (Class<?> c = clazz; c != Object.class; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

}
