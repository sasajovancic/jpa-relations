package eu.olaf.example.repo.test1n.composite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CompositeTest {
    private static final Logger LOG = LoggerFactory.getLogger(CompositeTest.class);

    @Autowired
    EntityManagerFactory emf;

    public EntityManagerFactory emf() {
        return emf;
    }

    @Test
    public void test() {
        // Company cc = new Company();
        // save COMPANY_1 with USER_10 and USER_11
        doInJPA(this::emf, entityManager -> {
            //Company comp1 = Company.make().withName("ONE_1").addTwo(Two.make().withDesc("TWO_10")).addTwo(Two.make().withDesc("TWO_11"));
            //entityManager.persist(one1);
        });
    }
}
