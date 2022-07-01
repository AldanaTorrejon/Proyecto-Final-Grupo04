package ar.edu.unju.edm.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table (name ="ListaUsuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "ID", nullable = true)
	private int Id;

	@NotEmpty //@Email
	private String email;
	
	private String tipo;
	
	@Size(min=4, max=30, message="La contraseña debe tener 4 caracteres minimo, maximo 30")
	@NotBlank(message="La contraseña no puede ser espacios en blanco")
	private String contraseña;
	
	@Size(min=2, max=50, message="El apellido debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El apellido no puede ser espacios en blanco")
	@NotEmpty(message="El apellido no puede estar vacio")
	private String apellido;
	
	@Size(min=2, max=50, message="EL nombre debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El nombre no puede ser espacios en blanco")
	@NotEmpty(message="El nombre no puede estar vacio")
	private String nombre;
	
	@Min(value=1000000, message="El dni debe ser mayor a 1.000.000")
	@Max(value=99999999, message="El dni debe ser menor a 99.999.999")
	private int dni;
	
	@Past(message="La fecha de nacimiento debe ser del pasado")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechanacimiento;
	
	public Boolean Estado;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setEstado(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean getEstado() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
