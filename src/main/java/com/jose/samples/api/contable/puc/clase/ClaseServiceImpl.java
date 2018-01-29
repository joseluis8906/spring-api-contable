package com.jose.samples.api.contable.puc.clase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClaseServiceImpl implements ClaseService {

	private ClaseRepository claseRepository;

	public ClaseServiceImpl (ClaseRepository claseRepository) {
		this.claseRepository = claseRepository;
	}

	public Clase findOne (long id) {
		return this.claseRepository.findOne(id);
	}

	public List<Clase> findByCodigoOrNombre (String codigo, String nombre) {
		return this.claseRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	public List<Clase> findAll() {
		return this.claseRepository.findAll();
	}

	public Clase add (Clase clase) {
		this.claseRepository.save(clase);
		return clase;
	}

	public void update (Clase clase) {
		this.claseRepository.save(clase);
	}

	public void delete (long id) {
		this.claseRepository.delete(id);
	}



}
