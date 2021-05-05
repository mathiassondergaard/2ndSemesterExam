package com.alexnmat.exam.service;

import com.alexnmat.exam.models.Effort;
import com.alexnmat.exam.models.Project;
import com.alexnmat.exam.repositories.EffortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class EffortService {

    private EffortRepository effortRepository;

    @Autowired
    public EffortService(EffortRepository effortRepository) {
        this.effortRepository = effortRepository;
    }

    public List<Effort> findAllEffortsForProject(long projectId) {
        if (effortRepository.findByProjectId(projectId).isEmpty()) {
            throw new NoResultException("No efforts available in database");
        }
        else {
            return effortRepository.findByProjectId(projectId);
        }
    }

    public Effort createEffort(Effort effort, Project project) {
        effort.setProject(project);
        return effortRepository.save(effort);
    }

    public void deleteEffort(long effortId) {
        Effort effort = effortRepository.findById(effortId).orElseThrow(() -> new NoResultException("Unable to find effort by id: " + effortId));
        effortRepository.delete(effort);
    }
}
