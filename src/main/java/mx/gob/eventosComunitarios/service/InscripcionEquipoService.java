package mx.gob.eventosComunitarios.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.eventosComunitarios.dao.InscripcionEquipoInterface;
import mx.gob.eventosComunitarios.entity.InscripcionEquipo;

public class InscripcionEquipoService implements InscripcionEquipoInterface{

	private JdbcTemplate jdbc;
	String sql;
	
	public InscripcionEquipoService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(InscripcionEquipo team,int usuario,int oferta) {
		// TODO Auto-generated method stub
		sql="INSERT INTO inscripcionequipo(usuario, nombre, pago, fechaInscripcion, oferta)values(?,?,?,?,?)";
		jdbc.update(sql, usuario, team.getNombre(), team.getPago(), team.getFechaInscripcion(), oferta);
	}

	@Override
	public void update(InscripcionEquipo team,int usuario,int oferta) {
		// TODO Auto-generated method stub
		sql ="UPDATE inscripcionequipo SET usuario = ?, nombre =?, pago=?, fechaInscripcion =?, oferta=? WHERE idEquipo = ?";
		jdbc.update(sql, usuario, team.getNombre(), team.getPago(), team.getFechaInscripcion(), oferta, team.getIdEquipo());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql ="DELETE FROM inscripcionequipo WHERE idEquipo = ?";
		jdbc.update(sql, id);
	}

	@Override
	public List<InscripcionEquipo> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM inscripcionequipo";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(InscripcionEquipo.class));
	}

	@Override
	public InscripcionEquipo findById(long id) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM inscripcionequipo WHERE idEquipo = ?";
		return this.jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(InscripcionEquipo.class));
	}

	@Override
	public InscripcionEquipo findByUser(long idUser, long idEvent) {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM inscripcionequipo WHERE usuario = ? AND evento = ?";
		return jdbc.queryForObject(sql, new Object[] {idUser, idEvent}, BeanPropertyRowMapper.newInstance(InscripcionEquipo.class));
	}

	@Override
	public long countPeople() {
		// TODO Auto-generated method stub
		sql ="SELECT COUNT(*) inscripcionequipo";
		return this.jdbc.queryForLong(sql, new Object[] {}, BeanPropertyRowMapper.newInstance(InscripcionEquipo.class));
	}

}
