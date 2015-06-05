package com.dianping.zebra.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Dozer @ 6/5/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class BasicController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public JsonError handleAllExceptions(Exception ex) {
        return new JsonError(ex.getMessage());
    }

    public class JsonError {
        private final String message;

        public JsonError(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
