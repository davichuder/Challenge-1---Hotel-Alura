package com.challenge.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.challenge.dao.ReservaDAO;
import com.challenge.entity.Reserva;
import com.challenge.factory.ConnectionFactory;

public class ReservaController {

    private ReservaDAO reservaDAO;

    private EntityManager entityManager;

    public ReservaController() {
        entityManager = ConnectionFactory.getEntityManager();
        reservaDAO = new ReservaDAO(entityManager);
    }

    public void save(Reserva reserva) {
        entityManager.getTransaction().begin();
        reservaDAO.save(reserva);
        entityManager.getTransaction().commit();
    }

    public void update(Reserva reserva) {
        entityManager.getTransaction().begin();
        reservaDAO.update(reserva);
        entityManager.getTransaction().commit();
    }

    public Reserva findById(Long id) {
        return reservaDAO.findById(id);
    }

    public List<Reserva> list() {
        return reservaDAO.list();
    }

    public List<Reserva> search(String busqueda) {
        return reservaDAO.search(busqueda);
    }

    public void removeById(Long id) {
        entityManager.getTransaction().begin();
        reservaDAO.removeById(id);
        entityManager.getTransaction().commit();
    }

    public void removeByHuespedId(Long id) {
        entityManager.getTransaction().begin();
        reservaDAO.removeByHuespedId(id);
        entityManager.getTransaction().commit();
    }
}