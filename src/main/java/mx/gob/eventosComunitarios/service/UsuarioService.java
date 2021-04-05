package mx.gob.eventosComunitarios.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mx.gob.eventosComunitarios.dao.UsuarioInterface;
import mx.gob.eventosComunitarios.entity.Evento;
import mx.gob.eventosComunitarios.entity.Modalidad;
import mx.gob.eventosComunitarios.entity.Persona;
import mx.gob.eventosComunitarios.entity.Rol;
import mx.gob.eventosComunitarios.entity.Tipo;
import mx.gob.eventosComunitarios.entity.Usuario;

public class UsuarioService implements UsuarioInterface {

	private JdbcTemplate jdbc;
	String sql;

	public UsuarioService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Usuario user) {
		// TODO Auto-generated method stub
		sql = "insert into (correo, contrasena, rol, persona, status)values(?,?,?,?,?)";
		jdbc.update(sql, user.getCorreo(), user.getContrasena(), user.getRol(), user.getPersona(), user.getStatus());
	}

	@Override
	public void update(Usuario user) {
		// TODO Auto-generated method stub
		sql = "update usuario set correo=?, contrasena=?, rol=?, persona=?, status? WHERE idUsuario = ?";
		jdbc.update(sql, user.getCorreo(), user.getContrasena(), user.getRol(), user.getPersona(), user.getStatus(),
				user.getIdUsuario());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "Delete From usuario WHERE idUsuario= ?";
		jdbc.update(sql, id);
	}

	/*
	 * @Override public List<Usuario> findAll() { // TODO Auto-generated method stub
	 * sql ="select * from usuario"; return this.jdbc.query(sql,
	 * BeanPropertyRowMapper.newInstance(Usuario.class)); }
	 */

	// prueba
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		sql = "select * from usuario " + "inner join rol on usuario.rol = idRol "
				+ "inner join persona on usuario.persona = idPersona;";

		List<Usuario> usuarios = this.jdbc.query(sql, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario u = new Usuario();

				u.setIdUsuario(rs.getLong("idUsuario"));
				u.setCorreo(rs.getString("correo"));
				u.setContrasena(rs.getString("contrasena"));
				u.setStatus(new Long(rs.getInt("status")));

				Rol r = new Rol();
				r.setIdRol(rs.getLong("idRol"));
				r.setNombre(rs.getString("rol.nombre"));

				Persona p = new Persona();
				p.setIdPersona(rs.getLong("idPersona"));
				p.setNombre(rs.getString("persona.nombre"));
				p.setApellidoPaterno(rs.getString("apellidoPaterno"));
				p.setApellidoMaterno(rs.getString("apellidoMaterno"));
				p.setTelefono(rs.getString("telefono"));
				p.setEdad(rs.getLong("edad"));
				p.setGenero(rs.getLong("genero"));
				p.setDireccion(rs.getString("direccion"));

				u.setRol(r);
				u.setPersona(p);

				return u;
			}

		});
		return usuarios;
	}
	/*
	 * @Override public Usuario findById(long id) { // TODO Auto-generated method
	 * stub sql="select * from usuario WHERE idUsuario = ?"; return
	 * this.jdbc.queryForObject(sql, new Object[] {id},
	 * BeanPropertyRowMapper.newInstance(Usuario.class)); }
	 */

	@Override
	public Usuario findById(long id) {
		// TODO Auto-generated method stub

		sql = "select * from usuario "
				+ "inner join rol on usuario.rol = idRol "
				+ "inner join persona on usuario.persona = idPersona "
				+ "Where idUsuario = ?;";
		Usuario usuario = jdbc.queryForObject(sql, new Object[] { id }, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario u = new Usuario();
				u.setIdUsuario(rs.getLong("idUsuario"));
				u.setCorreo(rs.getString("correo"));
				u.setContrasena(rs.getString("contrasena"));
				u.setStatus(rs.getLong("status"));

				Rol r = new Rol();
				r.setIdRol(new Long(rs.getInt("idRol")));
				r.setNombre(rs.getString("rol.nombre"));

				Persona p = new Persona();
				p.setIdPersona(new Long(rs.getInt("idPersona")));
				p.setNombre(rs.getString("persona.nombre"));

				p.setApellidoPaterno(rs.getString("apellidoPaterno"));
				p.setApellidoMaterno(rs.getString("apellidoMaterno"));
				p.setTelefono(rs.getString("telefono"));
				p.setEdad(rs.getInt("edad"));
				p.setGenero(rs.getInt("genero"));
				p.setDireccion(rs.getString("direccion"));

				u.setPersona(p);
				u.setRol(r);

				return u;
			}
		});

		return usuario;
	}

	@Override
	public List<Usuario> findByStatus(long status) {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM usuario WHERE status = ?";
		return this.jdbc.query(sql, new Object[] { status }, BeanPropertyRowMapper.newInstance(Usuario.class));
	}

	@Override
	public Usuario findByCorreo(String email) {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM usuario WHERE correo = ?";
		return this.jdbc.queryForObject(sql, new Object[] { email }, BeanPropertyRowMapper.newInstance(Usuario.class));
	}

}
