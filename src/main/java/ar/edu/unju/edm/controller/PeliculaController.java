package ar.edu.unju.edm.controller;

import java.util.Base64;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.UsuarioPeliculas;
import ar.edu.unju.edm.service.IPeliculaService;
import ar.edu.unju.edm.service.IUsuarioPeliculaService;
import ar.edu.unju.edm.service.IUsuarioService;


@Controller
public class PeliculaController {
	private static final Log GRUPO04=LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	IPeliculaService peliculaService;
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IUsuarioPeliculaService usuarioPeliculaService;

	// Cargar pelicula
	@GetMapping("/otraPelicula")
	public ModelAndView addMovie() {
		GRUPO04.info("Ingresando a AÑADIR PELICULA");
		Pelicula newPelicula = new Pelicula();
		ModelAndView vista = new ModelAndView("CargaPelicula");
		vista.addObject("pelicula", newPelicula);
		vista.addObject("editMode", false);
		GRUPO04.info("saliendo de AÑADIR PELICULA");
		return vista;
	}

	@PostMapping(value = "/guardarPelicula", consumes = "multipart/form-data")
	public String saveMovie(@Valid @ModelAttribute("pelicula") Pelicula peliculaparaguardar, BindingResult resultado,
			@RequestParam("file") MultipartFile file, Model model) { // del modelo viene 1 atributo llamado pelicula y lo
																																// agarra le indica el tipo y un nombre
		GRUPO04.info("Ingresando a GUARDAR PELICULA");
		Pelicula newPelicula = new Pelicula();
		if (resultado.hasErrors()) {
			GRUPO04.fatal("Error de validación en guardar peli");
			model.addAttribute("Peli", newPelicula);
			return "formulariopeliculas";
		}
		try {
			GRUPO04.info("Guardando..." + newPelicula.getDescripcion());
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculaparaguardar.setImagen(base64);
			peliculaparaguardar.setEstado(true);
			peliculaService.guardarPelicula(peliculaparaguardar);
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unaPeli", newPelicula);
			GRUPO04.error("saliendo del metodo");
			return "formulariopeliculas";
		}

		model.addAttribute("formPeliculaErrorMessage", "Pelicula Guardada Correctamente");
		model.addAttribute("pelicula", newPelicula);

		// model.addAttribute("editMode", false);
		GRUPO04.info("saliendo de  GUARDAR PELICULA");
		return "redirect:/ListadoPelicula-admin";
	}

	// listar pelicula
	@GetMapping("/ListadoPelicula")
	public ModelAndView showMovie() {
		ModelAndView vista = new ModelAndView("ListadoPeliculas");
		vista.addObject("listaPelicula", peliculaService.listarPelicula());
		return vista;
	}

	@GetMapping("/ListadoPelicula-admin")
	public ModelAndView showMovieadmin() {
		ModelAndView vista = new ModelAndView("ListadoPelicula-admin");
		vista.addObject("listaPelicula", peliculaService.listarPelicula());
		return vista;
	}

	// modificar pelicula
	@GetMapping("/editPelicula/{idPelicula}")
	public ModelAndView editMovie(Model model, @PathVariable(name = "idPelicula") Long idPelicula) throws Exception {
		Pelicula peliculaEncontrada = new Pelicula();
		peliculaEncontrada = peliculaService.buscarPelicula(idPelicula);
		ModelAndView modelView = new ModelAndView("CargaPelicula");
		modelView.addObject("pelicula", peliculaEncontrada);
		GRUPO04.error("saliendo del metodo: editMovie " + peliculaEncontrada.getNombrePelicula());
		modelView.addObject("editMode", true);
		return modelView;
	}

	// actualizar pelicula
	@PostMapping(value = "/editarPelicula", consumes = "multipart/form-data")
	public ModelAndView modPeli(@ModelAttribute("pelicula") Pelicula peliculas, @RequestParam("file") MultipartFile file,
			Model model) {
		ModelAndView vista2 = new ModelAndView("CargaPelicula");
		GRUPO04.info(peliculas.getIdp());
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculas.setImagen(base64);
			peliculaService.modificarPelicula(peliculas);
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("pelicula", peliculas);
			return vista2;
		}
		ModelAndView vista = new ModelAndView("ListadoPelicula-admin");
		vista.addObject("listaPelicula", peliculaService.listarPelicula());
		vista.addObject("formUsuarioErrorMessage", "Peli Guardada Correctamente");
		return vista;
	}

	// eliminar pelicula
	@GetMapping("/deleteMovie/{idPelicula}")
	public String deleteMovie(@PathVariable(name = "idPelicula") Long idPelicula, Model model) {
		try {
			GRUPO04.info("ingresando al metodo eliminar");
			peliculaService.eliminarPelicula(idPelicula);
		} catch (Exception error) {
			GRUPO04.error("encontrando: eliminarpelicula");
			model.addAttribute("formPeliculaErrorMessage", error.getMessage());
			return "redirect:/otraPelicula";
		}

		return "redirect:/ListadoPelicula-admin";
	}

	@GetMapping("/descrip/{idPelicula}")
	public ModelAndView descripcion(@PathVariable(name = "idPelicula") Long idPelicula, Model model) throws Exception {
		Pelicula peliculaencontrada = new Pelicula();
		ModelAndView encontrado = new ModelAndView("paginaconV,CyT");
		peliculaencontrada = peliculaService.buscarPelicula(idPelicula);
		GRUPO04.info("pelicula:" + peliculaencontrada.getNombrePelicula());
		encontrado.addObject("peli", peliculaencontrada);
        ////
		GRUPO04.info(peliculaencontrada.getDescripcion());
		GRUPO04.fatal("Saliendo del metodo encontrado pelis ");
		return encontrado;
	}

	////

	@GetMapping("/entrada")
	public ModelAndView Entradas() throws Exception {
		ModelAndView vista = new ModelAndView("ListadoCompra");
		Authentication auth = SecurityContextHolder
				.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Usuario userEnSesion = usuarioService.buscarUsuario(Long.parseLong(userDetail.getUsername()));
		vista.addObject("listaCompra", usuarioPeliculaService.listarUsuarioPeliculas(userEnSesion));
		return vista;
	}

	@GetMapping("/comprar/{idPelicula}")
	public String buyMovie(@PathVariable(name = "idPelicula") Long idPelicula, Model model) throws Exception {
		Pelicula peliculas = new Pelicula();
		UsuarioPeliculas usuarioPeliculas = new UsuarioPeliculas();
		peliculas = peliculaService.buscarPelicula(idPelicula);
		Authentication auth = SecurityContextHolder
				.getContext()
				.getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		Usuario userEnSesion = usuarioService.buscarUsuario(Long.parseLong(userDetail.getUsername()));
		usuarioPeliculas.setPelis(peliculas);
		usuarioPeliculas.setUsuario(userEnSesion);
		usuarioPeliculaService.agregarentrada(usuarioPeliculas);
		return "redirect:/entrada";
	}
}
