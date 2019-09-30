package ufcg.psoft.lab02.services.comparators;

import ufcg.psoft.lab02.entities.Disciplina;

import java.util.Comparator;

public class ComparadorDisciplinaPorLike implements Comparator<Disciplina> {

    @Override
    public int compare(Disciplina disciplina01, Disciplina disciplina02) {

        if(disciplina01.getLikes() > disciplina02.getLikes())
            return -1;
        else if (disciplina01.getLikes() < disciplina02.getLikes())
            return 1;
        else
            return disciplina01.getNome().compareTo(disciplina02.getNome());
    }
}
