package ufcg.psoft.lab02.services.comparators;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab02.entities.Disciplina;

import java.util.Comparator;

@Service
public class ComparadorDisciplinaPorNota implements Comparator<Disciplina> {

    @Override
    public int compare(Disciplina disciplina01, Disciplina disciplina02) {

        if(disciplina01.getNota() > disciplina02.getNota())
            return -1;
        else if (disciplina01.getNota() < disciplina02.getNota())
            return 1;
        else
            return 0;
    }
}
