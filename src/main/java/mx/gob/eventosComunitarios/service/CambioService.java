package mx.gob.eventosComunitarios.service;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.eventosComunitarios.dao.CambioInterface;
import mx.gob.eventosComunitarios.entity.Cambio;

public class CambioService implements CambioInterface{
	
	private JdbcTemplate jdbc;
	String sql;
	
	public CambioService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void save(Cambio change) {
		sql ="INSERT INTO cambio(descripcion, fecha, host, response)values(?,?,?,?)";
		jdbc.update(sql, change.getDescripcion(), change.getFecha(), change.getHost(), change.getResponse());
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cambio> findAll() {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM cambio";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Cambio.class));
	}

	@Override
	public List<Cambio> findByDate(Date date) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM cambio where fecha = ?";
		return this.jdbc.query(sql, new Object[] {date}, BeanPropertyRowMapper.newInstance(Cambio.class));
	}

	@Override
	public List<Cambio> findByResponse(String response) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM cambio where response = ?";
		return this.jdbc.query(sql, new Object[] {response},  BeanPropertyRowMapper.newInstance(Cambio.class));
	}

	@Override
	public List<Cambio> findByHost(String host) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM cambio where host = ?";
		return this.jdbc.query(sql, new Object[] {host}, BeanPropertyRowMapper.newInstance(Cambio.class));
	}
	
}
