package com.cmp.javakafka;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        String s = "hello";
        logger.info("hi {}", s);

        try {
            int i = 2 / 0;
        } catch (Exception e) {
            logger.error("", e);

            String stacktrace = ExceptionUtils.getStackTrace(e);
            logger.error(stacktrace);
        }

    }

}
