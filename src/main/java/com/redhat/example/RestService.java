package com.redhat.example;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestService {

  private final Logger logger = Logger.getLogger(RestService.class.getName());

  @Inject
  ILocalEJB localBean;

  @POST
  @Path("/test/{failFirst}/{failSecond}")
  public Response doTransaction(@PathParam("failFirst") boolean failFirst,
                                @PathParam("failSecond") boolean failSecond) {
    logger.info("starting transaction with: " +
        "first_fail=" + failFirst + " " +
        "second_fail=" + failSecond + " ");
    try {
      return Response.ok(localBean.doAction(failFirst, failSecond)).build();
    } catch (Exception e) {
      return Response.accepted(e).build();
    }
  }

}