package ufcg.psoft.lab02.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufcg.psoft.lab02.entities.Usuario;
import ufcg.psoft.lab02.services.JWTService;
import ufcg.psoft.lab02.services.UsuariosService;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final String TOKEN_KEY = "DefaultUserLogin";

    private UsuariosService usuariosService;
    private JWTService jwtService;

    public LoginController(UsuariosService usuariosService, JWTService jwtService) {

        super();
        this.usuariosService = usuariosService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {

        Optional<Usuario> authUsuario = usuariosService.getUsuario(usuario.getEmail());

        if(!authUsuario.isPresent())
            throw new ServletException("Usuario não encontrado!");

        if(!authUsuario.get().getSenha().equals(usuario.getSenha()))
            throw new ServletException("Senha incorreta!");

        String token = Jwts.builder().setSubject(authUsuario.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY).setExpiration(new Date(System.currentTimeMillis() + 80 * 1000)).compact();
        return new LoginResponse(token);
    }

    @DeleteMapping("/usuarios")
    public ResponseEntity<Usuario> removeUsuario(@RequestHeader("Authorization") String header) throws ServletException {

        try {

            if(jwtService.usuarioExiste(header)) {

                Optional<Usuario> usuario = usuariosService.removeUsuario(jwtService.getSujeitoDoToken(header));

                if(usuario.isPresent()) {
                    return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (ServletException err) {

            System.out.println("Erro: " + err);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private class LoginResponse {

        public String token;

        public LoginResponse(String token) {

            this.token = token;
        }
    }
}

