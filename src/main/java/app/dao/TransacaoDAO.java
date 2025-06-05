package app.dao;

import app.model.Categoria;
import app.model.Transacao;
import app.model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class TransacaoDAO extends GenericDAO<Transacao> {
    public List<Transacao> listarPorUsuario(Usuario usuario) {
        return em.createQuery("FROM Transacao WHERE usuario = :usuario", Transacao.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

    public List<Transacao> filtrar(LocalDate inicio, LocalDate fim, String tipo, Categoria categoria) {
        // Filtro opcional a ser implementado
        return null;
    }
}