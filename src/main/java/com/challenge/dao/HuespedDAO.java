package com.challenge.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.challenge.entity.Huesped;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class HuespedDAO {
    private EntityManager entityManager;

    public Huesped findById(Long id) {
        return entityManager.find(Huesped.class, id);
    }

    public List<Huesped> list() {
        final String jpql = "SELECT h FROM Huesped AS h";   
        return entityManager.createQuery(jpql, Huesped.class).getResultList();
    }

    public void remove(Huesped huesped) {
        Huesped reservaAux = entityManager.merge(huesped);
        entityManager.remove(reservaAux);
    }

    public void save(Huesped huesped) {
        entityManager.persist(huesped);
    }

    public void update(Huesped huesped) {
        entityManager.merge(huesped);
    }

    public List<Huesped> search(String busqueda) {
        final String jpql = "SELECT h FROM Huesped h WHERE"
                + " h.fechaEntrada LIKE '%'||:busqueda||'%' OR"
                + " h.fechaSalida LIKE '%'||:busqueda||'%' OR"
                + " CAST(h.valor AS java.lang.String)=:busqueda OR"
                + " h.formaDePago LIKE '%'||:busqueda||'%'";

        return entityManager.createQuery(jpql, Huesped.class).setParameter("busqueda", busqueda).getResultList();
    }

    public void removeById(Long id) {
        final String jpql = "DELETE FROM Huesped h WHERE h.id=:id";
        entityManager.createQuery(jpql).setParameter("id", id).executeUpdate();
    }

    public void removeByHuespedId(Long id) {
        final String jpql = "DELETE FROM Huesped h WHERE h.huesped.id=:id";
        entityManager.createQuery(jpql).setParameter("id", id).executeUpdate();
    }
}
