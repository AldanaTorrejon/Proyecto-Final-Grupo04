package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table (name ="pelicula")
@Component
public class Pelicula {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "Idp", nullable = true)
	private Long Idp;
	
	@Size(min=2, max=50, message="EL nombre debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El nombre no puede ser espacios en blanco")
	@NotEmpty(message="El nombre no puede estar vacio")
	private String NombrePelicula;
	
	@Size(min=2, max=100, message="La descripcion debe tener 2 caracteres minimo, maximo 100")
	@NotBlank(message="La descripcion no puede ser espacios en blanco")
	@NotEmpty(message="La descripcion no puede estar vacia")
	private String Descripcion;
	
	private String Duracion;

	private String generoPelicula;

	private Integer sala;

	public Integer getSala() {
		return sala;
	}
	public void setSala(Integer sala) {
		this.sala = sala;
	}
	private Boolean Estado;
	@Lob
	private String imagen;
	public Pelicula() {
	}
	public Long getIdp() {
		return Idp;
	}
	public void setIdp(Long idp) {
		Idp = idp;
	}
	public String getNombrePelicula() {
		return NombrePelicula;
	}
	public void setNombrePelicula(String nombrePelicula) {
		NombrePelicula = nombrePelicula;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getDuracion() {
		return Duracion;
	}
	public void setDuracion(String duracion) {
		Duracion = duracion;
	}
	public String getGeneroPelicula() {
		return generoPelicula;
	}
	public void setGeneroPelicula(String generoPelicula) {
		this.generoPelicula = generoPelicula;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
