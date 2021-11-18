package br.com.fiap.ambers.EAlimentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.EAlimentos.model.Retirada;
import br.com.fiap.ambers.EAlimentos.repository.RetiradaRepository;

@Service
public class RetiradaService {

	@Autowired
	private RetiradaRepository repository;
	
	public List<Retirada> listarRetiradas(){
		return this.repository.findAll();
	}
	
	public void retirarAlimento(Retirada retirada) throws Exception {
		retirada.getAlimento().setQuantidade(this.retirarQuantidade(retirada));
		this.repository.save(retirada);
	}
	
	private Long retirarQuantidade(Retirada retirada) throws Exception {
		Long quantidadeAtual = retirada.getAlimento().getQuantidade() - retirada.getQuantidadeRetirados();
		
		if(quantidadeAtual < 0)
			throw new Exception("Não é possível retirar mais itens que o disponível!");
		
		return quantidadeAtual;
		
	}
}
