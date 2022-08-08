package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.UsuarioPeliculas;

public interface IUsuarioPeliculaService {
  public List<UsuarioPeliculas> listarUsuarioPeliculas(Usuario usuario);
  public void agregarentrada(UsuarioPeliculas usuarioPeliculas);
  public Boolean existe(UsuarioPeliculas usuarioPeliculas);
}
