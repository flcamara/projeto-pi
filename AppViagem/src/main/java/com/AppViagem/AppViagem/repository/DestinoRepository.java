package com.AppViagem.AppViagem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppViagem.AppViagem.models.Destino;
import com.AppViagem.AppViagem.models.Viagem;

public interface DestinoRepository extends CrudRepository<Destino, String> {
	
	Iterable<Destino>findByViagem(Viagem viagem);
	
	Destino findBynomeDestino(String nomeDestino);
	
	Destino findById(long id);
	
	List<Destino>findByNomeDestino(String nomeDestino);

}
