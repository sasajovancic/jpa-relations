package eu.olaf.example.repo.test;

import eu.olaf.example.model.test.A;
import eu.olaf.example.model.test.B;
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
            A a1 = A.make().withName("A1").withB(B.make().withDesc("B1"));
            entityManager.persist(a1);
        });

        // save A2 with B2
        doInJPA(this::emf,  entityManager -> {
            A a2 = A.make().withName("A2").withB(B.make().withDesc("B2"));
            entityManager.persist(a2);
        });

        // show
        doInJPA(this::emf,  entityManager -> {
            List<A> list = entityManager.createQuery("select a from eu.olaf.example.model.test.A as a").getResultList();
            list.stream().forEach(a -> {LOG.info(a.toString());});
        });
    }


    @Test(expected = javax.persistence.PersistenceException.class)
    public void test_1() {


        // over take
        // update A2 with B1
                    doInJPA(this::emf,  entityManager -> {
                          A a2 = A.make().withId(3L).withName("newA2").withB(B.make().withId(2L));

                          entityManager.merge(a2);
                          entityManager.flush();
                    });

        // show
        doInJPA(this::emf,  entityManager -> {
            List<A> list = entityManager.createQuery("select a from eu.olaf.example.model.test.A as a").getResultList();
            list.stream().forEach(a -> {LOG.info(a.toString());});
        });

        // or create A3 with B1
    }
}
