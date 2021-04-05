package mx.gob.eventosComunitarios.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.eventosComunitarios.dao.RolInterface;
import mx.gob.eventosComunitarios.entity.Rol;

public class RolService implements RolInterface{

	private JdbcTemplate jdbc;
	String sql;
		
	public RolService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		sql="SELECT * FROM rol";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Rol.class));
	}

	@Override
	public List<Rol> findNotAdmin() {
		sql="select * from rol where nombre != 'Administrador'";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Rol.class));
	}

}
