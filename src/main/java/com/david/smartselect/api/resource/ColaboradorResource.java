package com.david.smartselect.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.smartselect.api.model.Colaborador;
import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.repository.ColaboradorRepository;
import com.david.smartselect.api.repository.ProjetoRepository;
import com.david.smartselect.api.service.ColaboradorService;
import com.david.smartselect.api.service.ProjetoService;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorResource {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@GetMapping
	public List<Colaborador> listar(){
		return colaboradorService.listar();
	}
	
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@GetMapping("/{codigo}")
	public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {				
		return colaboradorService.buscarPeloCodigo(codigo);
	}
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@PostMapping("/getByUser")
	public List<Colaborador> buscarPeloCodigoUsuario(@Valid @RequestBody Colaborador colaborador) {				
		return colaboradorService.buscarPeloCodigoUsuario(colaborador.getCodigoUsuario().getCodigo());
	}
	
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@PostMapping
	public ResponseEntity criar(@Valid @RequestBody Colaborador colaborador, HttpServletResponse response) {	
		return ResponseEntity.ok(colaboradorService.criar(colaborador, response));		
	}
	
	@CrossOrigin(maxAge= 10, origins = {"*"})
	@PostMapping("/update")
	public ResponseEntity<Colaborador> atualizar( @Valid @RequestBody Colaborador colaborador){		
			return ResponseEntity.ok(colaboradorService.atualizar(colaborador.getCodigo(), colaborador));
		}	
	
	
	
	
}
