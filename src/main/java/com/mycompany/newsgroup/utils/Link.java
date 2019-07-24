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
public class Link {
    private String rel;
    private String uri;
    
    public Link(){}
    public Link(String rel, String uri) {
        this.rel = rel;
        this.uri = uri;
    }

    
    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
}
