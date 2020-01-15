package com.esintcorp;

import java.io.IOException;
//import java.net.URI;
//import java.util.Enumeration;
//import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

import static java.nio.charset.StandardCharsets.UTF_8;

//@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

//    @Autowired
//    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(requestWrapper, responseWrapper);

//        String requestUrl = requestWrapper.getRequestURL().toString();
//        HttpHeaders requestHeaders = new HttpHeaders();
//        Enumeration headerNames = requestWrapper.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = (String) headerNames.nextElement();
//            requestHeaders.add(headerName, requestWrapper.getHeader(headerName));
//        }
//        HttpMethod httpMethod = HttpMethod.valueOf(requestWrapper.getMethod());
        requestWrapper.getParameterMap();

//        String requestBody = IOUtils.toString(requestWrapper.getInputStream(),UTF_8);
//        JsonNode requestJson = objectMapper.readTree(requestBody);

//        RequestEntity<JsonNode> requestEntity = new RequestEntity<>(requestJson,requestHeaders, httpMethod, URI.create(requestUrl));
        LOGGER.info("Logging Http Request");
//        LOGGER.info(requestBody);
        LOGGER.info(requestWrapper.getMethod());


//        HttpStatus responseStatus = HttpStatus.valueOf(responseWrapper.getStatusCode());
//        HttpHeaders responseHeaders = new HttpHeaders();
//        for (String headerName : responseWrapper.getHeaderNames()) {
//            responseHeaders.add(headerName, responseWrapper.getHeader(headerName));
//        }
        String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
//        JsonNode responseJson = objectMapper.readTree(responseBody);
//        ResponseEntity<JsonNode> responseEntity = new ResponseEntity<>(responseJson,responseHeaders,responseStatus);
        LOGGER.info("Logging Http Response");
        LOGGER.info("Status: " + responseWrapper.getStatusCode());
        LOGGER.info("Body: " + responseBody);
        responseWrapper.copyBodyToResponse();
    }
}