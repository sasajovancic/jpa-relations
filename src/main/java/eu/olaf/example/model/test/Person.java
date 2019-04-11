package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;


@Entity(name = "T_EX_PERSON")
@IdClass(CompositeId.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id = 0L;

    @Id
    @ManyToOne
    @JsonIgnore
    private Case cas;

    private String name;
    private String nationalNumber;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Person withId(Long id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Person withName(String name) { setName(name); return this; }

    public String getNationalNumber() { return nationalNumber; }
    public void setNationalNumber(String nationalNumber) { this.nationalNumber = nationalNumber; }

    public Case getCas() {
        return cas;
    }
    public void setCas(Case cas) {
        this.cas = cas;
    }

    public static Person make() { return new Person(); }

    @Override
    public String toString() {
        return "Person{" +
                 "id=" + id +
                ", name='" + name + '\'' +
                ", nationalNumber='" + nationalNumber + '\'' +
                '}';
    }
}
