package ufcg.psoft.lab02.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab02.dao.DisciplinasRepository;
import ufcg.psoft.lab02.entities.Disciplina;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinasService {

    private DisciplinasRepository<Disciplina, Long> disciplinasDAO;

    public DisciplinasService(DisciplinasRepository<Disciplina, Long> disciplinasDAO) {

        super();
        this.disciplinasDAO = disciplinasDAO;
    }

    public Disciplina adicionaDisciplina(Disciplina disciplina) {

        return disciplinasDAO.save(disciplina);
    }

    public List<Disciplina> getDisciplinas() {

        return disciplinasDAO.findAll();
    }

    public Optional<Disciplina> getDisciplina(Long id) {

        return disciplinasDAO.findById(id);
    }
}
