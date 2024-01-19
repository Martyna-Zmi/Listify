package com.example.listifyapi.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.channels.ClosedChannelException;

@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientException(HttpClientErrorException clientErrorException, RedirectAttributes redirectAttributes){
        String message = clientErrorException.getMessage().replaceFirst("^.[^\"]*", "");
        redirectAttributes.addAttribute("error",message);
        System.out.println(message);
        return "redirect:/listify/error";
    }
    @ExceptionHandler(HttpServerErrorException.class)
    public String handleServerException(HttpServerErrorException serverErrorException, RedirectAttributes redirectAttributes){
        String message = serverErrorException.getMessage().replaceFirst("^.[^\"]*", "");
        redirectAttributes.addAttribute("error", message);
        return "redirect:/listify/error";
    }
    @ExceptionHandler(ClosedChannelException.class)
    public String handleServerException(ClosedChannelException channelException, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("error", "server is offline");
        return "redirect:/listify/error";
    }
}
