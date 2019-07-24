package com.mycompany.newsgroup.entities;

import com.mycompany.newsgroup.entities.News;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-24T12:34:24")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Integer> shares;
    public static volatile SingularAttribute<Comment, News> newsNewsId;
    public static volatile SingularAttribute<Comment, Date> commentDate;
    public static volatile SingularAttribute<Comment, Integer> commentId;
    public static volatile SingularAttribute<Comment, Date> commentTime;
    public static volatile SingularAttribute<Comment, String> content;
    public static volatile SingularAttribute<Comment, Integer> likes;

}