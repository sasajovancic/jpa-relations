package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity(name = "T_PERSON")
@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {
    //@Id
    //@GeneratedValue
    //private Long id;

    private String name;
    private String nationalNumber;

//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//    public Person withId(Long id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Person withName(String name) { setName(name); return this; }

    public String getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(String nationalNumber) { this.nationalNumber = nationalNumber; }

    public static Person make() { return new Person(); }

    @Override
    public String toString() {
        return "Person{" +
                //"id=" + id +
                ", name='" + name + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                '}';
    }
}
