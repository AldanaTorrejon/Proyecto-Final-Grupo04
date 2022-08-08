package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
  
}
