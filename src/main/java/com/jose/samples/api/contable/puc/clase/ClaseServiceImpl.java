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

	public Clase findById (Long id) {
		return this.claseRepository.findById(id);
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


	//builder class
	public static class ClaseBuilder {

		private String tipo;
		private String codigo;
		private String nombre;

		public ClaseBuilder() {}

		public ClaseBuilder setTipo(String tipo){
			this.tipo = tipo;
			return this;
		}

		public ClaseBuilder setCodigo(String codigo){
			this.codigo = codigo;
			return this;}

		public ClaseBuilder setNombre(String nombre){
			this.nombre = nombre;
			return this;
		}

		public String getTipo() {
			return tipo;
		}

		public String getCodigo() {
			return codigo;
		}

		public String getNombre() {
			return nombre;
		}

		public Clase build() {
			return new Clase(this);
		}
	}
}
