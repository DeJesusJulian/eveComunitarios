package mx.gob.eventosComunitarios.dao;

import java.util.List;

import mx.gob.eventosComunitarios.entity.InscripcionPersona;

public interface InscripcionPersonaInterface {
	public void save(InscripcionPersona people);
	
	public void delete(long id);
	
	public List<InscripcionPersona> findAll();
	
	public long countPeople(); //contar las personas 
	
	public InscripcionPersona findByUser(long idUser, long idEvent); //evitar repeticiones
}
