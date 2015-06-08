package com.dianping.zebra.admin.controller;

import com.dianping.cat.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public JsonError handleAllExceptions(Exception ex) {
        logger.error(ex.getMessage(), ex);
        Cat.logError(ex);
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
