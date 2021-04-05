package mx.gob.eventosComunitarios.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oferta")
public class Oferta {
	@Id
	@Column(name="idOferta")
	private long idOferta;
	
	@Column(name="fechaInicio")
	private Date fechaInicio;
	
	@Column(name="fechaFin")
	private Date fechaFin;
	
	@Column(name="fechaInicioRegistro")
	private Date fechaInicioRegistro;
	
	@Column(name="fechaFinRegistro")
	private Date fechaFinRegistro;
	
	@Column(name="evento")
	private Evento evento;
	
	@Column(name="horario")
	private Horario horario;
	
	@Column(name="status")
	private long status;

	public Oferta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Oferta(Date fechaInicio, Date fechaFin, Date fechaInicioRegistro, Date fechaFinRegistro, Evento evento,
			Horario horario, long status) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaInicioRegistro = fechaInicioRegistro;
		this.fechaFinRegistro = fechaFinRegistro;
		this.evento = evento;
		this.horario = horario;
		this.status = status;
	}

	public long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicioRegistro() {
		return fechaInicioRegistro;
	}

	public void setFechaInicioRegistro(Date fechaInicioRegistro) {
		this.fechaInicioRegistro = fechaInicioRegistro;
	}

	public Date getFechaFinRegistro() {
		return fechaFinRegistro;
	}

	public void setFechaFinRegistro(Date fechaFinRegistro) {
		this.fechaFinRegistro = fechaFinRegistro;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}
}
