package ufcg.psoft.lab02.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ufcg.psoft.lab02.entities.Usuario;
import ufcg.psoft.lab02.services.UsuariosService;

@RestController
public class UsuariosController {

    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {

        super();
        this.usuariosService = usuariosService;
    }

    @PostMapping("api/usuarios")
    public ResponseEntity<HttpStatus> adicionaUsuario(@RequestBody Usuario usuario) {

        if(usuariosService.adicionaUsuario(usuario))
            return new ResponseEntity<>(HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
