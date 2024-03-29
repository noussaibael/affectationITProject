package com.example.exam.controller;

import com.example.exam.dao.ProjectDao;
import com.example.exam.model.Project;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;

import java.util.List;

@ManagedBean(name = "projectBean")
@RequestScoped
public class ProjectBean {
    private final ProjectDao projectDao = new ProjectDao();
    private Project project;

    public ProjectBean() {
        project = new Project();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String add() {
        project = new Project();
        return "/addProject.xhtml";
    }

    public String save() {
        projectDao.save(project);
        return "/index.xhtml";
    }

    public List<Project> getProjects() {
        return projectDao.getProjects();
    }

    public String delete(Long id) {
        projectDao.delete(id);
        return "/index.xhtml";
    }
}

