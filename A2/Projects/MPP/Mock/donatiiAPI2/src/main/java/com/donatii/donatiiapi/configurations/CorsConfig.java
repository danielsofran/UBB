package com.donatii.donatiiapi.configurations;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest request = (HttpServletRequest) req;

        StringBuffer requestURL = request.getRequestURL();
        System.out.println();
        System.out.printf("Request URL: {%s}\n", requestURL);
        System.out.println("Request headers: ");
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.printf("%s: %s\n", headerName, request.getHeader(headerName)));
        System.out.println("Request content type: " + request.getContentType());
        System.out.println("Request method: " + request.getMethod());
        System.out.println("Request query string: " + request.getQueryString());
        System.out.println("Request body: ");
        System.out.println(request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + "\n" + actual));
        System.out.println();
        System.out.println("----------------------------------------------------------");
        System.out.println();

        // Set the allowed origin(s)
        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin", origin != null && origin.startsWith(WebConfig.ADDRESS) ? origin : "");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // Set other CORS headers
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Allow", "POST, PUT, GET, OPTIONS, DELETE");

        try {
            chain.doFilter(request, response);
        } finally {
            int status = response.getStatus();
            System.out.printf("Response status: {%d}", status);
        }

//        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            chain.doFilter(req, res);
//        }
    }
}