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

	@Override
	public Auxiliar findById (Long id) {
		return this.auxiliarRepository.findById(id);
	}

	@Override
	public List<Auxiliar> findByCodigoOrNombre (String codigo, String nombre) {
		return this.auxiliarRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	@Override
	public List<Auxiliar> findAll() {
		return this.auxiliarRepository.findAll();
	}

	@Override
	public Auxiliar add (Auxiliar auxiliar) {
		this.auxiliarRepository.save(auxiliar);
		return auxiliar;
	}

	@Override
	public void update (Auxiliar auxiliar) {
		this.auxiliarRepository.save(auxiliar);
	}

	@Override
	public void delete (Long id) {
		this.auxiliarRepository.delete(id);
	}

}
