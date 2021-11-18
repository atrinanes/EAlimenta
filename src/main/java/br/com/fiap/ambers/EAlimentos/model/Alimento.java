package br.com.fiap.ambers.EAlimentos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.fiap.ambers.EAlimentos.inDto.AlimentoEntradaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@Entity
@Table(name = "TB_ALIMENTO")
@NoArgsConstructor
public class Alimento {
	
	@Id
	@GeneratedValue
	@Column(name = "id_alimento")
	private Long id;
	
	@Column(name = "nm_alimento", nullable = false)
	private String nome;
	
	@Column(name = "tipo_alimento", nullable = false)
	private String tipo;
	
	@Column(name = "data_vencimento", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimento;
	
	@Column(name = "data_cadastro", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCadastro;
	
	@Column(name = "quantidade", nullable = false)
	private Long quantidade;
	
	@OneToMany(mappedBy = "alimento", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Retirada> retiradas;
	
	public Alimento(AlimentoEntradaDto entrada) {
		this.nome = entrada.getNome();
		this.tipo = entrada.getTipo();
		this.dataVencimento = entrada.getDataVencimento();
		this.quantidade = entrada.getQuantidade();
		this.dataCadastro = LocalDate.now();
	}

	public void addRetirada(Retirada retirada) {
		if (retirada == null)
			retiradas = new ArrayList<>();
		//adicionar o aluno na lista de alunos do grupo
		retiradas.add(retirada);
		//setar o grupo do aluno
		retirada.setAlimento(this);
	}
	
}
