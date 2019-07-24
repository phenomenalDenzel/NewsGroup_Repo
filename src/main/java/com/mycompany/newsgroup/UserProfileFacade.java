/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup;

import com.mycompany.newsgroup.entities.UserProfile;
import com.mycompany.newsgroup.utils.Link;
import com.mycompany.newsgroup.utils.Message;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
@Stateless
@Path("member")
public class UserProfileFacade extends AbstractFacade<UserProfile> {

    @PersistenceContext(unitName = "com.mycompany_newsGroup_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    NewsFacade news;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserProfileFacade() {
        super(UserProfile.class);
    }

    @GET
    @Path("/{memberId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMember(@PathParam("memberId") int id, @Context UriInfo uriInfo) {
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        String baseUri = uri.toString();
        UserProfile user;
//        EntityManager em=emf.createEntityManager();
        user = find(id);

        System.out.println("prints--------> " + user.getEmail());
        user.getLinks().add(new Link("self", baseUri));
        user.getLinks().add(new Link("news", baseUri + "/news"));

        return Response.ok().entity(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserProfile> getAllMembers() {
        TypedQuery query = em.createQuery("select u from UserProfile u", UserProfile.class);
        List<UserProfile> members = query.getResultList();

        return members;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(UserProfile user) {
        if (user.getId() > 0 || user.getEmail() != null) {
            TypedQuery<UserProfile> query = em.createQuery("select u from UserProfile u where u.id=:id or u.email=:email", UserProfile.class);
            query.setParameter("id", user.getId());
            query.setParameter("email", user.getEmail());
            UserProfile returnValue = query.getSingleResult();
            returnValue.setFirstname(user.getFirstname());
            returnValue.setLastname(user.getLastname());
            Message message = new Message(200, "UserProfile update", returnValue.getId().toString());
            return Response.noContent().entity(message).build();
        }
        return null;

    }

    @DELETE
    @Path("/{memberId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteMember(@PathParam("memberId") int id) {
       UserProfile user=em.find(UserProfile.class, id);
        em.remove(user);
    }
    public UserProfile getUserByEmail(String email){
        TypedQuery<UserProfile> query=em.createQuery("select u from UserProfile u where u.email=:email",UserProfile.class);
        query.setParameter("email",email);
        return query.getSingleResult();
    }
    
      @Path("/{memberId}/news")
    public NewsFacade getNewsResource() {
        return news;
    }

}
