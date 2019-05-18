package com.esintcorp;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;

//import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;

//import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

//@Component
public class CustomRequestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        long startTime = Instant.now().toEpochMilli();
        logger.info("Request URL::" + request.getRequestURL().toString() +
            ":: Start Time=" + Instant.now());
        request.setAttribute("startTime", startTime);
//        ContentCachingRequestWrapper reqw = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper rw = new ContentCachingResponseWrapper(response);
//        
//        reqw.getParameterMap();
//        logger.info(" ** REQ: ** " + request.getHeaderNames());
//        logger.info(" ** REQ: ** " + request.getContentType());
//        String reqBody = IOUtils.toString(reqw.getInputStream(), UTF_8);
//        logger.info(" ** rw: ** " + reqBody);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        
        long startTime = (Long) request.getAttribute("startTime");
        
        logger.info("Request URL::" + request.getRequestURL().toString() +
            ":: Time Taken=" + (Instant.now().toEpochMilli() - startTime));
        ContentCachingRequestWrapper reqw = new ContentCachingRequestWrapper(request);
//        ContentCachingResponseWrapper rw = new ContentCachingResponseWrapper(response);
        
        reqw.getParameterMap();
        logger.info(" ** RESPONSE: ** " + response.getHeaderNames());
        logger.info(" ** RESPONSE: ** " + response.getContentType());
        logger.info(" ** RESPONSE: ** " + response.getStatus());
//        String responseBody = IOUtils.toString(rw.getContentInputStream(), UTF_8);
//        logger.info(" ** rw: ** " + responseBody);
    }
}
