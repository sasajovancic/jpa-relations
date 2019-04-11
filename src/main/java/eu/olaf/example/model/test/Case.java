package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "T_EX_CASE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    @JoinColumn(name = "b_id", unique = true)
    private Seizure seizure;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cas", orphanRemoval = true)
    private List<Person> persons;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Case withId(Long id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Case withName(String name) { setName(name); return this; }

    public Seizure getSeizure() { return seizure; }
    public void setSeizure(Seizure seizure) { this.seizure = seizure; }
    public Case withSeizure(Seizure seizure) { setSeizure(seizure); return this; }

    public List<Person> getPersons() { return persons; }
    public void setPersons(List<Person> persons) { this.persons = persons; }
    public Case addPerson(Person person) {
        if (persons == null) {
            persons = new ArrayList<>();
        }
        persons.add(person);
        person.setCas(this);

        return this;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seizure=" + seizure +
                ", persons=" + persons +
                '}';
    }

    public static Case make() { return new Case(); }
}
