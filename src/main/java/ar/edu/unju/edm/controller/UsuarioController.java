package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {
	private static final Log GRUPO04=LogFactory.getLog(UsuarioController.class);//constante con mayuscula

	@Autowired
	IUsuarioService serviceUsuario;
	
	//entra
	@GetMapping("/otroUsuario")
	public ModelAndView addUser() {
		GRUPO04.info("ingresando a NUEVO USUARIO");
		ModelAndView vista = new ModelAndView("LoginService");//pasa nombre de la lista a pasar
		Usuario newUsuario=new Usuario();
		//vista.addObject("nuevoUsuario");
		vista.addObject("usuario", newUsuario);
		vista.addObject("editMode", false);
		return vista;
	}
	//guardar usuarios
	@PostMapping("/guardarUsuario")
	public String saveUser(@Valid @ModelAttribute("usuario") Usuario user, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		GRUPO04.fatal("entro");
		if (resultado.hasErrors()) {
      GRUPO04.fatal("Error de validación");
      model.addAttribute("usuario", user);
      return "LoginService";
    }
    try {
      serviceUsuario.guardarUsuario(user);
      GRUPO04.info("guardado correctamente");
    } catch (Exception e) {
			model.addAttribute("unUsuario", user);
			GRUPO04.error("saliendo del metodo");
			return "LoginService";	
    }
		model.addAttribute("unUsuario", user);			
		return "IngresodeIUsuario";
	}
	@GetMapping("/ListadoUsuario")
	public ModelAndView listUser() {
		ModelAndView vistaa = new ModelAndView("ListadoUsuario");
		if(serviceUsuario.listarUsuarios().size()!=0) {
		vistaa.addObject("listaUsuario", serviceUsuario.listarUsuarios());
		GRUPO04.info("ingresando al metodo: listUsers "+serviceUsuario.listarUsuarios().get(0).getNombre());
		}
		return vistaa;
	}
	
	//eliminar usuario
	@GetMapping("/eliminarUsuario/{dni}")
	public String deleteUser(@PathVariable(name="dni")Long dni, Model model) {
		try {
			serviceUsuario.eliminarUsuario(dni);
		}catch(Exception error){
			GRUPO04.error("Encontrando el usuario a eliminar");
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			return "redirect:/otroUsuario";
		}
		return "redirect:/ListadoUsuario";
	}
	
	//modificar usuario
	@GetMapping("/editarUsuario/{dni}")
	public ModelAndView edituser(Model model,@PathVariable (name="dni") Long dni)throws Exception {	
	Usuario usuarioEncontrado = new Usuario();
		usuarioEncontrado = serviceUsuario.buscarUsuario(dni);
		ModelAndView modelView = new ModelAndView("LoginService");
		modelView.addObject("usuario", usuarioEncontrado);
		GRUPO04.error("saliendo de ELIMINAR USUARIO"+ usuarioEncontrado.getId());
		modelView.addObject("editMode", true);
		return modelView;
	}
	
	@PostMapping("/editarUsuario")
	public ModelAndView postEditarUsuario(@ModelAttribute ("usuario") Usuario usuarioparamodificar, BindingResult result) {  
		GRUPO04.fatal("Error de validacion"+usuarioparamodificar.getContraseña());
		GRUPO04.fatal("Error de validacion"+usuarioparamodificar.getId());
			if(result.hasErrors()){
			GRUPO04.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("LoginService");
			vista.addObject("usuario", usuarioparamodificar);
			vista.addObject("editMode",true);
			return vista;
		}
		try{
			serviceUsuario.modificarUsuario(usuarioparamodificar);
		}catch(Exception error){
			ModelAndView vista = new ModelAndView("LoginService");
			vista.addObject("formUsuarioErrorMessage", error.getMessage());
			vista.addObject("usuario", usuarioparamodificar);
			vista.addObject("editMode",true);
			GRUPO04.error("saliendo del metodo: editarusuario");
			return vista;
		}
		 GRUPO04.error("DNI de usuarioparamod "+ usuarioparamodificar.getId());
		 GRUPO04.error("Nombre de usuarioparamod "+ usuarioparamodificar.getNombre());
		ModelAndView vista1 = new ModelAndView("ListadoUsuario");		
		vista1.addObject("listaUsuarios", serviceUsuario.listarUsuarios());	
		vista1.addObject("formUsuarioErrorMessage","Usuario modificado Correctamente");
		
		return vista1;
	}
}
