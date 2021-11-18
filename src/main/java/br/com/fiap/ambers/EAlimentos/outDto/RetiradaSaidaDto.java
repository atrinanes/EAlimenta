package br.com.fiap.ambers.EAlimentos.outDto;

import java.time.LocalDate;

import br.com.fiap.ambers.EAlimentos.model.Retirada;
import lombok.Data;

@Data
public class RetiradaSaidaDto extends MessagesDto {

	private Long id;
	
	private String rg;
	
	private Long quantidadeRetirados;

	private LocalDate dataRetirada;
	
	private String nomeAlimento;
	
	private Long idAlimento;
	
	public RetiradaSaidaDto(Retirada retirada) {
		this.id = retirada.getId();
		this.rg = retirada.getRg();
		this.quantidadeRetirados = retirada.getQuantidadeRetirados();
		this.dataRetirada = retirada.getDataRetirada();
		this.nomeAlimento = retirada.getAlimento().getNome();
		this.idAlimento = retirada.getAlimento().getId();
	}
	
}
