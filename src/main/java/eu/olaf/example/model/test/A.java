package eu.olaf.example.model.test;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "T_A")
public class A {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true/*, optional = true*/)
    // todo - add 'unique = true' to fix over taking
    @JoinColumn(name = "b_id", unique = true)
    private B b;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public A withId(Long id) { setId(id); return this; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public A withName(String name) { setName(name); return this; }

    public B getB() { return b; }
    public void setB(B b) { this.b = b; }
    public A withB(B b) { setB(b); return this; }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", b=" + b +
                '}';
    }

    public static A make() { return new A(); }
}
