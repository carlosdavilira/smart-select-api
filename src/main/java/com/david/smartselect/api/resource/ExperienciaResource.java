package com.david.smartselect.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.david.smartselect.api.model.Experiencia;
import com.david.smartselect.api.repository.ExperienciaRepository;
import com.david.smartselect.api.service.ExperienciaService;


@RestController
@RequestMapping("/experiencia")
public class ExperienciaResource {
	
	@Autowired
	private ExperienciaService experienciaService;
	
	@Autowired
	private ExperienciaRepository experienciaRepository;
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@GetMapping("/{codigo}")
	public List<Experiencia> buscarPeloCodigo(@PathVariable Long codigo) {				
		return experienciaService.buscarPeloCodigo(codigo);
	}
	
}
