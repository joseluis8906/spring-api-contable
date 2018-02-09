package com.jose.samples.api.contable.puc.subcuenta;

import java.util.List;

public interface SubCuentaService {
	public SubCuenta findById(Long id);
	public List<SubCuenta> findByCodigoOrNombre(String codigo, String nombre);
	public List<SubCuenta> findAll();
	public SubCuenta add(SubCuenta subcuenta);
	public void update(SubCuenta subcuenta);
	public void delete(Long id);
}
