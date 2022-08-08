package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Pelicula;
import ar.edu.unju.edm.repository.PeliculaRepository;
import ar.edu.unju.edm.service.IPeliculaService;
@Service
public class IPeliculaServiceImp implements IPeliculaService{
	private static final Log GRUPO04 = LogFactory.getLog(IPeliculaServiceImp.class);
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	@Override
	public void guardarPelicula(Pelicula peliculaparaguardar) {
		peliculaparaguardar.setEstado(true);
		peliculaRepository.save(peliculaparaguardar);
	}

	@Override
	public void modificarPelicula(Pelicula pelicula) {
		peliculaRepository.save(pelicula);
	}

	@Override
	public void eliminarPelicula(Long idPelicula) throws Exception {
		Pelicula auxiliar =new Pelicula();
		auxiliar=buscarPelicula(idPelicula);
		auxiliar.setEstado(false);
		peliculaRepository.save(auxiliar);
	}

	@Override
	public Pelicula buscarPelicula(Long idPelicula) throws Exception {
		Pelicula Encontrada = new Pelicula();
		Encontrada=peliculaRepository.findById(idPelicula).orElseThrow(()->new Exception("Pelicula no encontrada"));
		return Encontrada;
	}

	@Override
	public List<Pelicula> listarPelicula() {
		GRUPO04.info("ingresando al metodo mostrar");
		List<Pelicula> auxiliar = new ArrayList<>();
		List<Pelicula> auxiliar2 = new ArrayList<>();
		auxiliar=(List<Pelicula>) peliculaRepository.findAll();
		for (int i = 0; i < auxiliar.size(); i++) {
			if(auxiliar.get(i).getEstado().equals(true)){
				auxiliar2.add(auxiliar.get(i));
			}
		}
		return auxiliar2;
	}

}
