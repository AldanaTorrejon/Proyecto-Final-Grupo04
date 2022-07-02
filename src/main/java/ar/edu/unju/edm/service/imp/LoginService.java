                                                package ar.edu.unju.edm.service.imp;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService{
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		// buscar usuario
		Usuario usuarioEncontrado = usuarioRepository.findByDni(Integer.parseInt(dni)).orElseThrow(()->new UsernameNotFoundException("Usuario Invalido"));
//		
//		//difinir autorizacion 
		List <GrantedAuthority> tipos = new ArrayList<> ();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getTipo());
		tipos.add(grantedAuthority);
//		
//		//definir el usuario en sesion
		UserDetails usuarioSesion = new User(dni,usuarioEncontrado.getContraseña(),tipos);
		
		return usuarioSesion;
	}
}
	

////TODO Auto-generated method stub
//

//		
//		return usuarioSesion;	

