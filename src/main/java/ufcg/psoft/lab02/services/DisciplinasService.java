package ufcg.psoft.lab02.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab02.dao.DisciplinasRepository;
import ufcg.psoft.lab02.entities.Disciplina;
import ufcg.psoft.lab02.services.comparators.ComparadorDisciplinaPorNota;

import java.util.Collections;
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

    public boolean disciplinaExists(Long id) {

        return disciplinasDAO.existsById(id);
    }

    public Disciplina atualizaDisciplina(Long id, String nome) {

        Disciplina disciplina = disciplinasDAO.getOne(id);
        disciplina.setNome(nome);

        disciplinasDAO.deleteById(id);

        return disciplinasDAO.save(disciplina);
    }

    public Disciplina atualizaDisciplina(Long id, double nota) {

        Disciplina disciplina = disciplinasDAO.getOne(id);
        disciplina.setNota(nota);

        disciplinasDAO.deleteById(id);

        return disciplinasDAO.save(disciplina);
    }

    public void removeDisciplina(Long id) {

        disciplinasDAO.deleteById(id);
    }

    public List<Disciplina> getRankingDisciplinas() {

        List<Disciplina> disciplinas = getDisciplinas();
        //Collections.sort(disciplinas, new ComparadorDisciplinaPorNota());

        return disciplinas;
    }
}
