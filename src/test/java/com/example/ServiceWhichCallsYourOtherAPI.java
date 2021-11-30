package com.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ServiceWhichCallsYourOtherAPI{


    @Inject
    @RestClient
    HelloWorldService helloWorldService;



    public void methodA(){

        HelloWorldProjo payloadToSend = new HelloWorldProjo();

        payloadToSend.setId(123);
        payloadToSend.setName("whee");

        helloWorldService.callHeloWorld(payloadToSend);

    }

}
