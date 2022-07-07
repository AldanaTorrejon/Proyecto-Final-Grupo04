package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.UsuarioPelicula;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;
@Controller
public class UsuarioPeliculaController {
	private static final Log GRUPO04 =LogFactory.getLog(UsuarioPeliculaController.class);

	@Autowired 
	IUsuarioPeliculaService UserPeliculaService;

	@Autowired
	IUsuarioService ServiceUsuario;

	@Autowired
	IPeliculaService ServicePelicula;

	@GetMapping ({"/compra"})
	public ModelAndView addCompra() {
		GRUPO04.info("Ingresando al metodo: Nuevo usuario");
		ModelAndView vistaa= new ModelAndView ("cargaCompra");
		vistaa.addObject("usuariopelicula", UserPeliculaService.newUserFilm ());
		vistaa.addObject("usuarios", ServiceUsuario.mostrarUsuarios());
		vistaa.addObject("peliculas", ServicePelicula.listarPelicula());
		vistaa.addObject("editMode", false);	
		return vistaa;
	}

	@PostMapping ("/guardarCompra")
	public ModelAndView saveCompra (@Valid @ModelAttribute ("usuariopelicula") UsuarioPelicula compraparaguardar, BindingResult result ) {
		ModelAndView vistaa=new ModelAndView();
		if (result.hasErrors()) {
			GRUPO04.fatal("Error de validacion");
			vistaa.addObject("usuariopelicula", compraparaguardar);
			vistaa.addObject("editMode", false);
			vistaa.setViewName("CargaCompra");
			return vistaa;
		}try {
			UserPeliculaService.saveUserFilm(compraparaguardar);
		}catch (Exception e) {
			vistaa.addObject("formUsuarioErrorMessage", e.getMessage());
			vistaa.addObject("usuariopelicula",compraparaguardar);
			GRUPO04.error("Saliendo del metodo: ");
			vistaa.addObject("editMode", false);
			vistaa.setViewName("CargaCompra");
			return vistaa;
		}
		vistaa.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		vistaa.addObject("unUsuario", UserPeliculaService.newUserFilm());
		vistaa.addObject("editMode", false);
		vistaa.setViewName("cargarCompra");
		return vistaa;
	}
	@GetMapping({"/comentario"})
	public ModelAndView addComentario () {
		GRUPO04.info("Ingrese al metodo: Nuevo Comentario");
		ModelAndView vistaa = new ModelAndView ("cargaComentario");
		vistaa.addObject("usuariopelicula", UserPeliculaService.newUserFilm());
		vistaa.addObject("usuario", ServiceUsuario.mostrarUsuarios());
		vistaa.addObject("pelicula",ServicePelicula.listarPeliculas());
		vistaa.addObject("editMode", false);
		return vistaa;
	}

	@PostMapping ("/guardarComentario")
	public ModelAndView saveComentario (@Valid @ModelAttribute ("usuariopelicula") UsuarioPelicula compraparaguardar, BindingResult result) {
		ModelAndView vistaa=new ModelAndView();
		if (result.hasErrors ()) {
			GRUPO04.fatal("Error de validacion");
			vistaa.addObject("usuariopelicula", compraparaguardar);
			vistaa.addObject("editMode", false);
		    vistaa.setViewName("cargaComentario");
		    return vistaa;
		}
		try {
			UserPeliculaService.saveUserFilm(compraparaguardar);
		}catch (Exception e) {
			vistaa.addObject("formUsuarioErrorMesssage", e.getMessage());
			vistaa.addObject("usuariopelicula", compraparaguardar);
			GRUPO04.error("Saliendo del metodo:");
			vistaa.addObject("aditMode", false);
			vistaa.setViewName("cargaComentario");
			return vistaa;
		}
		vistaa.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		vistaa.addObject("unUsuario", UserPeliculaService.newUserFilm());
		vistaa.addObject("editMode", false);
		vistaa.setViewName("cargaComentario");
		return vistaa;
	}
	@GetMapping({"/valoracion"})
	public ModelAndView addValoracion () {
		GRUPO04.info("Ingrese al metodo: Nueva Valoración");
		ModelAndView vistaa = new ModelAndView ("cargaValoracion");
		vistaa.addObject("usuariopelicula", UserPeliculaService.newUserFilm());
		vistaa.addObject("usuario", ServiceUsuario.mostrarUsuarios());
		vistaa.addObject("pelicula",ServicePelicula.listarPeliculas());
		vistaa.addObject("editMode", false);
		return vistaa;
	}
	@PostMapping ("/guardarValoracion")
	public ModelAndView saveValoracion (@Valid @ModelAttribute ("usuariopelicula") UsuarioPelicula compraparaguardar, BindingResult result) {
		ModelAndView vistaa=new ModelAndView();
		if (result.hasErrors ()) {
			GRUPO04.fatal("Error de validacion");
			vistaa.addObject("usuariopelicula", compraparaguardar);
			vistaa.addObject("editMode", false);
		    vistaa.setViewName("CargaValoracion");
		    return vistaa;
		}
		try {
			UserPeliculaService.saveUserFilm(compraparaguardar);
		}catch (Exception e) {
			vistaa.addObject("formUsuarioErrorMesssage", e.getMessage());
			vistaa.addObject("usuariopelicula", compraparaguardar);
			GRUPO04.error("Saliendo del metodo:");
			vistaa.addObject("aditMode", false);
			vistaa.setViewName("CargaValoracion");
			return vistaa;
		}
		vistaa.addObject("formUsuarioErrorMessage", "Usuario guardado correctamente");
		vistaa.addObject("unUsuario", UserPeliculaService.newUserFilm());
		vistaa.addObject("editMode", false);
		vistaa.setViewName("CargaValoracion");
		return vistaa;
	}
}