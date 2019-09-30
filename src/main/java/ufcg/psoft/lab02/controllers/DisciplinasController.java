package ufcg.psoft.lab02.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab02.entities.Disciplina;
import ufcg.psoft.lab02.services.DisciplinasService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DisciplinasController {

    private DisciplinasService disciplinasService;

    public DisciplinasController(DisciplinasService disciplinasService) {

        super();
        this.disciplinasService = disciplinasService;
    }

    @PostMapping("/disciplinas")
    public ResponseEntity<Disciplina> adicionaDisciplina(@RequestBody Disciplina disciplina) {

        return new ResponseEntity<Disciplina>(disciplinasService.adicionaDisciplina(disciplina), HttpStatus.CREATED);
    }

    @PostMapping("/disciplinas/start")
    public ResponseEntity<Disciplina> adicionaDisciplinas(@RequestBody List<Disciplina> disciplinas) {

        try {

            disciplinasService.adicionaDisciplinas(disciplinas);
            return new ResponseEntity<Disciplina>(HttpStatus.ACCEPTED);
        } catch (Exception err) {

            System.out.println("Não foi possivel adicionar as disciplinas selecionadas. Erro: " + err.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {

        return new ResponseEntity<List<Disciplina>>(disciplinasService.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable Long id) {

        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent())
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/{id}/nome")
    public ResponseEntity<Disciplina> atualizaNomeDisciplina(@PathVariable Long id, @RequestBody String nome) {

        if(disciplinasService.disciplinaExists(id))
            return new ResponseEntity<Disciplina>(disciplinasService.atualizaDisciplina(id,nome), HttpStatus.OK);
        else
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/nota/{id}")
    public ResponseEntity<Disciplina> atualizaNotaDisciplina(@PathVariable Long id, @RequestBody double nota) {

        if(disciplinasService.disciplinaExists(id))
            return new ResponseEntity<Disciplina>(disciplinasService.atualizaDisciplina(id,nota), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/disciplinas/{id}")
    public ResponseEntity<Disciplina> removeDisciplina(@PathVariable Long id) {

        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent()) {

            disciplinasService.removeDisciplina(id);
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/likes/{id}")
    public ResponseEntity<Disciplina> adicionaLike(@PathVariable Long id) {

        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent()) {

            return new ResponseEntity<>(disciplinasService.adicionaLike(id), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/disciplinas/comentarios/{id}")
    public ResponseEntity<Disciplina> adicionaComentario(@PathVariable Long id, @RequestBody String comentario) {

        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent()) {

            return new ResponseEntity<>(disciplinasService.adicionaComentario(id,comentario), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/disciplinas/ranking/notas")
    public ResponseEntity<List<Disciplina>> rankingDisciplinasPorNotas() {

        return new ResponseEntity<List<Disciplina>>(disciplinasService.getRankingDisciplinasPorNotas(), HttpStatus.OK);
    }

    @GetMapping("/disciplinas/ranking/likes")
    public ResponseEntity<List<Disciplina>> rankingDisciplinasPorLikes() {

        return new ResponseEntity<>(disciplinasService.getRankingDisciplinasPorLikes(), HttpStatus.OK);
    }
}
