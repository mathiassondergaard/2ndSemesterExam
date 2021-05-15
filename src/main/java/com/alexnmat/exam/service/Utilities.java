package com.alexnmat.exam.service;

import com.alexnmat.exam.models.entities.Person;
import com.alexnmat.exam.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Utilities {

    @Autowired
    private UserRepository userRepository;

    public Person getCurrentLoggedInPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLoggedInUsersUsername = authentication.getName();
        return userRepository.findByUsername(currentLoggedInUsersUsername).getPerson();
    }

    public boolean dateChecker(LocalDate startDate, LocalDate endDate) {
        return endDate.isBefore(startDate);
    }

    /*
    HACKER SNIPPET
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLoggedInUsersUsername = authentication.getName();
        List<Person> personList = userRepository.findByUsername(currentLoggedInUsersUsername).getPersons();
        long userId = userRepository.findByUsername(currentLoggedInUsersUsername).getId();
        Person person = new Person();
        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getId() == userId) {
                person.setId(personList.get(i).getId());
                person.setName(personList.get(i).getName());
                person.setLastName(personList.get(i).getLastName());
                person.setCompetence(personList.get(i).getCompetence());
            }
            else {
                throw new NoResultException("No person with id" + userId + "exists in database");
            }
        }
        return person;
     */

    //from STACKO (find link)
    public long calculateTotalWorkdayHours(LocalDate startDate, LocalDate endDate) {
        //Get startDay's value and endDay's value
        final int startDay = startDate.getDayOfWeek().getValue();
        final int endDay = endDate.getDayOfWeek().getValue();

        //Get days between start and end date
        final long days = ChronoUnit.DAYS.between(startDate, endDate);
        //Removes weekends from days
        long result = days - 2*(days/7);

        //Deal with the rest of the days
        if (days % 7 != 0) {
            if (startDay == 7) {
                result -= 1;
            } else if (endDay == 7) { //both days cant be sunday, would mean result == 0
                result -= 1;
            } else if (endDay < startDay) { //another weekend included.
                result -= 2;
            }
        }
        //One workday equals 8 hours
        int workdayHours = 8;

        return result * workdayHours;
    }
}
