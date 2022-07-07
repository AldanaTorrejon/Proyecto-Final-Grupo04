package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.UsuarioPelicula;
@Service
public interface IUsuarioPeliculaService {
	 public UsuarioPelicula newUserFilm ();
	 public void saveUserFilm (UsuarioPelicula UserFilm);
     public void deleteUserFilm (Long id) throws Exception;
     public void changeUserFilm (UsuarioPelicula UserFilm);
     public List<UsuarioPelicula> ListUserFilm ();
     public UsuarioPelicula searchUserFilm (long id) throws Exception;
}
