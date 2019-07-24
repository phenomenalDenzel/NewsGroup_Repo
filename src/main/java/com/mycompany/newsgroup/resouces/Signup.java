/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup.resouces;

import com.mycompany.newsgroup.entities.UserProfile;
import com.mycompany.newsgroup.services.SignupService;
import com.mycompany.newsgroup.utils.Link;
import com.mycompany.newsgroup.utils.Message;
import java.net.URI;
import javax.ejb.Stateless;
//import javax.ejb.Stateful;
import javax.inject.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author denzel
 */
@Path("signup")
@Stateless
public class Signup {
    @Inject
    SignupService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserAccount(UserProfile userProfile,@Context UriInfo uriInfo){
      URI uri= uriInfo.getAbsolutePathBuilder().build();
        Message message=service.addUserAccount(userProfile);
     
     
        message.setLink(new Link("newUser","/userprofile/api/"+message.getId()));
        return Response.created(uri).entity(message).build();
   
    }
    
    @Path("/{email}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUserAccount(@PathParam("email") String email){
        service.deleteUserAccount(email);
    }
}
