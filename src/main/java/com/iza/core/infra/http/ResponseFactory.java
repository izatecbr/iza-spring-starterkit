package com.iza.core.infra.http;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseFactory {
    public static Response ok(Object body) {
        return ok(body,"Operação realizada com sucesso");
    }
    public static Response ok(Object body, String message) {
        return response(HttpStatus.OK.value(), body,message);
    }
    public static Response create(Object body, String message) {
        return response(HttpStatus.CREATED.value(), body,message);
    }
    private static Response response(Serializable code, Object body, String message) {
        return define(code,body,message,"",true);
    }

    public static Response error() {
        return error("Error","Contacte o Suporte Técnico");
    }
    public static Response error(String message, String suggestion) {
        return error(500,message,suggestion);
    }
    public static Response error(Serializable code,String message, String suggestion){
        return define(code,null, message, suggestion, false);
    }
    private static Response define(Serializable code,Object body, String message, String suggestion, boolean success){
        Response response = new Response();
        ResponseStatus status = new ResponseStatus();
        status.code =code;
        status.message = message;
        status.suggestion = suggestion;
        status.success = success;
        response.setStatus(status);
        response.setBody(body);
        return response;
    }
}