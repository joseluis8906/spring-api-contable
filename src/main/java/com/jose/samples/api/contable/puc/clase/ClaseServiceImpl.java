package com.jose.samples.api.contable.puc.clase;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseServiceImpl implements ClaseService {

	private ClaseRepository claseRepository;

	public ClaseServiceImpl (ClaseRepository claseRepository) {
		this.claseRepository = claseRepository;
	}

	public Clase findOne (long id) {
		return this.claseRepository.findOne(id);
	}

	public List<Clase> findByCodigoOrNombre (String codigo, String nombre) {
		return this.claseRepository.findByCodigoOrNombre (codigo, nombre);
	}

	public List<Clase> findAll() {
		return this.claseRepository.findAll();
	}

	public void save (Clase clase) {
		this.claseRepository.save(clase);
	}

	public void update (Clase clase) {
		this.claseRepository.update(clase);
	}

	public void delete (long id) {
		this.claseRepository.delete(id);
	}



}
