package com.vvs.training.hospital.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.GenericTypeResolver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.vvs.training.hospital.daodb.GenericDao;
import com.vvs.training.hospital.daodb.sql.SqlProcessor;
import com.vvs.training.hospital.datamodel.AbstractModel;

public abstract class GenericDaoImpl<T extends AbstractModel> implements GenericDao<T> {

	protected Class<T> getClazz() {
		return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
	}

	@Inject
	private JdbcTemplate jdbcTemplate;

	public T get(Long id) {
		String sql = String.format("SELECT * FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		return (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(this.getClazz()));
	}
	
	
		
	@Override
	public void insert(T entity) throws IllegalArgumentException, IllegalAccessException{
		final SqlProcessor<T> sqlProcessor=new SqlProcessor<T>(entity);
		final String INSERT_SQL=sqlProcessor.insertSql();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
		            try {
		        		ps=sqlProcessor.setArgs(ps);
					} catch (Exception e) {}
		            return ps;
		        }
		    },
		    keyHolder);
		Long i=keyHolder.getKey().longValue();
		entity.setId(i);
	};
	
	@Override
	public void update(T entity) throws Exception{
		final SqlProcessor<T> sqlProcessor=new SqlProcessor<T>(entity);
		final String INSERT_SQL=sqlProcessor.updateSql();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
		            try {
		        		ps=sqlProcessor.setArgs(ps);
					} catch (Exception e) {System.out.println("SqlProcessor troubles");}
		            return ps;
		        }
		    });
	}
		
	@Override
	public void delete(Long id) {
		String sql = String.format("DELETE FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		jdbcTemplate.execute(sql);
	}

		
	@Override
	public List<T> getAll() {
		String sql="SELECT * FROM "+this.getClazz().getSimpleName();
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
	}
	
	public  <K> K getByField(K value){
		String sql=sqlProcessor.get
		
		return kObject;
	}

}
