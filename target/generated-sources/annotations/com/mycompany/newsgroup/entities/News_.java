package com.mycompany.newsgroup.entities;

import com.mycompany.newsgroup.entities.Comment;
import com.mycompany.newsgroup.entities.UserProfile;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-24T12:34:24")
@StaticMetamodel(News.class)
public class News_ { 

    public static volatile SingularAttribute<News, Integer> shares;
    public static volatile ListAttribute<News, Comment> commentList;
    public static volatile SingularAttribute<News, Integer> newsId;
    public static volatile SingularAttribute<News, Date> postTime;
    public static volatile SingularAttribute<News, Date> postDate;
    public static volatile SingularAttribute<News, UserProfile> userPofile;
    public static volatile SingularAttribute<News, String> content;
    public static volatile SingularAttribute<News, Integer> likes;

}