package com.jose.samples.api.contable.puc.auxiliar;

import com.jose.samples.api.contable.puc.subcuenta.SubCuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuxiliarRepository extends JpaRepository<Auxiliar, Long> {
	List<Auxiliar> findByCodigoContainingOrNombreContaining(String codigo, String nombre);
	Optional<Auxiliar> findById(Long id);
	void deleteById(Long id);
}
