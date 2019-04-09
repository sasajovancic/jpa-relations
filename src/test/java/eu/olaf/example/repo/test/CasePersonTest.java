package eu.olaf.example.repo.test;

import eu.olaf.example.model.test.Case;
import eu.olaf.example.model.test.Person;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.hibernate.testing.transaction.TransactionUtil.doInHibernate;
import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CasePersonTest {

    private static final Logger LOG = LoggerFactory.getLogger(CasesTest.class);

    @Autowired
    EntityManagerFactory emf;

    public EntityManagerFactory emf() {
        return emf;
    }

    public SessionFactory sf() { return emf.unwrap(SessionFactory.class); }

    @Test
    public void test() {
        // save ONE_1 with TWO_10 and TWO_11
        doInJPA(this::emf,  entityManager -> {
            Case cas1 = Case.make().withName("CASE_1").addPerson(Person.make().withName("PERSON_10")).addPerson(Person.make().withName("PERSON_11"));
            entityManager.persist(cas1);
        });
        doInHibernate(this::sf, session -> {
            Case cas2 = Case.make().withName("CASE_2").addPerson(Person.make().withName("PERSON_20")).addPerson(Person.make().withName("PERSON_21"));
            session.save(cas2);
        });
        doInHibernate(this::sf, session -> {
            List<Case> list = session.getEntityManagerFactory().createEntityManager().createQuery("select a from eu.olaf.example.model.test.Case as a").getResultList();
            list.stream().forEach(aCase -> {LOG.info(aCase.toString());});
        });
        // overtake
        doInHibernate(this::sf, session -> {
            Case cas2 = Case.make().withId(2L).withName("CASE_2_NEW").addPerson(Person.make()/*.withId(2L)*/.withName("PERSON_20_NEW"));
            session.saveOrUpdate(cas2);
        });
        doInHibernate(this::sf, session -> {
            List<Case> list = session.getEntityManagerFactory().createEntityManager().createQuery("select a from eu.olaf.example.model.test.Case as a").getResultList();
            list.stream().forEach(aCase -> {LOG.info(aCase.toString());});
        });
    }
}
