package mx.gob.eventosComunitarios.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mx.gob.eventosComunitarios.dao.PersonaInterface;
import mx.gob.eventosComunitarios.entity.Evento;
import mx.gob.eventosComunitarios.entity.Modalidad;
import mx.gob.eventosComunitarios.entity.Persona;
import mx.gob.eventosComunitarios.entity.Tipo;

public class PersonaService implements PersonaInterface{

	private JdbcTemplate jdbc;
	String sql;
	
	public PersonaService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Persona person) {
		// TODO Auto-generated method stub
		sql = "INSERT INTO persona(nombre, apellidoPaterno, apellidoMaterno, telefono, edad, genero, direccion)values(?,?,?,?,?,?,?)";
		jdbc.update(sql, person.getNombre(), person.getApellidoPaterno(), person.getApellidoMaterno(), person.getTelefono(), person.getEdad(), person.getGenero(), person.getDireccion());
	}

	@Override
	public void update(Persona person) {
		// TODO Auto-generated method stub
		sql ="UPDATE persona SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, telefono=?, edad=?, genero=?, direccion=? WHERE idPersona=?";
		jdbc.update(sql, person.getNombre(), person.getApellidoPaterno(), person.getApellidoMaterno(), person.getTelefono(), person.getEdad(), person.getGenero(), person.getDireccion(),person.getIdPersona());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql ="DELETE FROM persona WHERE idPersona =?";
		jdbc.update(sql, id);
	}

	/*@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM persona";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Persona.class));
	}*/
	
	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM persona";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Persona.class));
	}
	
	/*@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		sql="select * from evento "
				+ "inner join tipo on evento.tipo = idTipo "
				+ "inner join modalidad on evento.modalidad = idModalidad;";
		
		List<Persona> personas = this.jdbc.query(sql, new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
				


				Persona p = new Persona();
				
				p.setIdPersona(rs.getLong("idPersona"));
				p.setNombre(rs.getString("nombre"));
				p.setApellidoPaterno(rs.getString("apellidoPaterno"));
				p.setApellidoMaterno(rs.getString("apellidoMaterno"));
				p.setTelefono(rs.getString("telefono"));
				p.setEdad(rs.getLong("edad"));
				p.setGenero(rs.getLong("genero"));
				p.setDireccion(rs.getString("direccion"));
			
				return p;
			}

		});
		
		return personas;
	}*/

	@Override
	public Persona findById(long id) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM persona WHERE idPersona =?";
		return this.jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Persona.class));
	}

}
