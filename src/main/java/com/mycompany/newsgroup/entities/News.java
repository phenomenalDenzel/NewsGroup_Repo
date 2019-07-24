/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup.entities;

import com.mycompany.newsgroup.utils.Link;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
//import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//import org.glassfish.jersey.server.JSONP;

/**
 *
 * @author denzel
 */
@Entity
@Table(name = "news")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n")})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "news_id")
    private Integer newsId;
    @Size(max = 256)
    @Column(name = "content")
    private String content;
    @Column(name = "post_time")
    @Temporal(TemporalType.TIME)
    private Date postTime;
    @Column(name = "post_date")
    @Temporal(TemporalType.DATE)
    private Date postDate;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "shares")
    private Integer shares;
    @JoinColumn(name = "userPofile_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserProfile userPofile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsNewsId")
    private List<Comment> commentList;
    @Transient
    private List<Link> links=new ArrayList<Link>();
     @Transient
    private String author;

    public News() {
    }

    public News(Integer newsId) {
        this.newsId = newsId;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
    

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }
    @XmlTransient
    @JsonbTransient
    public UserProfile getUserPofile() {
        return userPofile;
    }

    public void setUserPofile(UserProfile userPofile) {
        this.userPofile = userPofile;
    }

    @XmlTransient
   @JsonbTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsId != null ? newsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.newsId == null && other.newsId != null) || (this.newsId != null && !this.newsId.equals(other.newsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.newsgroup.entities.News[ newsId=" + newsId + " ]";
    }
    
}
