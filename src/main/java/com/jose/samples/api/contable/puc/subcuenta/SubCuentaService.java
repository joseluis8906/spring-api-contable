package com.jose.samples.api.contable.puc.subcuenta;

import java.util.List;

public interface SubCuentaService {
	SubCuenta findById(Long id);
	List<SubCuenta> findByCodigoOrNombre(String codigo, String nombre);
	List<SubCuenta> findAll();
	SubCuenta add(SubCuenta subcuenta);
	void update(SubCuenta subcuenta);
	void delete(Long id);
}
