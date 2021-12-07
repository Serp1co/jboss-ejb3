package com.redhat.example;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestService {

  private final Logger logger = Logger.getLogger(RestService.class.getName());

  @EJB
  ILocalEJB localBean;

  @POST
  @Path("/test/{failFirst}/{failSecond}")
  public Response doTransaction(@PathParam("failFirst") boolean failFirst,
                                @PathParam("failSecond") boolean failSecond) {
    logger.info("starting transaction with: " +
        "first_fail=" + failFirst + " " +
        "second_fail=" + failSecond + " ");
//    Model model = new Model();
//    try {
//      try {
//        localBean.doAnotherAction(model, failFirst);
//      } finally {
//        localBean.doTheSecondAction(model, failSecond);
//      }
//    } catch (Exception e) {
//      return Response.ok(e.getMessage()).build();
//    }
    try {
      localBean.doAction(failFirst, failSecond);
      return Response.ok().build();
    } catch (Exception e) {
      return Response.ok(e.getMessage()).build();
    }
  }

}

