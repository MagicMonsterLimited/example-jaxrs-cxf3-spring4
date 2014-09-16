package com.magicmonster.example.jaxsrscxf3spring4;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * a collection of fruits
 * Test this using curl.
 */
// in beans.xml there is a "/cxf" prefix, all URLs for the services will be at "/cxf/fruit"
@Path("/fruit")
public class FruitService {
    private Map<String, Integer> fruitBasket = new ConcurrentHashMap<String, Integer>();

    // you can test this using curl from the command line.
    // curl http://localhost/cxf/fruit/
    @GET()
    @Produces({"application/json"})
    public Map<String, Integer> getAllFruits() {
        return fruitBasket;
    }

    // you can test this using curl from the command line.
    // curl http://localhost/cxf/fruit/pineapple
    @GET()
    @Path("/{fruit}")
    @Produces({"application/json"})
    public Integer getFruitCount(@PathParam("fruit") String fruit) {
        Integer integer = fruitBasket.get(fruit);
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    // you can test this using curl from the command line.
    // curl -X PUT http://localhost/cxf/fruit/pineapple/3
    @PUT()
    @Path("/{fruitName}/{count}")
    public void addFruit(@PathParam("fruitName") String fruit, @PathParam("count") String count) {
        fruitBasket.put(fruit, Integer.parseInt(count));
    }
}
