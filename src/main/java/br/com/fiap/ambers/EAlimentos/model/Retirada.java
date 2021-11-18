package br.com.fiap.ambers.EAlimentos.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import br.com.fiap.ambers.EAlimentos.inDto.RetiradaEntradaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TB_RETIRADA")
public class Retirada {
	
	@Id
	@GeneratedValue
	@Column(name = "id_retirada")
	private Long id;
	
	@Column(name = "rg_retirada", nullable = false)
	private String rg;
	
	@Column(name = "quantidade", nullable = false)
	private Long quantidadeRetirados;
	
	@Column(name = "data_retirada", nullable = false)
	private LocalDate dataRetirada;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_alimento_retirado", nullable = false)
	private Alimento alimento;
	
	public Retirada(Alimento alimento) {
		this.alimento = alimento;
	}

	public Retirada(RetiradaEntradaDto entrada, Alimento alimento) {
		this.rg = entrada.getRg();
		this.quantidadeRetirados = entrada.getQuantidadeRetirados();
		this.alimento = alimento;
		this.dataRetirada = LocalDate.now();
	}

}
