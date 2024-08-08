package com.plee.demorest;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("aliens")
public class AlienResource {
    AlienRepository repo = new AlienRepository();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Alien> getAliens() {
        System.out.println("getAliens called...");

        return repo.getAliens();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Alien getAlien(@PathParam("id") int id) {
        String message = String.format("getAlien with id %d called...", id);
        System.out.println(message);

        return repo.getAlien(id);
    }

}
