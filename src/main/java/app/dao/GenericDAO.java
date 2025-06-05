package app.dao;

import jakarta.persistence.*;

public class GenericDAO<T> {
    protected EntityManager em = Persistence.createEntityManagerFactory("financeiroPU").createEntityManager();

    public void salvar(T entidade) {
        em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
    }

    public void atualizar(T entidade) {
        em.getTransaction().begin();
        em.merge(entidade);
        em.getTransaction().commit();
    }

    public void remover(T entidade) {
        em.getTransaction().begin();
        em.remove(em.merge(entidade));
        em.getTransaction().commit();
    }

    public T buscarPorId(Class<T> clazz, Long id) {
        return em.find(clazz, id);
    }
}
