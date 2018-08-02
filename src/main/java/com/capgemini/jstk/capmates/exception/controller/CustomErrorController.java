package com.capgemini.jstk.capmates.exception.controller;

import com.capgemini.jstk.capmates.exception.ErrorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Map;

@RestController

public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(value = "/error", produces = "application/json")
    @ResponseBody
    public String error(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        String error;
        String message;

        if(response.getStatus() % 500 < 100){
            error = "Server has problem.";
            message = "Sorry you must wait.";
        }else if(response.getStatus() % 400 < 100){
            error = "You made a mistake.";
            message = "Please, give correct data";
        }else{
            error = "I am error";
            message = "I like you";
        }

        ErrorDTO errorDTO = new ErrorDTO(error, message);
        return new ObjectMapper().writeValueAsString(errorDTO);


    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest requestAttributes = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}
