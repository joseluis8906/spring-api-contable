package com.jose.samples.api.contable.puc.auxiliar;

import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
	public List<Auxiliar> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	public Auxiliar findById(Long id);
}
