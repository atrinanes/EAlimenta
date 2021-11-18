package br.com.fiap.ambers.EAlimentos.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ambers.EAlimentos.model.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Long>{

}
