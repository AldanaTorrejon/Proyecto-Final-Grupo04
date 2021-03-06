package ar.edu.unju.edm.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table (name ="pelicula")
@Component
public class Pelicula {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "Idp", nullable = true)
	private int Idp;
	
	@Size(min=2, max=50, message="EL nombre debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El nombre no puede ser espacios en blanco")
	@NotEmpty(message="El nombre no puede estar vacio")
	private String NombrePelicula;
	
	@Size(min=2, max=100, message="La descripcion debe tener 2 caracteres minimo, maximo 100")
	@NotBlank(message="La descripcion no puede ser espacios en blanco")
	@NotEmpty(message="La descripcion no puede estar vacia")
	private String Despcripcion;
	
	@DateTimeFormat(pattern="hh:mm:ss")
	private LocalTime Duracion;
	
	private String Tipo;
	
	private int Sala;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate FechaInicio;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate FechaFinal;
	
	@DateTimeFormat(pattern="hh:mm")
	private LocalTime Horario1;
	
	@DateTimeFormat(pattern="hh:mm")
	private LocalTime Horario2;
	
	@DateTimeFormat(pattern="hh:mm")
	private LocalTime Horario3;
	
	private Boolean Estado;

	public int getIdp() {
		return Idp;
	}

	public void setIdp(int idp) {
		Idp = idp;
	}

	public String getNombrePelicula() {
		return NombrePelicula;
	}

	public void setNombrePelicula(String nombrePelicula) {
		NombrePelicula = nombrePelicula;
	}

	public String getDespcripcion() {
		return Despcripcion;
	}

	public void setDespcripcion(String despcripcion) {
		Despcripcion = despcripcion;
	}

	public LocalTime getDuracion() {
		return Duracion;
	}

	public void setDuracion(LocalTime duracion) {
		Duracion = duracion;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public int getSala() {
		return Sala;
	}

	public void setSala(int sala) {
		Sala = sala;
	}

	public LocalDate getFechaInicio() {
		return FechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		FechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinal() {
		return FechaFinal;
	}

	public void setFechaFinal(LocalDate fechaFinal) {
		FechaFinal = fechaFinal;
	}

	public LocalTime getHorario1() {
		return Horario1;
	}

	public void setHorario1(LocalTime horario1) {
		Horario1 = horario1;
	}

	public LocalTime getHorario2() {
		return Horario2;
	}

	public void setHorario2(LocalTime horario2) {
		Horario2 = horario2;
	}

	public LocalTime getHorario3() {
		return Horario3;
	}

	public void setHorario3(LocalTime horario3) {
		Horario3 = horario3;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	
	//portada
	@Lob
	private String imagen;
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@Min(value=0,message="El duracion en horas debe ser mayor que 0")
	@Max(value=857,message="El duracion en horas debe ser menor que 857")
	private Integer duracionPelicula;
	public Integer getDuracionPelicula() {
		return duracionPelicula;
	}

	public void setDuracionPelicula(Integer duracionPelicula) {
		this.duracionPelicula = duracionPelicula;
	}

}
