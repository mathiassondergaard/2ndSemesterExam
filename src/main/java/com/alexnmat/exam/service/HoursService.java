package com.alexnmat.exam.service;

import com.alexnmat.exam.models.entities.Hours;
import com.alexnmat.exam.models.entities.Person;
import com.alexnmat.exam.models.entities.Project;
import com.alexnmat.exam.repositories.HoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HoursService extends Utilities {

    private HoursRepository hoursRepository;

    @Autowired
    public HoursService(HoursRepository hoursRepository) {
        this.hoursRepository = hoursRepository;
    }

    public List<Hours> getHoursForPerson(Person person) {
        if (hoursRepository.findByPerson(person).isEmpty()) {
            throw new NoResultException("No efforts for person available in database");
        } else {
            return hoursRepository.findByPerson(person);
        }
    }

    public List<Hours> getHoursForPersonAndProject(Person person, Project project) {
        if (hoursRepository.findByProjectAndPerson(project, person).isEmpty()) {
            throw new NoResultException("No efforts for person and project available in database");
        } else {
            return hoursRepository.findByProjectAndPerson(project, person);
        }
    }

    public List<Hours> getHoursForPersonAndProject(Project project) {
        if (hoursRepository.findByProject(project).isEmpty()) {
            throw new NoResultException("No efforts for project available in database");
        } else {
            return hoursRepository.findByProject(project);
        }
    }

    public Hours addHours(Hours hours, Project project) {
        hours.setPerson(getCurrentLoggedInPerson());
        hours.setProject(project);
        return hoursRepository.save(hours);
    }

    public void deleteHours(long hoursId) {
        Hours hours = hoursRepository.findById(hoursId).orElseThrow(() -> new NoResultException("Unable to find hours by id: " + hoursId));
        hoursRepository.delete(hours);
    }

}
