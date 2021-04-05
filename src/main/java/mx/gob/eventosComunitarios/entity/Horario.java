package mx.gob.eventosComunitarios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horario")
public class Horario {
	@Id
	@Column(name="idHorarios")
	private long idHorarios;
	
	@Column(name="dia")
	private String dia;
	
	@Column(name="horaInicio")
	private String horaInicio;
	
	@Column(name="horaFin")
	private String horaFin;
	
	@Column(name="status")
	private long status;

	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Horario(String dia, String horaInicio, String horaFin, long status) {
		super();
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.status = status;
	}



	public long getIdHorarios() {
		return idHorarios;
	}

	public void setIdHorarios(long idHorarios) {
		this.idHorarios = idHorarios;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public void setHoraFin(long status) {
		this.status = status;
	}
	
	public void setStatus(long status) {
		this.status = status;
	}
	
	public long getStatus() {
		return status;
	}
}
