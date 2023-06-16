package com.example.saulius;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT COUNT(e) FROM Project p JOIN p.employees e WHERE p.projectId = :projectId")
    int getParticipantCountByProjectId(Long projectId);

    @Query("SELECT COUNT(e) FROM Project p JOIN p.employees e WHERE p.projectName = :projectName")
    int getParticipantCountByProjectName(String projectName);

}
