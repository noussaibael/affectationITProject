package com.example.exam.dao;

import com.example.exam.model.JPAUtil;
import com.example.exam.model.Project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProjectDao {
    private final EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public void save(Project project) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void edit(Project project) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Project find(Long id) {
        return em.find(Project.class, id);
    }

    public void delete(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            Project project = em.find(Project.class, id);
            transaction.begin();
            em.remove(project);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Project> getProjects() {
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p", Project.class);
        return query.getResultList();
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}

