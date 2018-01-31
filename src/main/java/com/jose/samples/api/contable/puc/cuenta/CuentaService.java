package com.jose.samples.api.contable.puc.cuenta;

import java.util.List;

public interface CuentaService {
	public Cuenta findById(Long id);
	public List<Cuenta> findByCodigoOrNombre(String codigo, String nombre);
	//public List<Cuenta> findByClase(Long clase);
	public List<Cuenta> findAll();
	public Cuenta add(Cuenta grupo);
	public void update(Cuenta grupo);
	public void delete(Long id);
}
