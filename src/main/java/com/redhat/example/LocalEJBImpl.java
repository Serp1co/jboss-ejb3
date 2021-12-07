package com.redhat.example;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LocalEJBImpl implements ILocalEJB {

  private final Logger logger = Logger.getLogger(LocalEJBImpl.class.getName());

  @PersistenceContext(unitName = "primary", type = PersistenceContextType.TRANSACTION)
  protected EntityManager entityManager;

  @EJB
  // This is used so that this ejb is called from another context
  // to ensure the creation of a new transaction context
  private ILocalEJB self;

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void doAction(boolean failFirst, boolean failSecond) throws Exception {
    logger.info("Starting container transaction");
    String randomId = UUID.randomUUID().toString();
    try {
      Model model = new Model();
      self.doAnotherAction(model, randomId, failFirst);
    } finally {
      SecondModel model = new SecondModel();
      self.doTheSecondAction(model, randomId, failSecond);
    }
    logger.info("End container transaction");
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void doAnotherAction(Model model, String randomId, boolean fail) throws RollBackException {
    logger.info("Starting first transaction");
    model.setRandom(randomId);
    entityManager.persist(model);
    logger.info("Persisted entity the first time: " + model);
    if (fail) {
      logger.log(Level.SEVERE, "Exception on first transaction");
      throw new RollBackException("failing first commit");
    }
    logger.info("End first transaction");
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public void doTheSecondAction(SecondModel model, String randomId, boolean fail) throws RollBackException {
    logger.info("Starting second transaction");
    model.setValue(randomId);
    entityManager.persist(model);
    logger.info("Persisted entity the second time: " + model);
    if (fail) {
      logger.log(Level.SEVERE, "Exception on second transaction");
      throw new RollBackException("failing second commit");
    }
    logger.info("End second transaction");
  }


}