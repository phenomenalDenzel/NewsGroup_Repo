/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup;

import com.mycompany.newsgroup.entities.News;
import com.mycompany.newsgroup.entities.UserProfile;
import com.mycompany.newsgroup.utils.Link;
import com.mycompany.newsgroup.utils.Message;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Stateless
@Path("/")
public class NewsFacade extends AbstractFacade<News> {

    @PersistenceContext(unitName = "com.mycompany_newsGroup_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNewsByUser(@PathParam("memberId") int memberId) {
           TypedQuery<News> query=em.createQuery("select n from UserProfile u join u.newsList n where u.id=:memberId",News.class);
          query.setParameter("memberId", memberId);
          return query.getResultList();
    }

    @GET
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParticularNews(@PathParam("memberId") int memberId, @PathParam("newsId") int newsId, @Context UriInfo uriInfo) {
        String baseUri = uriInfo.getAbsolutePathBuilder().build().toString();
        TypedQuery<News> query = em.createQuery("select n from News n join n.userPofile u where u.id=:memberId and n.newsId=:newsId",News.class);
        query.setParameter("newsId", newsId);
        query.setParameter("memberId", memberId);
        News particularNews = query.getSingleResult();
        particularNews.setAuthor(particularNews.getUserPofile().getFirstname());
        particularNews.getLinks().add(new Link("self", baseUri));
        particularNews.getLinks().add(new Link("likes", baseUri + "/likes"));
        particularNews.getLinks().add(new Link("comments", baseUri + "/comments"));

        return Response.ok().entity(particularNews).build();

    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNews(@PathParam("memberId") int memberId,News news){
        news.setPostDate(Calendar.getInstance().getTime());
        news.setPostTime(Calendar.getInstance().getTime());
        
          UserProfile user=em.find(UserProfile.class, memberId);
          user.getNewsList().add(news);
          
          news.setUserPofile(user);
          em.persist(news);
          em.merge(user);
  
         return Response.status(Response.Status.CREATED).entity(new Message(201,"News added")).build();
    }
    @DELETE
    @Path("/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteNews(@PathParam("memberId") int memberId,@PathParam("newsId") int newsId){
         UserProfile user=em.find(UserProfile.class,memberId);
          News news=user.getNewsList().get(newsId-1);
          em.remove(news);
    }

}
