package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;
@Service
public class IUsuarioServiceImp implements IUsuarioService{
  private static final Log GRUPO04 = LogFactory.getLog(IUsuarioService.class);
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void guardarUsuario(Usuario usuarioparaguardar) {
		GRUPO04.info(usuarioparaguardar.getId());
		String pw=usuarioparaguardar.getContraseña();
		BCryptPasswordEncoder coder= new BCryptPasswordEncoder(4);
		usuarioparaguardar.setContraseña(coder.encode(pw));
		usuarioparaguardar.setEstado(true);
		usuarioRepository.save(usuarioparaguardar);
	}
	
	@Override
	public List<Usuario>listarUsuarios() {
		List<Usuario> auxiliar = new ArrayList<>();
		List<Usuario> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Usuario>) usuarioRepository.findAll();	
		for (int i = 0; i < auxiliar.size(); i++) {
			if(auxiliar.get(i).getEstado().equals(true)){
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	}
	
	@Override
	public void modificarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		
	}

	@Override
	public Usuario buscarUsuario(Long dni) throws Exception{
		Usuario usuarioEncontrado = new Usuario();
		usuarioEncontrado=usuarioRepository.findById(dni).orElseThrow(()->new Exception("usuario no encontrado"));
		return usuarioEncontrado;
	}
	

	@Override
	public void eliminarUsuario(Long dni) throws Exception {
		Usuario auxiliar =new Usuario();
		auxiliar=buscarUsuario(dni);
		auxiliar.setEstado(false);
		usuarioRepository.save(auxiliar);
	}
}
