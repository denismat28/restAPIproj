package com.task.restAPIproj.service;

import com.task.restAPIproj.entity.PassportEntity;
import com.task.restAPIproj.entity.ProjectEntity;
import com.task.restAPIproj.exception.PassportNotFoundException;
import com.task.restAPIproj.exception.ProjectAlreadyExistException;
import com.task.restAPIproj.exception.ProjectNotFoundException;
import com.task.restAPIproj.model.Passport;
import com.task.restAPIproj.model.Project;
import com.task.restAPIproj.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public ProjectEntity creation(ProjectEntity proj) throws ProjectAlreadyExistException {

        if (projectRepository.findById(proj.getProjectId()) != null) {
            throw new ProjectAlreadyExistException("Project already exist!");
        }
        return projectRepository.save(proj);
    }


    public Page<ProjectEntity> getAll(Pageable page) {
        return projectRepository.findAll(page);
    }

    public Project getOne(Long id) throws ProjectNotFoundException {
        ProjectEntity proj = projectRepository.findById(id).get();
        if (proj == null) {
            throw new ProjectNotFoundException("Project not found!");
        }
        return Project.toModel(proj);
    }

    public Long delete(Long id) {
        projectRepository.deleteById(id);
        return id;
    }


    public Project changeTitle(ProjectEntity proj) throws ProjectNotFoundException {
        if (projectRepository.findById(proj.getProjectId()) == null) {
            throw new ProjectNotFoundException("Project does not exist! Plz, create new one!");
        }
        ProjectEntity psp = projectRepository.getById(proj.getProjectId());
        psp.setTitle(proj.getTitle());
        return Project.toModel(psp);

    }
}
