package br.com.fiap.ambers.EAlimentos.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ambers.EAlimentos.inDto.RetiradaEntradaDto;
import br.com.fiap.ambers.EAlimentos.model.Alimento;
import br.com.fiap.ambers.EAlimentos.model.Retirada;
import br.com.fiap.ambers.EAlimentos.outDto.RetiradaSaidaDto;
import br.com.fiap.ambers.EAlimentos.service.AlimentoService;
import br.com.fiap.ambers.EAlimentos.service.RetiradaService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/retirada")
public class ApiRetiradaController {

	@Autowired
	private RetiradaService service;
	
	@Autowired
	private AlimentoService alimentoService;
	
	@GetMapping
	public ResponseEntity<List<RetiradaSaidaDto>> listarRetiradas(){
		List<RetiradaSaidaDto> retorno = new ArrayList<RetiradaSaidaDto>();
		
		try {
			
			List<Retirada> retiradas = this.service.listarRetiradas();
			
			for(Retirada retirada : retiradas) {
				retorno.add(new RetiradaSaidaDto(retirada));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ResponseEntity.ok(retorno);
	}
	
	public ResponseEntity<RetiradaEntradaDto> retirarAlimento(@RequestBody RetiradaEntradaDto entrada) {
		
		try {
			
			Optional<Alimento> alimento = this.alimentoService.buscarPorCodigo(entrada.getId());
			
			if(alimento.isPresent()) {
				Retirada retirada = new Retirada(entrada, alimento.get());
				
				this.service.retirarAlimento(retirada);
			}
			
		} catch (Exception e) {
			entrada.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entrada);
		}
		
		return ResponseEntity.ok(entrada);
		
	}
	
}
