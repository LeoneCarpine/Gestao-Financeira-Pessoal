package app.dao;
import app.model.*;
import java.util.List;

public class CategoriaDAO extends GenericDAO<Categoria> {
    public List<Categoria> listarPorUsuario(Usuario usuario) {
        return em.createQuery("FROM Categoria WHERE usuario = :usuario", Categoria.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }
}