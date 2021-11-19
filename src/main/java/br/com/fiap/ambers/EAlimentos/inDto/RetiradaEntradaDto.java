package br.com.fiap.ambers.EAlimentos.inDto;

import java.time.LocalDate;

import br.com.fiap.ambers.EAlimentos.outDto.MessagesDto;
import lombok.Data;

@Data
public class RetiradaEntradaDto extends MessagesDto{
	
	private String rg;
	
	private Long quantidadeRetirados;
	
	private Long idAlimento;
	
}
