package DAO;

import java.util.ArrayList;
import java.util.List;

import Entity.Usuario;

public class UsuarioDAO {
    private List<Usuario> usuarios;
     public UsuarioDAO() {
        usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}
