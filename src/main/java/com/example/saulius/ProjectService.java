package com.example.saulius;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {

    private ProjectRepository projectRepository;
    
    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        LocalDate today = LocalDate.now();
        if (!project.getProjectStartDate().isEqual(today)) {
            throw new IllegalArgumentException("Invalid project_start_date. It should be today's date.");
        }

        LocalDate projectEndDate = project.getProjectStartDate().plusYears(1);
        if (project.getProjectDueDate().isAfter(projectEndDate)) {
            throw new IllegalArgumentException("Invalid project_end_date. It cannot be longer than a year from the start date.");
        }

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }

    public Project updateProject(Long projectId, Project updatedProject) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            project.setProjectName(updatedProject.getProjectName());
            project.setProjectStartDate(updatedProject.getProjectStartDate());
            project.setProjectDueDate(updatedProject.getProjectDueDate());
            project.setEmployees(updatedProject.getEmployees());
            return projectRepository.save(project);
        }
        return null;
    }

    public boolean deleteProject(Long projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
            return true;
        }
        return false;
    }
    
    public int getParticipantCountByProjectId(Long projectId) {
        return projectRepository.getParticipantCountByProjectId(projectId);
    }

    public int getParticipantCountByProjectName(String projectName) {
        return projectRepository.getParticipantCountByProjectName(projectName);
    }
}
