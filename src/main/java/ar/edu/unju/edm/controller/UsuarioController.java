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
	Usuario newUsuario;
	@Autowired
	IUsuarioService serviceUsuario;
	
	//entra
	@GetMapping("/otroUsuario")
	public ModelAndView addUser() {
		GRUPO04.info("ingresando a NUEVO USUARIO");
		ModelAndView vista = new ModelAndView("CargaUsuario");//pasa nombre de la lista a pasar
		//vista.addObject("nuevoUsuario");
		vista.addObject("usuario", newUsuario);
		vista.addObject("editMode", false);
		return vista;
	}
	
	//guardar usuarios
	@PostMapping("/guardarUsuario")
	public String saveUser(@Valid @ModelAttribute("usuario") Usuario usuarioparaguardar, BindingResult resultado, Model model) { //del modelo viene 1 atributo llamado usuario y lo agarra le indica el tipo y un nombre 
		System.out.println(resultado.getAllErrors());
		if(resultado.hasErrors()) {
			GRUPO04.fatal("Error de Validacion");
			model.addAttribute("usuario",usuarioparaguardar);
			model.addAttribute("editMode", false);
			return "CargaUsuario";
		}
		try { //controla si algo se ejecuta bien
			serviceUsuario.guardarUsuario(usuarioparaguardar);
		}catch(Exception error){ //sale por aqui
			model.addAttribute("formUsuarioErrorMessage", error.getMessage());
			model.addAttribute("usuario",usuarioparaguardar);
			model.addAttribute("editMode", false);
			GRUPO04.error("saliendo del metodo: saveUser");
			return "CargaUsuario";
		}
		model.addAttribute("formUsuarioErrorMessage", "Usuario Guardado Correctamente");
		model.addAttribute("usuario", newUsuario);
		model.addAttribute("editMode", false);
		return "CargaUsuario";
	}
	
	
	//listar usuarios
	@GetMapping("/listadoUsuario")
	public ModelAndView listUser() {
		ModelAndView vistaa = new ModelAndView("ListadoUsuario");
		if(serviceUsuario.mostrarUsuarios().size()!=0) {
		vistaa.addObject("listaUsuario", serviceUsuario.mostrarUsuarios());
		GRUPO04.info("ingresando al metodo: listUsers "+serviceUsuario.mostrarUsuarios().get(0).getApellido());
		}
		return vistaa;
	}
	
	//eliminar usuario
	@RequestMapping("/eliminarUsuario/{dni}")
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
		ModelAndView modelView = new ModelAndView("cargarUsuario");
		modelView.addObject("usuario", usuarioEncontrado);
		GRUPO04.error("saliendo de ELIMINAR USUARIO"+ usuarioEncontrado.getDni());
		modelView.addObject("editMode", true);
		return modelView;
	}
	
	@PostMapping("/editarUsuario")
	public ModelAndView postEditarUsuario(@ModelAttribute ("usuario") Usuario usuarioparamodificar, BindingResult result) {  
		GRUPO04.fatal("Error de validacion"+usuarioparamodificar.getContrase??a());
		GRUPO04.fatal("Error de validacion"+usuarioparamodificar.getDni());
			if(result.hasErrors()){
			GRUPO04.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("CargaUsuario");
			vista.addObject("usuario", usuarioparamodificar);
			vista.addObject("editMode",true);
			return vista;
		}
		try{
			serviceUsuario.modificarUsuario(usuarioparamodificar);
		}catch(Exception error){
			ModelAndView vista = new ModelAndView("cargarUsuario");
			vista.addObject("formUsuarioErrorMessage", error.getMessage());
			vista.addObject("usuario", usuarioparamodificar);
			vista.addObject("editMode",true);
			GRUPO04.error("saliendo del metodo: editarusuario");
			return vista;
		}
		 GRUPO04.error("DNI de usuarioparamod "+ usuarioparamodificar.getDni());
		 GRUPO04.error("Nombre de usuarioparamod "+ usuarioparamodificar.getNombre());
		ModelAndView vista1 = new ModelAndView("ListadoUsuario");		
		vista1.addObject("listaUsuarios", serviceUsuario.mostrarUsuarios());	
		vista1.addObject("formUsuarioErrorMessage","Usuario modificado Correctamente");
		
		return vista1;
	}
}
