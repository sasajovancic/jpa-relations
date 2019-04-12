package eu.olaf.example.model.test;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity(name = "T_EX_PERSON")
public class Person {

    @EmbeddedId
    @GenericGenerator(name = "sequence_comp_id", strategy = "eu.olaf.example.model.test.CompositeIdGenerator")
    @GeneratedValue(generator = "sequence_comp_id")
    private CompositeId compositeId;

    private String name;
    private String nationalNumber;

    public Person() {
    }

    public CompositeId getCompositeId() { return compositeId; }
    public void setCompositeId(CompositeId compositeId) { this.compositeId = compositeId; }
    public Person withCompositeId(CompositeId compositeId) { setCompositeId(compositeId); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Person withName(String name) { setName(name); return this; }

    public String getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(String nationalNumber) { this.nationalNumber = nationalNumber; }

    public static Person make() { return new Person(); }

    @Override
    public String toString() {
        return "Person{" +
                // "id=" + id +
                "compositeId=" + compositeId +
                ", name='" + name + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                '}';
    }
}
