package app.dao;
import app.model.*;

public class UsuarioDAO extends GenericDAO<Usuario> {
    public Usuario buscarPorEmailESenha(String email, String senha) {
        return em.createQuery("FROM Usuario WHERE email = :email AND senha = :senha", Usuario.class)
                .setParameter("email", email)
                .setParameter("senha", senha)
                .getSingleResult();
    }
}