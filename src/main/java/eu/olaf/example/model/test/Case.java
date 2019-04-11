package eu.olaf.example.model.test;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "T_CASE")
public class Case {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private Seizure seizure;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Case withId(Long id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Case withName(String name) { setName(name); return this; }

    public Seizure getSeizure() { return seizure; }
    public void setSeizure(Seizure seizure) { this.seizure = seizure; }
    public Case withSeizure(Seizure seizure) { setSeizure(seizure); return this; }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seizure=" + seizure +
                '}';
    }

    public static Case make() { return new Case(); }
}
