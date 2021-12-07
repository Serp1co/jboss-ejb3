package com.redhat.example;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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

  public Model doAction(boolean failFirst, boolean failSecond) throws Exception {
    logger.info("Starting container transaction");
    Model model = new Model();
    try {
      model = doAnotherAction(model, failFirst);
    } finally{
      model = doTheSecondAction(model, failSecond);
    }
    logger.info("End container transaction, final model: " + model.toString());
    return model;
  }

  public Model doAnotherAction(Model model, boolean fail) throws Exception {
    logger.info("Starting first transaction");
    if (!fail) {
      model.setRandom(UUID.randomUUID().toString());
      entityManager.persist(model);
    } else {
      logger.log(Level.SEVERE, "Exception on first transaction");
      throw new Exception("failing first commit");
    }
    logger.info("End first transaction");
    return model;
  }

  public Model doTheSecondAction(Model model, boolean fail) throws Exception {
    logger.info("Starting second transaction");
    if (!fail) {
      model.setUpdated("TRUE");
      entityManager.persist(model);
    } else {
      logger.log(Level.SEVERE, "Exception on second transaction");
      throw new Exception("failing second commit");
    }
    logger.info("End first transaction");
    return model;
  }

}
