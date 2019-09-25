package ufcg.psoft.lab02.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab02.entities.Disciplina;
import ufcg.psoft.lab02.services.DisciplinasService;

import java.util.List;
import java.util.Optional;

@RestController
public class DisciplinasController {

    private DisciplinasService disciplinasService;

    public DisciplinasController(DisciplinasService disciplinasService) {

        super();
        this.disciplinasService = disciplinasService;
    }

    @PostMapping("/v1/api/disciplinas")
    public ResponseEntity<Disciplina> adicionaDisciplina(@RequestBody Disciplina disciplina) {

        return new ResponseEntity<Disciplina>(disciplinasService.adicionaDisciplina(disciplina), HttpStatus.CREATED);
    }

    @GetMapping("/v1/api/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinas() {

        return new ResponseEntity<List<Disciplina>>(disciplinasService.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable Long id) {

        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent())
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("v1/api/disciplinas/{id}/nome")
    public ResponseEntity<Disciplina> atualizaNomeDisciplina(@PathVariable Long id, @RequestBody String nome) {

        if(disciplinasService.disciplinaExists(id))
            return new ResponseEntity<Disciplina>(disciplinasService.atualizaDisciplina(id,nome), HttpStatus.OK);
        else
            return new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("v1/api/disciplinas/{id}/nota")
    public ResponseEntity<Disciplina> atualizaNotaDisciplina(@PathVariable Long id, @RequestBody double nota) {

        if(disciplinasService.disciplinaExists(id))
            return new ResponseEntity<Disciplina>(disciplinasService.atualizaDisciplina(id,nota), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> removeDisciplina(@PathVariable Long id) {

        Optional<Disciplina> disciplina = disciplinasService.getDisciplina(id);
        if(disciplina.isPresent()) {

            disciplinasService.removeDisciplina(id);
            return new ResponseEntity<Disciplina>(disciplina.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("v1/api/disciplinas/ranking")
    public ResponseEntity<List<Disciplina>> rankingDisciplinas() {

        return new ResponseEntity<List<Disciplina>>(disciplinasService.getRankingDisciplinas(), HttpStatus.OK);
    }
}
