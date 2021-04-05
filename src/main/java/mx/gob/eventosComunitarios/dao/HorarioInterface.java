package mx.gob.eventosComunitarios.dao;

import java.util.List;

import mx.gob.eventosComunitarios.entity.Horario;

public interface HorarioInterface {
	public void save(Horario schedule);
	
	public void update(Horario schedule);
	
	public void deleteLogical(long id);
	
	public List<Horario> findAll(); // para el admin
	
	public List<Horario> findActivate();
	
	public Horario findById(long id);
	
	// --- Filtros ---
	
	public List<Horario> findByDay(String day);
	
	public List<Horario> findByHour(String hour);
}
