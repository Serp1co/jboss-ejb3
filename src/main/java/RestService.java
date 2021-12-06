import jakarta.inject.Inject;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

public class RestService {

  @Inject
  TransactionService transactionService;

  @POST
  @Path("/test")
  public void doTransaction(){
    transactionService.executeTransaction();
  }

}