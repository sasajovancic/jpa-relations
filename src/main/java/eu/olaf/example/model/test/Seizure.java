package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity(name = "T_SEIZURE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seizure {
    @Id
    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId
    @JsonBackReference
    private Case cas;

    private String desc;

    public Long getId() { return cas ==null ? null : cas.getId(); }
    // public void setId(Long id) { this.id = id; }
    // public Seizure withId(Long id) { setId(id); return this; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public Seizure withDesc(String desc) { setDesc(desc); return this; }

    public Case getCas() {
        return cas;
    }
    public void setCas(Case cas) {
        this.cas = cas;
        this.id = cas.getId();
    }

    @Override
    public String toString() {
        return "Seizure{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static Seizure make() { return new Seizure(); }
}
