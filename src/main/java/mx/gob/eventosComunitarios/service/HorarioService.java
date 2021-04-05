package mx.gob.eventosComunitarios.service;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import mx.gob.eventosComunitarios.dao.HorarioInterface;
import mx.gob.eventosComunitarios.entity.Horario;

public class HorarioService implements HorarioInterface{

	private JdbcTemplate jdbc;
	String sql;
	
	public HorarioService(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.jdbc = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void save(Horario schedule) {
		// TODO Auto-generated method stub
		sql ="INSERT INTO horario(dia, horaInicio, horaFin)values(?,?,?)";
		jdbc.update(sql, schedule.getDia(), schedule.getHoraInicio(), schedule.getHoraFin());		
	}

	@Override
	public void update(Horario schedule) {
		// TODO Auto-generated method stub
		sql="update horario set dia =?, horaInicio = ?, horaFin = ? WHERE idHorarios = ?";
		jdbc.update(sql, schedule.getDia(), schedule.getHoraInicio(), schedule.getHoraFin(), schedule.getIdHorarios());
	}

	@Override
	public void deleteLogical(long id) {
		// TODO Auto-generated method stub
		sql ="DELETE FROM horario WHERE idHorarios = ?";
		jdbc.update(sql, id);
	}

	@Override
	public List<Horario> findAll() {
		// TODO Auto-generated method stub
		sql="SELECT * FROM horario";
		return this.jdbc.query(sql, BeanPropertyRowMapper.newInstance(Horario.class));
	}

	@Override
	public List<Horario> findActivate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Horario findById(long id) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM horario WHERE idHorarios = ?";
		return this.jdbc.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(Horario.class));
	}

	@Override
	public List<Horario> findByDay(String day) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM horario WHERE dia = ?";
		return this.jdbc.query(sql, new Object[] {day}, BeanPropertyRowMapper.newInstance(Horario.class));
	}

	@Override
	public List<Horario> findByHour(String hour) {
		// TODO Auto-generated method stub
		sql ="SELECT * FROM horario WHERE horaInicio = ?";
		return this.jdbc.query(sql, new Object[] {hour}, BeanPropertyRowMapper.newInstance(Horario.class));
	}

}
