package com.david.smartselect.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.david.smartselect.api.event.ResourceCreatedEvent;
import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.repository.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	ProjetoRepository projetoRepository;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	public Projeto atualizar(Long codigo, Projeto projeto) {
		Optional<Projeto> projetoSalvo = projetoRepository.findById(codigo);
		if(projetoSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(projeto, projetoSalvo.get(), "codigo");
		projetoRepository.save(projetoSalvo.get());
		return projetoSalvo.get();
	}
	
	public ResponseEntity criar(Projeto projeto, HttpServletResponse response) {
		Projeto projetoSalva = projetoRepository.save(projeto);
		//devolver caminho no HEADER
		publisher.publishEvent(new ResourceCreatedEvent(this, response, projeto.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalva);		
	}
		
	public ResponseEntity buscarPeloCodigo(Long codigo) {
		Optional projeto = projetoRepository.findById(codigo);		
		return !projeto.isEmpty() ? ResponseEntity.ok().body(projeto) : ResponseEntity.notFound().build();
	}
	
	public List<Projeto> listar(){
		return projetoRepository.findAll();
	}
	
	
	
}
