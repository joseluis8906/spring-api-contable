package com.jose.samples.api.contable.puc.auxiliar;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuxiliarServiceImpl implements AuxiliarService {

	private AuxiliarRepository auxiliarRepository;

	public AuxiliarServiceImpl(AuxiliarRepository auxiliarRepository) {
		this.auxiliarRepository = auxiliarRepository;
	}

	public Auxiliar findById (Long id) {
		return this.auxiliarRepository.findById(id).get();
	}

	public List<Auxiliar> findByCodigoOrNombre (String codigo, String nombre) {
		return this.auxiliarRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	public List<Auxiliar> findAll() {
		return this.auxiliarRepository.findAll();
	}

	public Auxiliar add (Auxiliar auxiliar) {
		this.auxiliarRepository.save(auxiliar);
		return auxiliar;
	}

	public void update (Auxiliar auxiliar) {
		this.auxiliarRepository.save(auxiliar);
	}

	public void delete (Long id) {
		this.auxiliarRepository.deleteById(id);
	}
}
