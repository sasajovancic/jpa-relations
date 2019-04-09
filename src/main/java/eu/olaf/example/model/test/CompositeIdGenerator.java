package eu.olaf.example.model.test;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class CompositeIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Long lId = (Long)((UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) / 10000000000000L);

        Person p;
        CompositeId compId;
        if (o == null || !(o instanceof Person)) {
            compId = new CompositeId();
            compId.setId(lId);
            return compId;
        }

        if (o != null && o instanceof Person && ((Person)o).getCompositeId() == null) {
            compId = new CompositeId();
            compId.setId(lId);
            return compId;
        } else {
            compId = ((Person)o).getCompositeId();
            if (compId.getId() == null) {
                compId.setId(lId);
            }
            return compId;
        }
    }
}
