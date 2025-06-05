package app.controller;

import app.dao.*;
import app.model.*;
import java.time.LocalDate;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario login(String email, String senha) {
        return usuarioDAO.buscarPorEmailESenha(email, senha);
    }

    public void cadastrar(String nome, String email, String senha) {
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(senha);
        usuarioDAO.salvar(u);
    }
}
