package com.challenge.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.challenge.entity.Reserva;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ReservaDAO {
    private EntityManager entityManager;

    public Reserva findById(Long id) {
        return entityManager.find(Reserva.class, id);
    }

    public List<Reserva> list() {
        final String jpql = "SELECT r FROM Reserva AS r";   
        return entityManager.createQuery(jpql, Reserva.class).getResultList();
    }

    public void remove(Reserva reserva) {
        Reserva reservaAux = entityManager.merge(reserva);
        entityManager.remove(reservaAux);
    }

    public void save(Reserva reserva) {
        entityManager.persist(reserva);
    }

    public void update(Reserva reserva) {
        entityManager.merge(reserva);
    }

    public List<Reserva> search(String busqueda) {
        final String jpql = "SELECT r FROM Reserva r WHERE"
                + " r.fechaEntrada LIKE '%'||:busqueda||'%' OR"
                + " r.fechaSalida LIKE '%'||:busqueda||'%' OR"
                + " CAST(r.valor AS java.lang.String)=:busqueda OR"
                + " r.formaDePago LIKE '%'||:busqueda||'%'";

        return entityManager.createQuery(jpql, Reserva.class).setParameter("busqueda", busqueda).getResultList();
    }

    public void removeById(Long id) {
        final String jpql = "DELETE FROM Reserva r WHERE r.id=:id";
        entityManager.createQuery(jpql).setParameter("id", id).executeUpdate();
    }

    public void removeByHuespedId(Long id) {
        final String jpql = "DELETE FROM Reserva r WHERE r.huesped.id=:id";
        entityManager.createQuery(jpql).setParameter("id", id).executeUpdate();
    }
}