package ar.edu.unju.edm.repository;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.edm.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
	public List<Usuario> findByEstado(boolean Estado);
	public Optional<Usuario> findByDni(int dni);
}
