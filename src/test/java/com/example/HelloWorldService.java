package com.example;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.example.HelloWorldProjo;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/v1")
@RegisterRestClient
public interface HelloWorldService{

    @POST
    @Path("/helloworld")
    Response callHeloWorld(HelloWorldProjo payloadToSend);
}