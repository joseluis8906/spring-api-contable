package com.jose.samples.api.contable.puc.grupo;

import com.jose.samples.api.contable.puc.grupo.Grupo;

import java.util.List;

public interface GrupoService {
	public Grupo findById(Long id);
	public List<Grupo> findByCodigoOrNombre(String codigo, String nombre);
	public List<Grupo> findAll();
	public Grupo add(Grupo grupo);
	public void update(Grupo grupo);
	public void delete(Long id);
}
