package com.example.files.services;


import com.example.files.dto.Person;
import com.example.files.utils.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




@Service
public class PersonService {


    private List<Person> list ;
    private Long id=0L;

    public PersonService(){
        list=new ArrayList<>();
    }



    public Person findPerson(Long id) {
        for(Person current:list){
            if(current.getId().equals(id)){
                return current;
            }
        }
        return null;
    }


    public void addPerson(Person person) {
        list.add(person);
    }



    public void addPersons(List<Person> personList) {
        list.addAll(personList);
    }


    public Boolean updatePerson(Person updatedPerson,Long id) {
        Person person = findPerson(id);
        if(person==null){
            return false;
        }
        person.setAll(updatedPerson);
        return true;
    }

    public Boolean removePerson(Long id) {
        return list.remove(id);
    }


    public List<Person> getAllPersons() {
        return list;
    }



    public List<Person> fetchPersonsFromFiles(List<String> filePathes) throws Exception {
        List<Person> fetchList = new ArrayList<>();
        for(String current:filePathes){
            List<Person> tempPersons = FileUtil.fileToListOfObjects(current,Person.class);
            injectId(tempPersons);
            fetchList.addAll(tempPersons);
        }
        return fetchList;
    }




    public Object parseStringToObject(String str,Class T) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object result = mapper.readValue(str,T);
        return result;
    }


    private void injectId(List<Person> list){
        list.stream().forEach(c->c.setId(++id));
    }


}



