package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Pelicula;

public interface IPeliculaService {
  public void guardarPelicula(Pelicula Peliculaparaguardar);
	public void modificarPelicula(Pelicula Pelicula);
	public void eliminarPelicula(Long idPelicula) throws Exception;
	public Pelicula buscarPelicula(Long idPelicula) throws Exception;
	public List<Pelicula> listarPelicula();
}

