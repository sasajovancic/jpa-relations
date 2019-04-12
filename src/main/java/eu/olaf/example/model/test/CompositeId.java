package eu.olaf.example.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeId implements Serializable {

    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASE_ID", referencedColumnName = "ID")
    @JsonIgnore
    private Case cas;

    public CompositeId() {}
    public CompositeId(Long id, Case cas) {
        this.id = id;
        this.cas = cas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Case getCas() { return cas; }
    public void setCas(Case cas) { this.cas = cas; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeId that = (CompositeId) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cas, that.cas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cas);
    }

    @Override
    public String toString() {
        return "CompositeId{" +
                "id=" + id +
                '}';
    }
}
