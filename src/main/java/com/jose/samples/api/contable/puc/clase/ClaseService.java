package com.jose.samples.api.contable.puc.clase;

import java.util.List;

public interface ClaseService {
	public Clase findOne (long id);
	public List<Clase> findByCodigoOrNombre (String codigo, String nombre);
	public List<Clase> findAll();
	public void save (Clase clase);
	public void update (Clase clase);
	public void delete (long id);
}
