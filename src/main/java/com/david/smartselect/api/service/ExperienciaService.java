package com.david.smartselect.api.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.david.smartselect.api.event.ResourceCreatedEvent;
import com.david.smartselect.api.model.Colaborador;
import com.david.smartselect.api.model.Experiencia;
import com.david.smartselect.api.repository.ExperienciaRepository;

@Service
public class ExperienciaService {
	
	@Autowired
	private ExperienciaRepository experienciaRepository;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	
	public List<Experiencia> buscarPeloCodigo(Long codigoColaborador){
		return experienciaRepository.findAll().stream().filter(c -> c.getCodigoColaborador().getCodigo() == codigoColaborador).collect(Collectors.toList());
	}
	
	public ResponseEntity criar(Experiencia experiencia, HttpServletResponse response) {
		Experiencia experienciaSalva = experienciaRepository.save(experiencia);
		//devolver caminho no HEADER
		publisher.publishEvent(new ResourceCreatedEvent(this, response, experiencia.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(experienciaSalva);		
	}
	
}
