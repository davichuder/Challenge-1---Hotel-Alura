package com.challenge.controller;

import java.util.List;

import javax.persistence.EntityManager;

import com.challenge.dao.HuespedDAO;
import com.challenge.entity.Huesped;
import com.challenge.factory.ConnectionFactory;

public class HuespedController {

    private HuespedDAO huespedDAO;

    private EntityManager entityManager;

    public HuespedController() {
        entityManager = ConnectionFactory.getEntityManager();
        huespedDAO = new HuespedDAO(entityManager);
    }

    public void save(Huesped huesped) {
        entityManager.getTransaction().begin();
        huespedDAO.save(huesped);
        entityManager.getTransaction().commit();
    }

    public void update(Huesped huesped) {
        entityManager.getTransaction().begin();
        huespedDAO.update(huesped);
        entityManager.getTransaction().commit();
    }

    public Huesped findById(Long id) {
        return huespedDAO.findById(id);
    }

    public List<Huesped> list() {
        return huespedDAO.list();
    }

    public List<Huesped> search(String busqueda) {
        return huespedDAO.search(busqueda);
    }

    public void removeById(Long id) {
        entityManager.getTransaction().begin();
        huespedDAO.removeById(id);
        entityManager.getTransaction().commit();
    }

    public void removeByHuespedId(Long id) {
        entityManager.getTransaction().begin();
        huespedDAO.removeByHuespedId(id);
        entityManager.getTransaction().commit();
    }
}