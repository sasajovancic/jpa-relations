package eu.olaf.example.repo.test;

import eu.olaf.example.model.test.Case;
import eu.olaf.example.model.test.Seizure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CasesTest {

    private static final Logger LOG = LoggerFactory.getLogger(CasesTest.class);

    @Autowired
    EntityManagerFactory emf;

    public EntityManagerFactory emf() {
        return emf;
    }


    @Before
    public void init(){
        // save A1 with B1
        doInJPA(this::emf,  entityManager -> {
            Case case1 = Case.make().withName("A1").withSeizure(Seizure.make().withDesc("B1"));
            entityManager.persist(case1);
        });

        // save A2 with B2
        doInJPA(this::emf,  entityManager -> {
            Case case2 = Case.make().withName("A2").withSeizure(Seizure.make().withDesc("B2"));
            entityManager.persist(case2);
        });

        // show
        doInJPA(this::emf,  entityManager -> {
            List<Case> list = entityManager.createQuery("select a from eu.olaf.example.model.test.Case as a").getResultList();
            list.stream().forEach(aCase -> {LOG.info(aCase.toString());});
        });
    }


    @Test//(expected = javax.persistence.PersistenceException.class)
    public void test_1() {


        // over take
        // update A2 with B1
        doInJPA(this::emf,  entityManager -> {
            Case case2 = Case.make().withId(3L).withName("newA2").withSeizure(Seizure.make().withId(2L));

            entityManager.merge(case2);
            entityManager.flush();
        });

        // show
        doInJPA(this::emf,  entityManager -> {
            List<Case> list = entityManager.createQuery("select a from eu.olaf.example.model.test.Case as a").getResultList();
            list.stream().forEach(aCase -> {LOG.info(aCase.toString());});
        });

        // or create A3 with B1
    }
}
