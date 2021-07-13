package org.acme;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/my-entities")
public class MyEntityResource {

    @POST
    @Transactional
    public MyEntity create() {
        MyEntity entity = new MyEntity();
        entity.persist();
        return entity;
    }
}