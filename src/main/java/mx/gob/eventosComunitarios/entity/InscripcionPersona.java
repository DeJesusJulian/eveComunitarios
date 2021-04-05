package mx.gob.eventosComunitarios.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inscripcionPersona")
public class InscripcionPersona {
	@Id
	@Column(name="idPersona")
	private long idPersona;
	
	@Column(name="usuario")
	private Usuario usuario;
	
	@Column(name="pago")
	private long pago;
	
	@Column(name="fechaInscripcion")
	private Date fechaInscripcion;
	
	@Column(name="oferta")
	private Oferta oferta;

	public InscripcionPersona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InscripcionPersona(Usuario usuario, long pago, Date fechaInscripcion, Oferta oferta) {
		super();
		this.usuario = usuario;
		this.pago = pago;
		this.fechaInscripcion = fechaInscripcion;
		this.oferta = oferta;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
