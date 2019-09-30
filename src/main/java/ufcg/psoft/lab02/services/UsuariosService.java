package ufcg.psoft.lab02.services;

import org.springframework.stereotype.Service;
import ufcg.psoft.lab02.dao.UsuariosRepository;
import ufcg.psoft.lab02.entities.Usuario;

import java.util.Optional;

@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, String> usuariosDao;

    public UsuariosService(UsuariosRepository<Usuario, String> usuariosDao) {

        super();
        this.usuariosDao = usuariosDao;
    }

    public boolean adicionaUsuario(Usuario usuario) {

        if(!usuariosDao.existsById(usuario.getEmail())) {

            usuariosDao.save(usuario);
            return true;
        }

        return false;
    }

    public Optional<Usuario> getUsuario(String email) {

        return usuariosDao.findById(email);
    }

    public Optional<Usuario> removeUsuario(String email) {

        System.out.println(email);
        Optional<Usuario> usuario = usuariosDao.findById(email);
        if(usuario.isPresent()) {

            usuariosDao.delete(usuario.get());
        }

        return usuario;
    }
}
