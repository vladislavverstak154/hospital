package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.GenericTypeResolver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.vvs.training.hospital.daodb.GenericDao;
import com.vvs.training.hospital.daodb.sql.SqlProcessor;
import com.vvs.training.hospital.datamodel.AbstractModel;

public abstract class GenericDaoImpl<T extends AbstractModel> implements GenericDao<T> {

	protected Class<T> getClazz() {
		return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
	}

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private SimpleJdbcInsert insertEntity;
	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public T get(Long id) {
		String sql = String.format("SELECT * FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		return (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(this.getClazz()));
	}
	
	
	public void insert(T entity){
		this.insertEntity.withTableName(this.getClazz().getSimpleName()).usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(entity);
        Number newId = insertEntity.executeAndReturnKey(parameters);
        entity.setId(newId.longValue());
	}
	
	@Override
	public void update(T entity) throws Exception{
		String sql =new SqlProcessor(entity).updateSql();
	    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
	    this.namedParameterJdbcTemplate.update(sql, namedParameters);
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
	
	/**public  <K> K getByField(K value){
		String sql=sqlProcessor.get
		
		return kObject;
	} */

}
