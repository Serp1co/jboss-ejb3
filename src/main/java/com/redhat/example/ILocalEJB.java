package com.redhat.example;

import javax.ejb.ApplicationException;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface ILocalEJB {

  void doAction(boolean failFirst, boolean failSecond) throws Exception;

  void doAnotherAction(Model model, String randomId, boolean fail) throws RollBackException;

  void doTheSecondAction(SecondModel model, String randomId, boolean fail) throws RollBackException;

  @ApplicationException(rollback = true)
  class RollBackException extends Exception {
    RollBackException(String msg) {
      super(msg);
    }
  }

}
