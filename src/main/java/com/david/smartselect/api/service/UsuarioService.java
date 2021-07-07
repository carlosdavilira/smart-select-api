package com.david.smartselect.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public ResponseEntity login(Usuario usuario) {
		//List<Usuario> usuarios = usuarioRepository.findAll();
		List<Usuario> usuarios =  usuarioRepository.findAll().stream().filter(
				user -> user.getUsuario().equals(usuario.getUsuario()) && 
				user.getSenha().equals(usuario.getSenha())).collect(Collectors.toList());
		return !usuarios.isEmpty() ? ResponseEntity.ok().body(usuarios.get(0)) : ResponseEntity.ok().body(null);
	}
	

}
