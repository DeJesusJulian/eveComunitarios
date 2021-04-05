package mx.gob.eventosComunitarios.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inscripcionEquipo")
public class InscripcionEquipo {
	@Id
	@Column(name="idEquipo")
	private long idEquipo;
	
	@Column(name="usuario")
	private Usuario usuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="pago")
	private long pago;
	
	@Column(name="fechaInscripcion")
	private Date fechaInscripcion;
	
	@Column(name="oferta")
	private Oferta oferta;

	public InscripcionEquipo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InscripcionEquipo(Usuario usuario, String nombre, long pago, Date fechaInscripcion, Oferta oferta) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.pago = pago;
		this.fechaInscripcion = fechaInscripcion;
		this.oferta = oferta;
	}

	public long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getPago() {
		return pago;
	}

	public void setPago(long pago) {
		this.pago = pago;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
}
