package eu.olaf.example.model.test;

import java.io.Serializable;
import java.util.Objects;


public class CompositeId implements Serializable {


    private Long id;
    private Long cas;

    public CompositeId() {}
    public CompositeId(Long id, Long caseId) {
        this.id = id;
        this.cas = caseId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCas() { return cas; }
    public void setCas(Long caseId) { this.cas = caseId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeId that = (CompositeId) o;
        return Objects.equals(id, that.id) && Objects.equals(cas, that.cas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cas);
    }

    @Override
    public String toString() {
        return "CompositeId{" +
                "id=" + id +
                ", cas=" + cas +
                '}';
    }
}
