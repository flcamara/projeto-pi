package com.AppViagem.AppViagem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppViagem.AppViagem.models.Viagem;

public interface ViagemRepository extends CrudRepository<Viagem, String> {
	Viagem findByCodigo(long codigo);
	//List<Viagem> findByNome(String nome);
}
