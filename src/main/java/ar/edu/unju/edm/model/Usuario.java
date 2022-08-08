package ar.edu.unju.edm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Usuario {
	@Id
	@Min(value=1000000, message="El dni debe ser mayor a 1.000.000")
	@Max(value=99999999, message="El dni debe ser menor a 99.999.999")
	@Column (name= "ID", nullable = true)
  private Long Id;
  @NotEmpty
  private String email;
  private String tipo;
  @Size(min=4, max=30, message="La contraseña debe tener 4 caracteres minimo, maximo 30")
	@NotBlank(message="La contraseña no puede ser espacios en blanco")
  private String contraseña;
  @Size(min=2, max=50, message="EL nombre debe tener 2 caracteres minimo, maximo 50")
	@NotBlank(message="El nombre no puede ser espacios en blanco")
	@NotEmpty(message="El nombre no puede estar vacio")  
  private String nombre;
  private Boolean estado;
  public Usuario(
      @Min(value = 1000000, message = "El dni debe ser mayor a 1.000.000") @Max(value = 99999999, message = "El dni debe ser menor a 99.999.999") Long id,
      @NotEmpty String email, String tipo,
      @Size(min = 4, max = 30, message = "La contraseña debe tener 4 caracteres minimo, maximo 30") @NotBlank(message = "La contraseña no puede ser espacios en blanco") String contraseña,
      @Size(min = 2, max = 50, message = "EL nombre debe tener 2 caracteres minimo, maximo 50") @NotBlank(message = "El nombre no puede ser espacios en blanco") @NotEmpty(message = "El nombre no puede estar vacio") String nombre,
      Boolean estado) {
        super();
    Id = id;
    this.email = email;
    this.tipo = tipo;
    this.contraseña = contraseña;
    this.nombre = nombre;
    this.estado = estado;
  }
  public Usuario() {
  }
  public Long getId() {
    return Id;
  }
  public void setId(Long id) {
    Id = id;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTipo() {
    return tipo;
  }
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  public String getContraseña() {
    return contraseña;
  }
  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public Boolean getEstado() {
    return estado;
  }
  public void setEstado(Boolean estado) {
    this.estado = estado;
  }
}
