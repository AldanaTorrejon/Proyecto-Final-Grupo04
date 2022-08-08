package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.UsuarioPeliculas;
@Repository
public interface UsuarioPeliculasRepository extends CrudRepository<UsuarioPeliculas,Long>{
  public Optional<UsuarioPeliculas> findByUsuario(Usuario usuario);
}