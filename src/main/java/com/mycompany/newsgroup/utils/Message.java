/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.newsgroup.utils;

/**
 *
 * @author denzel
 */
public class Message {
    private int status;
    private String message;
    private String id;
    private Link link;
    

    public Message(){}
    
    public Message(int status, String message,String id){
        this.status=status;
        this.message=message;
        this.id=id;
    }
        public Message(int status, String message){
        this.status=status;
        this.message=message;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    
    
}
