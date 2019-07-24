package com.mycompany.newsgroup.entities;

import com.mycompany.newsgroup.entities.News;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-24T12:34:24")
@StaticMetamodel(UserProfile.class)
public class UserProfile_ { 

    public static volatile ListAttribute<UserProfile, News> newsList;
    public static volatile SingularAttribute<UserProfile, String> password;
    public static volatile SingularAttribute<UserProfile, String> firstname;
    public static volatile SingularAttribute<UserProfile, String> salt;
    public static volatile SingularAttribute<UserProfile, Integer> id;
    public static volatile SingularAttribute<UserProfile, byte[]> pic;
    public static volatile SingularAttribute<UserProfile, String> email;
    public static volatile SingularAttribute<UserProfile, String> lastname;

}