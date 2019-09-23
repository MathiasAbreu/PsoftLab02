package ufcg.psoft.lab02.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Disciplina {

    @Id
    @GeneratedValue
    private long id;

    private String nome;
    private String comentarios;

    private double nota;
    private int likes;

    @JsonCreator
    public Disciplina() {
        super();
    }

    @JsonCreator
    public Disciplina(long id, String nome, double nota, String comentarios, int likes) {

        this.id = id;
        this.nome = nome;
        this.nota = nota;
        this.comentarios = comentarios;
        this.likes = likes;

    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
