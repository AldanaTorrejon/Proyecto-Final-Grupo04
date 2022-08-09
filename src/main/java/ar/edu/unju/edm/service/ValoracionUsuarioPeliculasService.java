package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.ValoracionUsuarioPeliculas;

public interface ValoracionUsuarioPeliculasService {
  public void guardarValoracion(ValoracionUsuarioPeliculas valoracion) throws Exception;
  public List<ValoracionUsuarioPeliculas> ListarValoracion(Pelicula peliculas);
  public Boolean Existe(Usuario usuario) throws Exception;
}
