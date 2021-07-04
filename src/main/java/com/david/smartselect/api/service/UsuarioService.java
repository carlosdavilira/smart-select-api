package com.david.smartselect.api.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.david.smartselect.api.event.ResourceCreatedEvent;
import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.model.Usuario;
import com.david.smartselect.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	public ResponseEntity buscarPeloCodigo(Long codigo) {
		Optional usuario = usuarioRepository.findById(codigo);		
		return !usuario.isEmpty() ? ResponseEntity.ok().body(usuario) : ResponseEntity.notFound().build();
	}
	
	public ResponseEntity criar(Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		//devolver caminho no HEADER
		publisher.publishEvent(new ResourceCreatedEvent(this, response, usuario.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);		
	}

}
