import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.UUID;

public class LocalBeanImpl implements ILocalBean {

  @PersistenceContext(type = PersistenceContextType.TRANSACTION)
  protected EntityManager entityManager;

  @Override
  public void doAction() {
    doTheSecondAction(doAnotherAction());
  }

  @Override
  public Model doAnotherAction() {
    Model model  = new Model();
    model.setRandom(UUID.randomUUID().toString());
    entityManager.persist(model);
    return model;
  }

  @Override
  public void doTheSecondAction(Model model) {
    model.setUpdated("TRUE");
    entityManager.persist(model);
  }

}
