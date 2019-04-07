package eu.olaf.example.repo.test1n;

import eu.olaf.example.model.test1n.One;
import eu.olaf.example.model.test1n.Two;
import eu.olaf.example.repo.test.CasesTest;
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
public class OneTwoTest {

    private static final Logger LOG = LoggerFactory.getLogger(CasesTest.class);

    @Autowired
    EntityManagerFactory emf;

    public EntityManagerFactory emf() {
        return emf;
    }

    @Test
    public void test() {
        // save ONE_1 with TWO_10 and TWO_11
        doInJPA(this::emf,  entityManager -> {
            One one1 = One.make().withName("ONE_1").addTwo(Two.make().withDesc("TWO_10")).addTwo(Two.make().withDesc("TWO_11"));
            entityManager.persist(one1);
        });

        // save ONE_2 with TWO_20 and TWO_21
        doInJPA(this::emf,  entityManager -> {
            One one2 = One.make().withName("ONE_2").addTwo(Two.make().withDesc("TWO_20")).addTwo(Two.make().withDesc("TWO_21"));
            entityManager.persist(one2);
        });

        // show
        doInJPA(this::emf,  entityManager -> {
            List<One> list = entityManager.createQuery("select one from eu.olaf.example.model.test1n.One as one").getResultList();
            list.stream().forEach(x -> {LOG.info(x.toString());});
        });

        // update ONE_2 and over take TWO_10
        doInJPA(this::emf,  entityManager -> {
            One one2 = One.make().withId(4L).withName("newONE_2").addTwo(Two.make().withId(2L).withDesc("TWO_20"));
            entityManager.merge(one2);
        });

        // show
        doInJPA(this::emf,  entityManager -> {
            List<One> list = entityManager.createQuery("select one from eu.olaf.example.model.test1n.One as one").getResultList();
            list.stream().forEach(x -> {LOG.info(x.toString());});
        });
    }
}
