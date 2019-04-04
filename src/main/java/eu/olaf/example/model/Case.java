package eu.olaf.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity(name = "T_CASE")
public class Case implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "cas.refNumber")
    private String refNumber;
    private String status;
    private String title;

    private Date created;
    private Date updated;

//    // 1-1
//    @OneToOne
//    private Person owner;
//
//    // 1-N
//    @OneToMany
//    private List<Person> personsInvolve;
//
//    // 1-1
//    @OneToOne
//    private Train train;
//
//    // 1-1
//    @OneToOne
//    private Vessel vessel;


    /// prop
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Case withId(Long id) { setId(id); return this; }

    public String getRefNumber() { return refNumber; }
    public void setRefNumber(String refNumber) { this.refNumber = refNumber; }
    public Case withRefNumber(String refNumber) { setRefNumber(refNumber); return this; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    public Date getUpdated() { return updated; }
    public void setUpdated(Date updated) { this.updated = updated; }

//    public Person getOwner() { return owner; }
//    public void setOwner(Person owner) { this.owner = owner; }
//
//    public List<Person> getPersonsInvolve() { return personsInvolve; }
//    public void setPersonsInvolve(List<Person> personsInvolve) { this.personsInvolve = personsInvolve; }
//
//    public Train getTrain() { return train; }
//    public void setTrain(Train train) { this.train = train; }
//
//    public Vessel getVessel() { return vessel; }
//    public void setVessel(Vessel vessel) { this.vessel = vessel; }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", refNumber='" + refNumber + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", updated=" + updated +
//                ", owner=" + owner +
//                ", personsInvolve=" + personsInvolve +
//                ", train=" + train +
//                ", vessel=" + vessel +
                '}';
    }

    public static Case make() { return new Case(); }
}
