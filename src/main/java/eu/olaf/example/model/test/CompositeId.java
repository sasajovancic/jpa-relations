package eu.olaf.example.model.test;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeId implements Serializable {

    @Column(name = "id", unique = true)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(name = "CASE_ID", insertable = false, updatable = false)
//    private Long caseId;

    @ManyToOne
    @JoinColumn(name = "CASE_ID", referencedColumnName = "ID")
    private Case cas;


    public CompositeId() {}
    public CompositeId(Long id, Case cas) {
        this.id = id;
        this.cas = cas;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

//    public Long getCaseId() { return caseId; }
//    public void setCaseId(Long caseId) { this.caseId = caseId; }


    public Case getCas() { return cas; }
    public void setCas(Case cas) { this.cas = cas; }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CompositeId that = (CompositeId) o;
//        return Objects.equals(id, that.id) && Objects.equals(caseId, that.caseId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, caseId);
//    }


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
                //", caseId=" + caseId +
                '}';
    }
}
