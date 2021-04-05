package mx.gob.eventosComunitarios.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.eventosComunitarios.dao.TipoInterface;
import mx.gob.eventosComunitarios.entity.Tipo;

public class TipoService implements TipoInterface{

	private JdbcTemplate jdbc;
	String sql;
	
	
	public TipoService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Tipo> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM tipo";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Tipo.class));
	}

}
