package ufcg.psoft.lab02.controllers;

import org.springframework.web.bind.annotation.RestController;
import ufcg.psoft.lab02.services.DisciplinasService;

@RestController
public class DisciplinasController {

    private DisciplinasService disciplinasService;

    public DisciplinasController(DisciplinasService disciplinasService) {

        super();
        this.disciplinasService = disciplinasService;
    }

    
}
