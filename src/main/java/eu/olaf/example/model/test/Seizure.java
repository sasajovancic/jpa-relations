package eu.olaf.example.model.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(name = "T_SEIZURE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seizure {
    @Id
    @GeneratedValue
    private Long id;

    // @NotNull
    private String desc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Seizure withId(Long id) { setId(id); return this; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public Seizure withDesc(String desc) { setDesc(desc); return this; }

    @Override
    public String toString() {
        return "Seizure{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static Seizure make() { return new Seizure(); }
}
