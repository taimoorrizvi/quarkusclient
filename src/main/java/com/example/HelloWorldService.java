package com.example;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1")
@RegisterRestClient
public interface HelloWorldService {

    @POST
    @Path("/helloworld")
    Response callHeloWorld(HelloWorldProjo payloadToSend);
}