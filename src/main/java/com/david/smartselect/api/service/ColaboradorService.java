package com.david.smartselect.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.david.smartselect.api.event.ResourceCreatedEvent;
import com.david.smartselect.api.model.Colaborador;
import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.repository.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	public List<Colaborador> listar(){
		return colaboradorRepository.findAll();
	}
	
	public ResponseEntity buscarPeloCodigo(Long codigo) {
		Optional colaborador = colaboradorRepository.findById(codigo);		
		return !colaborador.isEmpty() ? ResponseEntity.ok().body(colaborador) : ResponseEntity.notFound().build();
	}
	
	public ResponseEntity criar(Colaborador colaborador, HttpServletResponse response) {
		Colaborador colaboradorSalvo = colaboradorRepository.save(colaborador);
		//devolver caminho no HEADER
		publisher.publishEvent(new ResourceCreatedEvent(this, response, colaborador.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorSalvo);		
	}
	
	public Colaborador atualizar(Long codigo, Colaborador colaborador) {
		Optional<Colaborador> colaboradorSalvo = colaboradorRepository.findById(codigo);
		if(colaboradorSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(colaborador, colaboradorSalvo.get(), "codigo");
		colaboradorRepository.save(colaboradorSalvo.get());
		return colaboradorSalvo.get();
	}

	public List<Colaborador> buscarPeloCodigoUsuario(Long codigo) {
		List<Colaborador> colaboradores = colaboradorRepository.findAll();		
		return colaboradores.stream().filter( usuario -> usuario.getCodigoUsuario().getCodigo() == codigo).collect(Collectors.toList());
	}
	
	
	

}
