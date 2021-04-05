package mx.gob.eventosComunitarios.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mx.gob.eventosComunitarios.dao.OfertaInterface;
import mx.gob.eventosComunitarios.entity.Evento;
import mx.gob.eventosComunitarios.entity.Horario;
import mx.gob.eventosComunitarios.entity.Modalidad;
import mx.gob.eventosComunitarios.entity.Oferta;
import mx.gob.eventosComunitarios.entity.Tipo;

public class OfertaService implements OfertaInterface {

	private JdbcTemplate jdbc;
	String sql;

	public OfertaService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(Oferta promo) {
		// TODO Auto-generated method stub
		sql = "INSERT INTO oferta(fechaInicio, fechaFin, fechaInicioRegistro, fechaFinRegistro, evento, horario, status)values(?,?,?,?,?,?,?)";
		jdbc.update(sql, promo.getFechaInicio(), promo.getFechaFin(), promo.getFechaInicioRegistro(),
				promo.getFechaFinRegistro(), 1, 1, promo.getStatus());
	}

	@Override
	public void update(Oferta promo, int evento, int horario) {
		// TODO Auto-generated method stub
		sql = "UPDATE oferta SET fechaInicio=?, fechaFin=?, fechaInicioRegistro =?, fechaFinRegistro=?, evento=?, horario=?, status=? where idOferta=?";
		jdbc.update(sql, promo.getFechaInicio(), promo.getFechaFin(), promo.getFechaInicioRegistro(),
				promo.getFechaFinRegistro(), evento, horario, promo.getStatus(), promo.getIdOferta());
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		sql = "DELETE FROM oferta WHERE idOferta=?";
		jdbc.update(sql, id);
	}

