package com.example.exam.controller;

import com.example.exam.dao.EmployeDao;
import com.example.exam.dao.ProjectDao;
import com.example.exam.model.Employe;

import com.example.exam.model.Project;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;

import java.util.List;

@ManagedBean(name = "employeBean")
@RequestScoped
public class EmployeBean {
    private EmployeDao employeDao = new EmployeDao();
    private Employe employe;

    private Long selectedEmployeId;
    private Long selectedProjetId;
    private List<Employe> employeList;
    private List<Project> projetList;

    private ProjectDao projetDao;
    public EmployeBean() {
        employeDao = new EmployeDao();
        projetDao = new ProjectDao();
        loadEmployeList();
        loadProjetList();
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String add() {
        employe = new Employe();
        return "/affectation.xhtml";
    }

    public String save() {
        employeDao.save(employe);
        return "/index.xhtml";
    }

    public List<Employe> getEmployes() {
        return employeDao.getEmployees();
    }

    public String delete(Long id) {
        employeDao.delete(id);
        return "/index.xhtml";
    }

    public Long getSelectedEmployeId() {
        return selectedEmployeId;
        }

        public void setSelectedEmployeId(Long selectedEmployeId) {
            this.selectedEmployeId = selectedEmployeId;
        }

        public Long getSelectedProjetId() {
            return selectedProjetId;
        }

        public void setSelectedProjetId(Long selectedProjetId) {
            this.selectedProjetId = selectedProjetId;
        }

        public List<Employe> getEmployeList() {
            return employeList;
        }

        public List<Project> getProjetList() {
            return projetList;
        }

        private void loadEmployeList() {
            employeList = employeDao.getEmployees();
        }

        private void loadProjetList() {
            projetList = projetDao.getProjects();
        }


    }

