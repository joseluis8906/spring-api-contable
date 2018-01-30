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
	public Grupo findOne (long id) {
		return this.grupoRepository.findOne(id);
	}

	@Override
	public List<Grupo> findByCodigoOrNombre (String codigo, String nombre) {
		return this.grupoRepository.findByCodigoContainingOrNombreContaining (codigo, nombre);
	}

	@Override
	public List<Grupo> findByClase(Long clase) {
		return this.grupoRepository.findByClase(clase);
	}

	@Override
	public List<Grupo> findAll() {
		return this.grupoRepository.findAll();
	}

	@Override
	public Grupo add (Grupo clase) {
		this.grupoRepository.save(clase);
		return clase;
	}

	@Override
	public void update (Grupo clase) {
		this.grupoRepository.save(clase);
	}

	@Override
	public void delete (long id) {
		this.grupoRepository.delete(id);
	}
}
