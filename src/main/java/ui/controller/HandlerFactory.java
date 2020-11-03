package ui.controller;

import domain.service.ContactService;
import domain.service.PersonService;
import domain.service.TestResultService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, PersonService model, ContactService contactModel, TestResultService testResultModel) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(model, contactModel, testResultModel);
        } catch (Exception e) {
            throw new RuntimeException("This page doesn't exist!");
        }
        return handler;
    }
}
