package com.example.exam.dao;

import com.example.exam.model.Employe;
import com.example.exam.model.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeDao {
    private final EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public void save(Employe employe) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(employe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Employe find(Long id) {
        return em.find(Employe.class, id);
    }

    public void delete(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            Employe employe = em.find(Employe.class, id);
            transaction.begin();
            em.remove(employe);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employe> getEmployees() {
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e", Employe.class);
        return query.getResultList();
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
