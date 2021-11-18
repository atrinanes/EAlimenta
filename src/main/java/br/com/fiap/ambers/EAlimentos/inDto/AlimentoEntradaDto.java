package br.com.fiap.ambers.EAlimentos.inDto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AlimentoEntradaDto {
	
	private String nome;
	
	private String tipo;
	
	private LocalDate dataVencimento;
	
	private Long quantidade;
}
