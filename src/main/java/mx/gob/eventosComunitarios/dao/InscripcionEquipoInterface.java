package mx.gob.eventosComunitarios.dao;

import java.util.List;

import mx.gob.eventosComunitarios.entity.InscripcionEquipo;

public interface InscripcionEquipoInterface {
	public void save(InscripcionEquipo team, int usuario,int oferta);
	
	public void update(InscripcionEquipo team,int usuario,int oferta);
	
	public void delete(long id); // conocido en la app como cancelar inscripcion
	
	public List<InscripcionEquipo> findAll(); //por si se necesita
	
	public InscripcionEquipo findById(long id);
	
	public InscripcionEquipo findByUser(long idUser, long idEvent); //evitar repeticiones
	
	public long countPeople(); //ver cuantas personas hay
}
