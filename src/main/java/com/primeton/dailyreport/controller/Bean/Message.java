package com.primeton.dailyreport.controller.Bean;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class Message {

    public static final String MESSAGE_NAME = "DAILY_MESSAGE";

    public static enum MessageType {
        notice, warning, error
    }

    private MessageType type;

    private String message;

    private List<String> exceptions;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public boolean isError() {
        return type == MessageType.error;
    }

    public boolean isWarning() {
        return type == MessageType.warning;
    }

    public boolean isNotice() {
        return type == MessageType.notice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public Message(MessageType type, String message) {
        super();
        this.type = type;
        this.message = message;
    }

    public Message(MessageType type, String message, List<String> exceptions) {
        super();
        this.type = type;
        this.message = message;
        this.setExceptions(exceptions);
    }


    public static void setNotice(String messageCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext context = new RequestContext(request);
        request.setAttribute(Message.MESSAGE_NAME, new Message(MessageType.notice, context.getMessage(messageCode)));
    }

    public static void setWarning(String messageCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext context = new RequestContext(request);
        request.setAttribute(Message.MESSAGE_NAME, new Message(MessageType.warning, context.getMessage(messageCode)));
    }

    public static void setError(String messageCode) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext context = new RequestContext(request);
        request.setAttribute(Message.MESSAGE_NAME, new Message(MessageType.error, context.getMessage(messageCode)));
    }

    public static void setError(String messageCode, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestContext context = new RequestContext(request);
        request.setAttribute(Message.MESSAGE_NAME, new Message(MessageType.error, context.getMessage(messageCode)));
    }
}