package com.jose.samples.api.contable.puc.grupo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	public List<Grupo> findByCodigoContainingOrNombreContaining (String codigo, String nombre);
	public Grupo findById(Long id);
}
