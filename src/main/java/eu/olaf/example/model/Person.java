package eu.olaf.example.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String nationalIdReg;
    private String firstName;
    private String lastName;

    private Date created;
    private Date updated;
}
