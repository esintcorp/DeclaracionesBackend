package com.esintcorp.security.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import com.esintcorp.data.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final ObjectMapper mapper;

    @Autowired
    public CustomAuthenticationSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws IOException, ServletException {

System.out.println("STATUS:::: " + response.getStatus());

        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		HttpSession session = request.getSession();
		session.setAttribute("UserID", user.getUser().getId());

		Map<String, Object> responseData = new HashMap<>();
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		String token = "";
		if (csrf != null) {
			token = csrf.getToken();
		}
		responseData.put("token", token);

        responseData.put("sessionInfo", (User) user.getUser());

        //Send JSON back to the client with the CSRF token
        PrintWriter writer = response.getWriter();
        mapper.writeValue(writer, responseData);
        writer.flush();
	}

}
