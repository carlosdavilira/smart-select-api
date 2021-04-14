package com.david.smartselect.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.repository.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	ProjetoRepository projetoRepository;
	
	public Projeto atualizar(Long codigo, Projeto projeto) {
		Optional<Projeto> projetoSalvo = projetoRepository.findById(codigo);
		if(projetoSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(projeto, projetoSalvo.get(), "codigo");
		projetoRepository.save(projetoSalvo.get());
		return projetoSalvo.get();
	}
}
