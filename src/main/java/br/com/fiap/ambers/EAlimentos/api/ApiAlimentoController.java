package br.com.fiap.ambers.EAlimentos.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ambers.EAlimentos.inDto.AlimentoEntradaDto;
import br.com.fiap.ambers.EAlimentos.model.Alimento;
import br.com.fiap.ambers.EAlimentos.outDto.AlimentoSaidaDto;
import br.com.fiap.ambers.EAlimentos.service.AlimentoService;

@RestController
@RequestMapping("/api/alimento")
public class ApiAlimentoController {

	@Autowired
	private AlimentoService service;

	@GetMapping
	public ResponseEntity<List<AlimentoSaidaDto>> buscarTodos() {
		List<AlimentoSaidaDto> retorno = new ArrayList<AlimentoSaidaDto>();

		try {

			List<Alimento> alimentos = this.service.buscarTodos();

			for (Alimento alimento : alimentos) {
				retorno.add(new AlimentoSaidaDto(alimento));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return ResponseEntity.ok(retorno);
	}

	@GetMapping("{id}")
	public ResponseEntity<AlimentoSaidaDto> buscarPorCodigo(@PathVariable Long id) {
		AlimentoSaidaDto retorno = new AlimentoSaidaDto();

		try {
			Optional<Alimento> alimento = this.service.buscarPorCodigo(id);

			if (alimento.isPresent())
				retorno = new AlimentoSaidaDto(alimento.get());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return ResponseEntity.ok(retorno);
	}

	@PostMapping
	public ResponseEntity<AlimentoEntradaDto> incluir(@RequestBody AlimentoEntradaDto entrada) {

		try {

			Alimento alimento = new Alimento(entrada);
			this.service.salvar(alimento);

		} catch (Exception e) {
			// TODO:
		}

		return ResponseEntity.ok(entrada);
	}

	@PutMapping("{id}")
	public ResponseEntity<AlimentoEntradaDto> editar(@RequestBody AlimentoEntradaDto entrada, @PathVariable Long id) {

		try {

			Alimento alimento = new Alimento(entrada);
			alimento.setId(id);
			this.service.editar(alimento);

		} catch (Exception e) {
			// TODO:
		}

		return ResponseEntity.ok(entrada);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> editar(@PathVariable Long id) {

		try {
			this.service.excluir(id);

		} catch (Exception e) {
			// TODO:
		}

		return ResponseEntity.ok("Alimento " + id.toString() + " e seus registros excluídos.");
	}
}
