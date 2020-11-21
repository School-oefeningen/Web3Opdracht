package ui.controller;

import domain.service.ContactTracingService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, ContactTracingService contactTracingService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(contactTracingService);
        } catch (Exception e) {
            throw new RuntimeException("This page doesn't exist!");
        }
        return handler;
    }
}
