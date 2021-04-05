package mx.gob.eventosComunitarios.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cambio")
public class Cambio {
	@Id
	@Column(name="idCambios")
	private long idCambios;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="host")
	private String host;
	
	@Column(name="response")
	private String response;

	public Cambio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cambio(String descripcion, Date fecha, String host, String response) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.host = host;
		this.response = response;
	}

	public long getIdCambios() {
		return idCambios;
	}

	public void setIdCambios(long idCambios) {
		this.idCambios = idCambios;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
