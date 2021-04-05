package mx.gob.eventosComunitarios.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.eventosComunitarios.dao.InscripcionPersonaInterface;
import mx.gob.eventosComunitarios.entity.InscripcionPersona;

public class InscripcionPersonaService implements InscripcionPersonaInterface{

	private JdbcTemplate jdbc;
	String sql;
	
	public InscripcionPersonaService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(InscripcionPersona people) {
		// TODO Auto-generated method stub
		sql ="INSERT INTO inscripcionpersona(usuario, pago, fechaInscripcion, oferta)values(?,?,?,?)";
		jdbc.update(sql, people.getUsuario(), people.getPago(), people.getFechaInscripcion(), people.getOferta());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "DELETE FROM inscripcionpersona WHERE idPersona = ?";
		jdbc.update(sql, id);
	}

	@Override
	public List<InscripcionPersona> findAll() {
		// TODO Auto-generated method stub
		sql="SELECT * FROM inscripcionpersona";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(InscripcionPersona.class));
	}

	@Override
	public long countPeople() {
		// TODO Auto-generated method stub
		sql="SELECT COUNT(*) FROM inscripcionpersona";
		return this.jdbc.queryForLong(sql);
	}

	@Override
	public InscripcionPersona findByUser(long idUser, long idEvent) {
		// TODO Auto-generated method stub
		sql ="SELECT * fROM inscripcionpersona WHERE usuario =? AND evento = ?";
		return this.jdbc.queryForObject(sql, new Object[] {idUser, idEvent}, BeanPropertyRowMapper.newInstance(InscripcionPersona.class));
	}

}
