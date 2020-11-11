package com.example.files.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;




@Data
public class Person {

    private Long id;

    @JsonProperty("firstName")
    @CsvBindByName(column = "Fname")
    private String firstName;


    @JsonProperty("lastName")
    @CsvBindByName(column = "lname")
    private String lastName;


    @JsonProperty("age")
    @CsvBindByName(column = "howOld")
    private Integer age;


    public Person() {
    }

    public Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(Long id, String firstName, String lastName, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setAll(Person person){
        if(person.getId()!=null) setId(person.getId());
        if(person.getAge()!=null) setAge(person.getAge());
        if(person.getFirstName()!=null) setFirstName(person.getFirstName());
        if(person.getLastName()!=null) setLastName(person.getLastName());
    }




}

