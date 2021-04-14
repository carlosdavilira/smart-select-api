package com.david.smartselect.api.resource;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.smartselect.api.event.ResourceCreatedEvent;
import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.repository.ProjetoRepository;
import com.david.smartselect.api.service.ProjetoService;

@RestController
@RequestMapping("/projeto")
public class ProjetoResource {

	@Autowired
	private ProjetoRepository projetoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProjetoService projetoService;
	
	
	@GetMapping
	public List<Projeto> listar(){
		return projetoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
		Optional projeto = projetoRepository.findById(codigo);		
		return !projeto.isEmpty() ? ResponseEntity.ok().body(projeto) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity criar(@Valid @RequestBody Projeto projeto, HttpServletResponse response) {
		Projeto projetoSalva = projetoRepository.save(projeto);
		//devolver caminho no HEADER
		publisher.publishEvent(new ResourceCreatedEvent(this, response, projeto.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalva);		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Projeto> atualizar(@PathVariable Long codigo, @Valid @RequestBody Projeto categoria){		
			return ResponseEntity.ok(projetoService.atualizar(codigo, categoria));
		}
	
	
	
}
