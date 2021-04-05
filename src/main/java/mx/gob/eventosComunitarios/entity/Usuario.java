package mx.gob.eventosComunitarios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	@Id
	@Column(name="idUsuario")
	private long idUsuario;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="contrasena")
	private String contrasena;
	
	@Column(name="rol")
	private Rol rol;
	
	@Column(name="persona")
	private Persona persona;
	
	@Column(name="status")
	private long status;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(String correo, String contrasena, Rol rol, Persona persona, long status) {
		super();
		this.correo = correo;
		this.contrasena = contrasena;
		this.rol = rol;
		this.persona = persona;
		this.status = status;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

}
