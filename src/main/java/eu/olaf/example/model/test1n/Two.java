package eu.olaf.example.model.test1n;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity(name = "T_TWO")
public class Two {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String desc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Two withId(Long id) { setId(id); return this; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public Two withDesc(String desc) { setDesc(desc); return this; }

    @Override
    public String toString() {
        return "Two{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static Two make() { return new Two(); }
}
