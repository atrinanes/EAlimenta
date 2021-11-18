package br.com.fiap.ambers.EAlimentos.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationFieldError {
	
	private String campo;
	private String erro;

}
