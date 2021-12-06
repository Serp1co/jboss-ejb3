import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Local
public interface ILocalBean {

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  void doAction();

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  Model doAnotherAction();

  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  void doTheSecondAction(Model model);

}
