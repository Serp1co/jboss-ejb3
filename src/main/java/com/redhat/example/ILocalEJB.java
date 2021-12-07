package com.redhat.example;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface ILocalEJB {

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  Model doAction(boolean failFirst, boolean failSecond) throws Exception;

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  Model doAnotherAction(Model model, boolean fail) throws Exception;

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  Model doTheSecondAction(Model model, boolean fail) throws Exception;

}
