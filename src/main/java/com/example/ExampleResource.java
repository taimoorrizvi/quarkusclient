package com.example;

import ecommerce.ProductInfoGrpc;
import ecommerce.ProductInfoOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
@Path("/hello")
public class ExampleResource {

    @GET
    @Path("/hello1")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Path("/helloworld")
    public String testPostCall(HelloWorldProjo payloadToSend){

        return getgRPC(payloadToSend.getName(),payloadToSend.getDescription(),payloadToSend.getPrice());
    }

    public  String getgRPC(String name, String description, String price ) {

        final Logger logger = Logger.getLogger(ExampleResource.class.getName());
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ProductInfoGrpc.ProductInfoBlockingStub stub =
                ProductInfoGrpc.newBlockingStub(channel);

        ProductInfoOuterClass.ProductID productID = stub.addProduct(
                ProductInfoOuterClass.Product.newBuilder()
                        .setName(name)
                        .setDescription(description)
                        .setPrice(Float.parseFloat(price))
                        //.setPrice(700.0f)
                        // .setName("ReadMe Node10")
                        //.setDescription("ReadMe Node10 is the latest smart phone, " +
                        //       "launched in February 2019")
                        //.setPrice(700.0f)
                        .build());
        logger.info("Product ID: " + productID.getValue() + " added successfully.");

        ProductInfoOuterClass.Product product = stub.getProduct(productID);
        logger.info("Product: " + product.toString());
        channel.shutdown();


        return String.format("Product:  %s!", product.toString());
    }

}