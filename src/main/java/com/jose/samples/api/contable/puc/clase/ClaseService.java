package com.jose.samples.api.contable.puc.clase;

import java.util.List;
import java.util.Optional;

public interface ClaseService {
	Clase findById (Long id);
	List<Clase> findByCodigoOrNombre (String codigo, String nombre);
	List<Clase> findAll();
	Clase add (Clase clase);
	void update (Clase clase);
	void delete (Long id);
}
