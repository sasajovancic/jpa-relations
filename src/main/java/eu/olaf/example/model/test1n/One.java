package eu.olaf.example.model.test1n;

import eu.olaf.example.model.test.A;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "T_ONE")
public class One {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // TODO remove column
    @JoinColumn(name = "ONE_ID", referencedColumnName = "ID")
    List<Two> twos;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public One withId(Long id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public One withName(String name) { setName(name); return this; }

    public List<Two> getTwos() { return twos; }
    public void setTwos(List<Two> twos) { this.twos = twos; }
    public One addTwo(Two two) {
        if (twos == null) {
            twos = new ArrayList<>();
        }
        twos.add(two);
        return this;
    }

    @Override
    public String toString() {
        return "One{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", twos=" + twos +
                '}';
    }

    public static One make() { return new One(); }
}
