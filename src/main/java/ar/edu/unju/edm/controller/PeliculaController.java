package ar.edu.unju.edm.controller;

import java.util.Base64;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.service.IPeliculaService;

@Controller
public class PeliculaController {
	private static final Log GRUPO04=LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	Pelicula newPelicula;
	@Autowired
	IPeliculaService peliculaService;
	
	//Cargar pelicula
	@GetMapping("/otraPelicula")
	public ModelAndView addMovie() {
		GRUPO04.info("Ingresando a AÑADIR PELICULA");
		ModelAndView vista=new ModelAndView("CargarPelicula");
		vista.addObject("pelicula", newPelicula);
		vista.addObject("editMode", false);
		GRUPO04.info("saliendo de AÑADIR PELICULA");
		return vista;
	}
	
	@PostMapping(value="/guardarPelicula", consumes = "multipart/form-data")
	public String saveMovie(@Valid @ModelAttribute("pelicula")Pelicula peliculaparaguardar, BindingResult resultado, @RequestParam("file") MultipartFile file, Model model) { //del modelo viene 1 atributo llamado pelicula y lo agarra le indica el tipo y un nombre
		GRUPO04.info("Ingresando a GUARDAR PELICULA");
		if(resultado.hasErrors()) {
			GRUPO04.fatal("Error en GUARDAR PELICULA");
			model.addAttribute("editMode", false);
			model.addAttribute("pelicula", peliculaparaguardar);
            return "CargaPelicula";
		} 
		try {
			
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			peliculaparaguardar.setImagen(base64);
			peliculaparaguardar.setDuracionPelicula(45);
			peliculaService.guardarPelicula(peliculaparaguardar);
		}catch(Exception error) {
			model.addAttribute("formPeliculaErrorMessage", error.getMessage());
			model.addAttribute("pelicula", peliculaparaguardar);
			GRUPO04.error("No se pudo guardar la pelicula");
			return "CargaPelicula";
		}
		
		model.addAttribute("formPeliculaErrorMessage", "Pelicula Guardada Correctamente");
		model.addAttribute("pelicula", newPelicula);

		//model.addAttribute("editMode", false);
		GRUPO04.info("saliendo de  GUARDAR PELICULA");
		return "CargaPelicula";
	}
	
	// listar pelicula
	@GetMapping("/ListadoPeliculas")
	public ModelAndView showMovie() {
		ModelAndView vista = new ModelAndView("ListadoPeliculas");
		vista.addObject("listaPelicula", peliculaService.listarPelicula());
		return vista;
	}
	
		//modificar  pelicula
	@RequestMapping("/editPelicula/{idPelicula}")
	public ModelAndView editMovie(Model model,@PathVariable (name="idPelicula") Long idPelicula)throws Exception {	
		Pelicula peliculaEncontrada = new Pelicula();
		peliculaEncontrada = peliculaService.buscarPelicula(idPelicula);		
		ModelAndView modelView = new ModelAndView("CargaPelicula");
		modelView.addObject("pelicula", peliculaEncontrada);
		 GRUPO04.error("saliendo del metodo: editMovie "+ peliculaEncontrada.getNombrePelicula());
		modelView.addObject("editMode", true);
		return modelView;
	}
	
	
	//actualizar pelicula
	@PostMapping("/editarPelicula")
	public ModelAndView saveEditMovie(@Valid @ModelAttribute ("pelicula") Pelicula peliculaparamodificar, BindingResult result) {  
		if(result.hasErrors()) {
			GRUPO04.fatal("Error de validacion");
			ModelAndView vista = new ModelAndView("CargaPelicula");
			vista.addObject("pelicula", peliculaparamodificar);
			vista.addObject("editMode",true);
			return vista;
		}
		try {
			peliculaService.modificarPelicula(peliculaparamodificar);
		}catch(Exception error){
			ModelAndView vista = new ModelAndView("CargaPelicula");
			vista.addObject("formPeliculaErrorMessage", error.getMessage());
			vista.addObject("pelicula", peliculaparamodificar);
			vista.addObject("editMode",true);
			GRUPO04.error("saliendo de EDITAR PELICULA");
			return vista;
		}
			ModelAndView vista = new ModelAndView("ListadoPeliculas");
			vista.addObject("listaPelicula", peliculaService.listarPelicula());	
			vista.addObject("formPeliculaErrorMessage","Pelicula modificada Correctamente");
		return vista;
	}
		

	
	// eliminar pelicula
			@RequestMapping("/deleteMovie/{idPelicula}")
			public String deleteMovie(@PathVariable(name="idPelicula") Long idPelicula, Model model) {
				try {
					GRUPO04.info("ingresando al metodo eliminar");
					peliculaService.eliminarPelicula(idPelicula);
				}catch(Exception error){
					GRUPO04.error("encontrando: eliminarpelicula");
					model.addAttribute("formPeliculaErrorMessage", error.getMessage());
					return "redirect:/otraPelicula";
				}
			
			    return "redirect:/listarPelicula";
			}
}
