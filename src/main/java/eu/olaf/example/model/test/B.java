package eu.olaf.example.model.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name = "T_B")
public class B {
    @Id
    @GeneratedValue
    private Long id;

    // @NotNull
    private String desc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public B withId(Long id) { setId(id); return this; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public B withDesc(String desc) { setDesc(desc); return this; }

    @Override
    public String toString() {
        return "B{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static B make() { return new B(); }
}
