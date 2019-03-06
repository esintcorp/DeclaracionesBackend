package com.esintcorp.security.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;

    @Autowired
    public CustomAuthenticationFailureHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
//		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        //Data that will later be serialized into JSON so the client can process it
        Map<String, Object> responseData = new HashMap<>();
        //Type should be used to localize the error messages on the client side
        responseData.put("id", e.getClass().getSimpleName());
        responseData.put("message", e.getLocalizedMessage());
        //TODO delete this lines and uncomment above two lines when refactor on client has been done:
//        responseData.put("errorType", Utils.uncapitalize(e.getClass().getSimpleName()));
//        responseData.put("errorMessage", e.getLocalizedMessage());

        response.setContentType("application/json; charset=utf-8");

        //Send JSON back to the client with the CSRF token
        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, responseData);
        writer.flush();
	}

}
