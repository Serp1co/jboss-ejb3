import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TransactionService {

  @Resource
  private SessionContext sessionContext;

  private ILocalBean bean;

  @PostConstruct
  public void init(){
    this.bean = sessionContext.getBusinessObject(LocalBeanImpl.class);
  }

  public void executeTransaction(){
    bean.doAction();
  }

}
