package mx.gob.eventosComunitarios.dao;

import java.util.List;

import mx.gob.eventosComunitarios.entity.Persona;

public interface PersonaInterface {
	public void save(Persona person);
	
	public void update(Persona person);
	
	public void delete(long id);
	
	public List<Persona> findAll();
	
	public Persona findById(long id);
}
