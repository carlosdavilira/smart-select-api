package com.david.smartselect.api.model;

import java.sql.Date;

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
@Table(name="experiencias")
public class Experiencia {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Column(name="nome_empresa")
	private String nomeEmpresa;	
	
	@NotNull
	@Column(name="cargo")
	private String cargo;	
	
	@NotNull
	@Column(name="atividades_realizadas")
	private String atividadesRealizadas;	
		
	@Column(name="inicio")
	private Date inicio;
	
	@Column(name="fim")
	private Date fim;
	
	@ManyToOne	
	@JoinColumn(name = "codigo_colaborador")
	private Colaborador codigoColaborador;
	
	
	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getAtividadesRealizadas() {
		return atividadesRealizadas;
	}

	public void setAtividadesRealizadas(String atividadesRealizadas) {
		this.atividadesRealizadas = atividadesRealizadas;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Colaborador getCodigoColaborador() {
		return codigoColaborador;
	}

	public void setCodigoColaborador(Colaborador codigoColaborador) {
		this.codigoColaborador = codigoColaborador;
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
		Experiencia other = (Experiencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
