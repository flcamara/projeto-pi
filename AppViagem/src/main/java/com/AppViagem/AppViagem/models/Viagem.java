package com.AppViagem.AppViagem.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotEmpty;

@Entity
public class Viagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotEmpty
	private String horario;
	
	@NotEmpty
	private String descricao;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private String valor;
	
	@OneToMany(mappedBy = "viagem", cascade = CascadeType.REMOVE)
	private List<Destino> destino;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Destino> getDestino() {
		return destino;
	}

	public void setDestino(List<Destino> destino) {
		this.destino = destino;
	}
	
	
	
}
