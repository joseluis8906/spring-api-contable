package com.jose.samples.api.contable.puc.auxiliar;

import java.util.List;

public interface AuxiliarService {
	Auxiliar findById(Long id);
	List<Auxiliar> findByCodigoOrNombre(String codigo, String nombre);
	List<Auxiliar> findAll();
	Auxiliar add(Auxiliar auxiliar);
	void update(Auxiliar auxiliar);
	void delete(Long id);
}
