package com.challenge.dao;

import java.util.Optional;

import javax.persistence.EntityManager;

import com.challenge.entity.Variable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class VariableDAO {
    private EntityManager entityManager;

    public Variable findById(String id) {
        return entityManager.find(Variable.class, id);
    }

    public void save(Variable variable) {
        entityManager.persist(variable);
    }

    public void update(Variable variable) {
        entityManager.merge(variable);
    }

    public void removeById(String id) {
        final String jpql = "DELETE FROM Variable v WHERE v.id=:id";
        entityManager.createQuery(jpql).setParameter("id", id).executeUpdate();
    }

    public boolean existsById(String id) {
        final String jpql = "SELECT COUNT(v) FROM Variable v WHERE v.id = :id";
        Long cantidad = entityManager.createQuery(jpql, Long.class)
                                .setParameter("id", id)
                                .getSingleResult();
        return cantidad > 0;
    }
    
}