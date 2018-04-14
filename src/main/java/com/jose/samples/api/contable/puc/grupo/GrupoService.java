package com.jose.samples.api.contable.puc.grupo;

import com.jose.samples.api.contable.puc.grupo.Grupo;

import java.util.List;
import java.util.Optional;

public interface GrupoService {
	Optional<Grupo> findById(Long id);
	List<Grupo> findByCodigoOrNombre(String codigo, String nombre);
	List<Grupo> findAll();
	Grupo add(Grupo grupo);
	void update(Grupo grupo);
	void delete(Long id);
}
