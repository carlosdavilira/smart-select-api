package com.david.smartselect.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.smartselect.api.model.Projeto;
import com.david.smartselect.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
