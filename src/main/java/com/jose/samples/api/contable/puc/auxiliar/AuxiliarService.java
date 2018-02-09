package com.jose.samples.api.contable.puc.auxiliar;

import java.util.List;

public interface AuxiliarService {
	public Auxiliar findById(Long id);
	public List<Auxiliar> findByCodigoOrNombre(String codigo, String nombre);
	public List<Auxiliar> findAll();
	public Auxiliar add(Auxiliar auxiliar);
	public void update(Auxiliar auxiliar);
	public void delete(Long id);
}
