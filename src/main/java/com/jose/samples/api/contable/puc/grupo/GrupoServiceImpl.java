package com.jose.samples.api.contable.puc.grupo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GrupoServiceImpl implements GrupoService {

	private GrupoRepository grupoRepository;

	public GrupoServiceImpl(GrupoRepository grupoRepository) {
		this.grupoRepository = grupoRepository;
	}

	@Override
	public Grupo findById (Long id) {
		return this.grupoRepository.findById(id);
	}

	@Override
	public List<Grupo> findByCodigoOrNombre (String codigo, String nombre) {
		return this.grupoRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	@Override
	public List<Grupo> findAll() {
		return this.grupoRepository.findAll();
	}

	@Override
	public Grupo add (Grupo grupo) {
		this.grupoRepository.save(grupo);
		return grupo;
	}

	@Override
	public void update (Grupo grupo) {
		this.grupoRepository.save(grupo);
	}

	@Override
	public void delete (Long id) {
		this.grupoRepository.delete(id);
	}
}
