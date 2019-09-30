package ufcg.psoft.lab02.services;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import ufcg.psoft.lab02.controllers.filters.TokenFilter;

import javax.servlet.ServletException;

@Service
public class JWTService {

    private UsuariosService usuariosService;

    public JWTService(UsuariosService usuariosService) {

        super();
        this.usuariosService = usuariosService;
    }

    public boolean usuarioExiste(String authorizationHeader) throws ServletException {

        String subject = getSujeitoDoToken(authorizationHeader);

        return usuariosService.getUsuario(subject).isPresent();
    }

    public String getSujeitoDoToken(String authorizationHeader) throws ServletException {

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new ServletException("Token inexistente ou mal formatado!");

        String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);
        String subject = null;

        try {

            subject = Jwts.parser().setSigningKey("DefaultUserLogin").parseClaimsJws(token).getBody().getSubject();
            if(!(usuariosService.getUsuario(subject)).isPresent())
                throw new ServletException("Token expirado!");

        } catch (Exception err) {

            throw new ServletException("Token inválido ou expirado!");
        }

        return subject;
    }
}
