package com.david.smartselect.api.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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
	
}
