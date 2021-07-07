package com.david.smartselect.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.model.Usuario;
import com.david.smartselect.api.repository.UsuarioRepository;
import com.david.smartselect.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@GetMapping("/{codigo}")
	public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {				
		return usuarioService.buscarPeloCodigo(codigo);
	}
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody Usuario usuario) {				
		return usuarioService.login(usuario);
	}
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@PostMapping
	public ResponseEntity criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {	
		return ResponseEntity.ok(usuarioService.criar(usuario, response));		
	}
	
}
