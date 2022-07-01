package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.controller.UsuarioController;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;
import ar.edu.unju.edm.until.ListaUsuario;

@Service
public class IUsuarioServiceImp implements IUsuarioService{
private static final Log GRUPO04 = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ListaUsuario lista;
	
	@Override
	public void guardarUsuario(Usuario usuarioparaguardar) {
		usuarioRepository.save(usuarioparaguardar);
	}
	
	@Override
	public List<Usuario>mostrarUsuarios() {
		// TODO Auto-generated method stub
		List<Usuario> auxiliar = new ArrayList<>();
		List<Usuario> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Usuario>) usuarioRepository.findAll();
		for(int i = 0 ;i<auxiliar.size();i++) {
			if (auxiliar.get(i).getEstado()==true) {
				auxiliar2.add(auxiliar.get(i));
			}
		}	
		return auxiliar2;
	}
	
	@Override
	public void modificarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepository.save(usuario);
		
	}

	@Override
	public Usuario buscarUsuario(Long dni) throws Exception{
		// TODO Auto-generated method stub´
		Usuario usuarioEncontrado = new Usuario();
		usuarioEncontrado=usuarioRepository.findById(dni).orElseThrow(()->new Exception("usuario no encontrado"));
		return usuarioEncontrado;
	}
	

	@Override
	public void eliminarUsuario(Long dni) throws Exception {
		Usuario auxiliar =new Usuario();
		auxiliar= usuarioRepository.findById(dni).orElseThrow(()->new Exception("usuario no encontrado"));
		auxiliar.setEstado(false);
		usuarioRepository.save(auxiliar);
		
	}
	@Override
	public List<Usuario> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
}
