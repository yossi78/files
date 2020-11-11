package com.example.files.schedule;


import com.example.files.dto.Person;
import com.example.files.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@EnableScheduling
public class PersonScheduler {

    @Value("${personSvc.personScheduler.files}")
    private List<String> files;


    private Logger logger = LoggerFactory.getLogger(PersonScheduler.class);
    private PersonService personService;


    @Autowired
    public PersonScheduler(PersonService personService) {
        this.personService = personService;
    }


    @Scheduled(fixedDelayString = "${personSvc.personScheduler.delay:20000}")
    public void fetchPersonsFromExternal() {
        try {
            List<Person> personsFromFiles = personService.fetchPersonsFromFiles(files);
            personService.addPersons(personsFromFiles);
        } catch (Exception e) {
            logger.error("Failed to fetch Persons from external reasources",e);
        }

    }




}
