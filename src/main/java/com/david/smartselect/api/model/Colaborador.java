package com.david.smartselect.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="colaborador")
public class Colaborador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Column(name="projeto_atual")
	private String projetoAtual;	
	
	@Column(name="gerente_atual")
	private String gerenteAtual;
	
	@Column(name="habilidades")
	private String habilidades;
	
	
	@ManyToOne	
	@JoinColumn(name = "codigo_usuario")
	private Usuario codigoUsuario;
	
		
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProjetoAtual() {
		return projetoAtual;
	}

	public void setProjetoAtual(String projetoAtual) {
		this.projetoAtual = projetoAtual;
	}

	public String getGerenteAtual() {
		return gerenteAtual;
	}

	public void setGerenteAtual(String gerenteAtual) {
		this.gerenteAtual = gerenteAtual;
	}

	public String getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}

	public Usuario getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Usuario codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaborador other = (Colaborador) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
