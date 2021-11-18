package br.com.fiap.ambers.EAlimentos.outDto;

import java.time.LocalDate;

import br.com.fiap.ambers.EAlimentos.model.Alimento;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlimentoSaidaDto {

	private String nome;
	
	private String tipo;
	
	private LocalDate dataVencimento;
	
	private Long quantidade;
	
	public AlimentoSaidaDto(Alimento alimento) {
		this.nome = alimento.getNome();
		this.tipo = alimento.getTipo();
		this.quantidade = alimento.getQuantidade();
		this.dataVencimento = alimento.getDataCadastro();
	}
	
}
