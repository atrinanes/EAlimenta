package br.com.fiap.ambers.EAlimentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.EAlimentos.model.Alimento;
import br.com.fiap.ambers.EAlimentos.repository.AlimentoRepository;

@Service
public class AlimentoService {

	@Autowired
	private AlimentoRepository repository;

	public List<Alimento> buscarTodos() {
		return this.repository.findAll();
	}

	public Optional<Alimento> buscarPorCodigo(Long codigo) {
		return this.repository.findById(codigo);
	}

	public void salvar(Alimento alimento) {
		this.repository.save(alimento);
	}

	public void editar(Alimento alimento) {
		if (this.repository.existsById(alimento.getId()))
			this.repository.save(alimento);
	}

	public void excluir(Long id) {
		if (this.repository.existsById(id))
			this.repository.deleteById(id);
	}

}
