package com.vvs.training.hospital.daodb.sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Id;

public class SqlProcessor<T> {

	private T entity;
	private Class clazz;
	private ArrayList<String> columnNames = new ArrayList<>(1);
	private String columnNamesRow;
	private String argPlaces;
	private ArrayList<String> datatypes = new ArrayList<>(1);
	private ArrayList<Field> fields = new ArrayList<>(1);
	private Long id;

	public SqlProcessor(T entity) throws IllegalArgumentException, IllegalAccessException {

		this.entity = entity;
		this.clazz = entity.getClass();
		this.fields=this.getAnnotatedFields();
		
	}
	
	public <K>String getByFieldSql(String field, K value){
		
		String sql=new String("SELECT FROM "+clazz.getSimpleName()+"WHERE "+fields.)
		
		return sql;
		
	}

	public String insertSql() {
		String sql = new String("INSERT INTO " + clazz.getSimpleName() + "(" + columnNamesRow + ") " + "VALUES("
				+ this.argPlaces + ")");
		return sql;
	}

	public String updateSql() {
		String sql = new String("UPDATE " + clazz.getSimpleName() + " SET(" + columnNamesRow + ")" + "=("
				+ this.argPlaces + ")" + " WHERE id=" + this.id);
		return sql;
	}

	public PreparedStatement setArgs(PreparedStatement statement) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int i = 1;
		Iterator<Field> iterator = this.fields.iterator();
		for (String datatype : this.datatypes) {
			Field field = iterator.next();
			Class clazz = statement.getClass();
			Method method = statement.getClass().getMethod(datatype, int.class, field.getType());
			method.setAccessible(true);
			field.setAccessible(true);
			method.invoke(statement, i, field.get(this.entity));
			i++;
		}
		
		return statement;
	}

	// returns column names divided by comma
	private void getColumnNames() throws IllegalArgumentException, IllegalAccessException {
		StringBuilder columnNames = new StringBuilder("");
		StringBuilder argPlaces = new StringBuilder("");
		Iterator<Field> iterator = getAnnotatedFields().iterator();
		while (iterator.hasNext()) {
			Field field = iterator.next();
			Column column = field.getAnnotation(Column.class);
			StringBuilder columnName = new StringBuilder(column.name());
			this.columnNames.add(columnName.toString());
			this.datatypes.add(column.datatype());
			this.fields.add(field);
			columnNames.append(columnName);
			if (iterator.hasNext()) {
				columnNames.append(',');
			}
			argPlaces.append('?');
			if (iterator.hasNext()) {
				argPlaces.append(',');
			}

		}
		this.columnNamesRow = columnNames.toString();
		this.argPlaces = argPlaces.toString();
	}

	private ArrayList<Field> getAnnotatedFields() throws IllegalArgumentException, IllegalAccessException {
		ArrayList<Field> fieldsA = getInheritedFields(this.clazz);
		ArrayList<Field> fieldsB = new ArrayList<>(1);
		for (Field field : fieldsA) {
			if (field.isAnnotationPresent(Id.class)) {
				field.setAccessible(true);
				this.id = (Long) field.get(this.entity);
			}
			if (field.isAnnotationPresent(Column.class)) {
				fieldsB.add(field);
				
			}
		}
		return fieldsB;
	}

	public static ArrayList<Field> getInheritedFields(Class<?> clazz) {
		ArrayList<Field> fields = new ArrayList<Field>(1);
		for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

}