	@Override
	public List<Oferta> findAll() {
		// TODO Auto-generated method stub
		sql = "select * from oferta " + "inner join evento on evento = idEvento " + "inner join tipo on tipo = idTipo "
				+ "inner join modalidad on modalidad = idModalidad " + "inner join horario on horario = idHorarios";
		List<Oferta> ofertas = this.jdbc.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

				Oferta o = new Oferta();
				o.setIdOferta(new Long(rs.getInt("idOferta")));
				o.setFechaInicio(rs.getDate("fechaInicio"));
				o.setFechaFin(rs.getDate("fechaFin"));
				o.setFechaInicioRegistro(rs.getDate("fechaInicioRegistro"));
				;
				o.setFechaFinRegistro(rs.getDate("fechaFinRegistro"));
				o.setStatus(new Long(rs.getInt("status")));

				Evento e = new Evento();
				e.setIdEvento(rs.getLong("idEvento"));
				e.setNombre(rs.getString("evento.nombre"));
				e.setCapacidadMinima(new Long(rs.getInt("capacidadMinima")));
				e.setCapacidadMaxima(new Long(rs.getInt("capacidadMaxima")));
				e.setCosto(rs.getDouble("costo"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setEdadMinima(new Long(rs.getInt("edadMinima")));
				e.setPublicoObjetivo(rs.getString("publicoObjetivo"));
				e.setStatus(new Long(rs.getInt("status")));
				Modalidad m = new Modalidad();
				m.setIdModalidad(new Long(rs.getInt("idModalidad")));
				m.setNombre(rs.getString("modalidad.nombre"));

				Tipo t = new Tipo();
				t.setIdTipo(new Long(rs.getInt("idTipo")));
				t.setNombre(rs.getString("tipo.nombre"));

				e.setModalidad(m);
				e.setTipo(t);
				o.setEvento(e);

				Horario h = new Horario();
				h.setIdHorarios(new Long(rs.getInt("idHorarios")));
				h.setDia(rs.getString("dia"));
				h.setHoraInicio(rs.getString("horaInicio"));
				h.setHoraFin(rs.getString("horaFin"));
				o.setHorario(h);
				return o;
			}

		});
		return ofertas;
	}

	@Override
	public Oferta findById(long id) {
		// TODO Auto-generated method stub
		sql = "select * from oferta " + "inner join evento on evento = idEvento " + "inner join tipo on tipo = idTipo "
				+ "inner join modalidad on modalidad = idModalidad "
				+ "inner join horario on horario = idHorarios where idOferta = ?";
		Oferta oferta = this.jdbc.queryForObject(sql, new Object[] { id }, new RowMapper<Oferta>() {

			@Override
			public Oferta mapRow(ResultSet rs, int rowNum) throws SQLException {
				Oferta o = new Oferta();
				o.setIdOferta(new Long(rs.getInt("idOferta")));
				o.setFechaInicio(rs.getDate("fechaInicio"));
				o.setFechaFin(rs.getDate("fechaFin"));
				o.setFechaInicioRegistro(rs.getDate("fechaInicioRegistro"));
				;
				o.setFechaFinRegistro(rs.getDate("fechaFinRegistro"));
				o.setStatus(new Long(rs.getInt("status")));

				Evento e = new Evento();
				e.setIdEvento(rs.getLong("idEvento"));
				e.setNombre(rs.getString("evento.nombre"));
				e.setCapacidadMinima(new Long(rs.getInt("capacidadMinima")));
				e.setCapacidadMaxima(new Long(rs.getInt("capacidadMaxima")));
				e.setCosto(rs.getDouble("costo"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setEdadMinima(new Long(rs.getInt("edadMinima")));
				e.setPublicoObjetivo(rs.getString("publicoObjetivo"));
				e.setStatus(new Long(rs.getInt("status")));
				Modalidad m = new Modalidad();
				m.setIdModalidad(new Long(rs.getInt("idModalidad")));
				m.setNombre(rs.getString("modalidad.nombre"));

				Tipo t = new Tipo();
				t.setIdTipo(new Long(rs.getInt("idTipo")));
				t.setNombre(rs.getString("tipo.nombre"));

				e.setModalidad(m);
				e.setTipo(t);
				o.setEvento(e);
				Horario h = new Horario();
				h.setIdHorarios(new Long(rs.getInt("idHorarios")));
				h.setDia(rs.getString("dia"));
				h.setHoraInicio(rs.getString("horaInicio"));
				h.setHoraFin(rs.getString("horaFin"));
				o.setHorario(h);
				return o;
			}

		});
		return oferta;
	}

	@Override
	public List<Oferta> findByDate(Date date) {
		// TODO Auto-generated method stub
		sql = "select * from oferta " + "inner join evento on evento = idEvento " + "inner join tipo on tipo = idTipo "
				+ "inner join modalidad on modalidad = idModalidad "
				+ "inner join horario on horario = idHorarios where fechaInicio = ?";
		List<Oferta> ofertas = this.jdbc.query(sql, new Object[] { date }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Oferta o = new Oferta();
				o.setIdOferta(new Long(rs.getInt("idOferta")));
				o.setFechaInicio(rs.getDate("fechaInicio"));
				o.setFechaFin(rs.getDate("fechaFin"));
				o.setFechaInicioRegistro(rs.getDate("fechaInicioRegistro"));
				;
				o.setFechaFinRegistro(rs.getDate("fechaFinRegistro"));
				o.setStatus(new Long(rs.getInt("status")));

				Evento e = new Evento();
				e.setIdEvento(rs.getLong("idEvento"));
				e.setNombre(rs.getString("evento.nombre"));
				e.setCapacidadMinima(new Long(rs.getInt("capacidadMinima")));
				e.setCapacidadMaxima(new Long(rs.getInt("capacidadMaxima")));
				e.setCosto(rs.getDouble("costo"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setEdadMinima(new Long(rs.getInt("edadMinima")));
				e.setPublicoObjetivo(rs.getString("publicoObjetivo"));
				e.setStatus(new Long(rs.getInt("status")));
				Modalidad m = new Modalidad();
				m.setIdModalidad(new Long(rs.getInt("idModalidad")));
				m.setNombre(rs.getString("modalidad.nombre"));

				Tipo t = new Tipo();
				t.setIdTipo(new Long(rs.getInt("idTipo")));
				t.setNombre(rs.getString("tipo.nombre"));

				e.setModalidad(m);
				e.setTipo(t);
				o.setEvento(e);
				Horario h = new Horario();
				h.setIdHorarios(new Long(rs.getInt("idHorarios")));
				h.setDia(rs.getString("dia"));
				h.setHoraInicio(rs.getString("horaInicio"));
				h.setHoraFin(rs.getString("horaFin"));
				o.setHorario(h);
				return o;
			}

		});
		return ofertas;
	}

	@Override
	public List<Oferta> findByEvento(Evento event) {
		// TODO Auto-generated method stub
		sql = "select * from oferta " + "inner join evento on evento = idEvento " + "inner join tipo on tipo = idTipo "
				+ "inner join modalidad on modalidad = idModalidad "
				+ "inner join horario on horario = idHorarios where evento = ?";
		List<Oferta> ofertas = this.jdbc.query(sql, new Object[] { event }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Oferta o = new Oferta();
				o.setIdOferta(new Long(rs.getInt("idOferta")));
				o.setFechaInicio(rs.getDate("fechaInicio"));
				o.setFechaFin(rs.getDate("fechaFin"));
				o.setFechaInicioRegistro(rs.getDate("fechaInicioRegistro"));
				;
				o.setFechaFinRegistro(rs.getDate("fechaFinRegistro"));
				o.setStatus(new Long(rs.getInt("status")));

				Evento e = new Evento();
				e.setIdEvento(rs.getLong("idEvento"));
				e.setNombre(rs.getString("evento.nombre"));
				e.setCapacidadMinima(new Long(rs.getInt("capacidadMinima")));
				e.setCapacidadMaxima(new Long(rs.getInt("capacidadMaxima")));
				e.setCosto(rs.getDouble("costo"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setEdadMinima(new Long(rs.getInt("edadMinima")));
				e.setPublicoObjetivo(rs.getString("publicoObjetivo"));
				e.setStatus(new Long(rs.getInt("status")));
				Modalidad m = new Modalidad();
				m.setIdModalidad(new Long(rs.getInt("idModalidad")));
				m.setNombre(rs.getString("modalidad.nombre"));

				Tipo t = new Tipo();
				t.setIdTipo(new Long(rs.getInt("idTipo")));
				t.setNombre(rs.getString("tipo.nombre"));

				e.setModalidad(m);
				e.setTipo(t);
				o.setEvento(e);
				Horario h = new Horario();
				h.setIdHorarios(new Long(rs.getInt("idHorarios")));
				h.setDia(rs.getString("dia"));
				h.setHoraInicio(rs.getString("horaInicio"));
				h.setHoraFin(rs.getString("horaFin"));
				o.setHorario(h);
				return o;
			}

		});
		return ofertas;
	}

	@Override
	public List<Oferta> findByStatus(long status) {
		// TODO Auto-generated method stub
		sql = "select * from oferta " + "inner join evento on evento = idEvento " + "inner join tipo on tipo = idTipo "
				+ "inner join modalidad on modalidad = idModalidad "
				+ "inner join horario on horario = idHorarios where status = ?";
		List<Oferta> ofertas = this.jdbc.query(sql, new Object[] { status }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Oferta o = new Oferta();
				o.setIdOferta(new Long(rs.getInt("idOferta")));
				o.setFechaInicio(rs.getDate("fechaInicio"));
				o.setFechaFin(rs.getDate("fechaFin"));
				o.setFechaInicioRegistro(rs.getDate("fechaInicioRegistro"));
				;
				o.setFechaFinRegistro(rs.getDate("fechaFinRegistro"));
				o.setStatus(new Long(rs.getInt("status")));

				Evento e = new Evento();
				e.setIdEvento(rs.getLong("idEvento"));
				e.setNombre(rs.getString("evento.nombre"));
				e.setCapacidadMinima(new Long(rs.getInt("capacidadMinima")));
				e.setCapacidadMaxima(new Long(rs.getInt("capacidadMaxima")));
				e.setCosto(rs.getDouble("costo"));
				e.setDescripcion(rs.getString("descripcion"));
				e.setEdadMinima(new Long(rs.getInt("edadMinima")));
				e.setPublicoObjetivo(rs.getString("publicoObjetivo"));
				e.setStatus(new Long(rs.getInt("status")));
				Modalidad m = new Modalidad();
				m.setIdModalidad(new Long(rs.getInt("idModalidad")));
				m.setNombre(rs.getString("modalidad.nombre"));

				Tipo t = new Tipo();
				t.setIdTipo(new Long(rs.getInt("idTipo")));
				t.setNombre(rs.getString("tipo.nombre"));

				e.setModalidad(m);
				e.setTipo(t);
				o.setEvento(e);
				Horario h = new Horario();
				h.setIdHorarios(new Long(rs.getInt("idHorario")));
				h.setDia(rs.getString("dia"));
				h.setHoraInicio(rs.getString("horaInicio"));
				h.setHoraFin(rs.getString("horaFin"));
				o.setHorario(h);
				return o;
			}

		});
		return ofertas;
	}

}
