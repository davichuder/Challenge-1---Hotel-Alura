package com.challenge.controller;

import javax.persistence.EntityManager;

import com.challenge.dao.VariableDAO;
import com.challenge.entity.Variable;
import com.challenge.factory.ConnectionFactory;

public class VariableController {
    private VariableDAO variableDAO;

    private EntityManager entityManager;

    public VariableController() {
        entityManager = ConnectionFactory.getEntityManager();
        variableDAO = new VariableDAO(entityManager);
    }

    public void save(Variable variable) {
        entityManager.getTransaction().begin();
        variableDAO.save(variable);
        entityManager.getTransaction().commit();
    }

    public void update(Variable variable) {
        entityManager.getTransaction().begin();
        variableDAO.update(variable);
        entityManager.getTransaction().commit();
    }

    public Variable findById(String nombre) {
        return variableDAO.findById(nombre);
    }

    public void removeById(String nombre) {
        entityManager.getTransaction().begin();
        variableDAO.removeById(nombre);
        entityManager.getTransaction().commit();
    }

    public boolean existsById(String nombre) {
        return variableDAO.existsById(nombre);
    }
}