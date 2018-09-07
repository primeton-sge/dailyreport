package com.primeton.dailyreport.controller.Bean;

/**
 * Created by qinhao on 2018/7/3.
 */
public class Nodes {

    private String text;
    private String requestURI;

    public Nodes() {
    }

    public Nodes(String text, String requestURI) {
        this.text = text;
        this.requestURI = requestURI;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }
}
