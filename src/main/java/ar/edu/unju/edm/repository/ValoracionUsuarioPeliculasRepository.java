package ar.edu.unju.edm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.ValoracionUsuarioPeliculas;

@Repository
public interface ValoracionUsuarioPeliculasRepository extends CrudRepository<ValoracionUsuarioPeliculas,Long>{
  public Optional<ValoracionUsuarioPeliculas> findByUsuario(Usuario usuario);
}
