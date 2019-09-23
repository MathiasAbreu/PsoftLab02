package ufcg.psoft.lab02.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufcg.psoft.lab02.entities.Disciplina;

import java.io.Serializable;

@Repository
public interface DisciplinasRepository<T, ID extends Serializable> extends JpaRepository<Disciplina, Long> {
}
