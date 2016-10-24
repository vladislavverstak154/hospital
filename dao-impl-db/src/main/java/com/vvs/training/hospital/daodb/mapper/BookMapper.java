package com.vvs.training.hospital.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.training.library.datamodel.Book;

public final class PatientMapper implements  {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        Book entity = new Book();
        entity.setId(id);
        entity.setTitle(title);
        return entity;
    }
}