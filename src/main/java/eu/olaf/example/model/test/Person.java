package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Embeddable;

@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    private String name;
    private String nationalNumber;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Person withName(String name) { setName(name); return this; }

    public String getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(String nationalNumber) { this.nationalNumber = nationalNumber; }

    public static Person make() { return new Person(); }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                '}';
    }
}
