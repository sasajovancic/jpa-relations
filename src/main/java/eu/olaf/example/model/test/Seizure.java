package eu.olaf.example.model.test;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
public class Seizure {

	private String desc;

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }
    public Seizure withDesc(String desc) { setDesc(desc); return this; }

    @Override
    public String toString() {
        return "Seizure{" +
                "desc='" + desc + '\'' +
                '}';
    }

    public static Seizure make() { return new Seizure(); }
}
