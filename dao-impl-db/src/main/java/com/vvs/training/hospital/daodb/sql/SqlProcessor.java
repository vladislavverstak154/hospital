package com.vvs.training.hospital.daodb.sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Id;

public class SqlProcessor<T> {

	private T entity;
	private Class clazz;
	private Map<String, Field> nameField;
	private Set<String> columnNames;
	private Long id;

	public SqlProcessor(T entity) throws IllegalArgumentException, IllegalAccessException {

		this.entity = entity;
		this.clazz = entity.getClass();
		this.getAnnotatedFields();
		this.columnNames = this.nameField.keySet();
	}

	public <K> String getByFieldSql(String field, K value) {

		String sql = null;

		try {
			sql = new String("SELECT FROM " + clazz.getSimpleName() + "WHERE "
					+ this.clazz.getDeclaredField(field).getAnnotation(Column.class).name());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return sql;

	}

	public String updateSql() {
		Iterator<String> iterator = columnNames.iterator();
		StringBuffer values=new StringBuffer();
		while(iterator.hasNext()){
		values.append(iterator.next()+"="+nameField.get(iterator.next()).getName());
		}
		String sql = new String("UPDATE " + this.clazz.getSimpleName() + " SET("+values.toString()+")" + " WHERE id=" + this.id);				
		return sql;
	}

	// This method select only annotated fields that are related to database and
	// put them into map<columnName,fieldName>
	private void getAnnotatedFields() {
		ArrayList<Field> fieldsA = getInheritedFields(this.clazz);
		ArrayList<Field> fieldsB = new ArrayList<>(1);
		this.nameField = new TreeMap<String, Field>();
		for (Field field : fieldsA) {
			if (field.isAnnotationPresent(Column.class)) {
				this.nameField.put(field.getAnnotation(Column.class).name(), field);
				fieldsB.add(field);
			}
		}
	}

	// This method returns all fields of the Entity type
	public static ArrayList<Field> getInheritedFields(Class<?> clazz) {
		ArrayList<Field> fields = new ArrayList<Field>(1);
		for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

}
