package eu.olaf.example.repo;

import eu.olaf.example.model.Case;
import org.hibernate.testing.transaction.TransactionUtil;
import org.hibernate.testing.transaction.TransactionUtil2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseRepositoryHibernateJpaTest {

    @Autowired
    EntityManagerFactory emf;

    public EntityManagerFactory emf() {
        return emf;
    }

    @Test
    public void test_save() {

        doInJPA(this::emf,  entityManager -> {
            Case cas = new Case();
            cas.setRefNumber("123");
            entityManager.persist(cas);
        });

        // TransactionUtil.doInHibernate()
        // TransactionUtil2.inTransaction();
    }
}
