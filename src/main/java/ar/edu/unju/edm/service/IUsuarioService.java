package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.model.Usuario;

public interface IUsuarioService {
  public void guardarUsuario(Usuario usuario);
	public void eliminarUsuario(Long dni) throws Exception;
	public void modificarUsuario(Usuario usuario);
	public List<Usuario> listarUsuarios(); 
	public Usuario buscarUsuario(Long dni)throws Exception;
}
