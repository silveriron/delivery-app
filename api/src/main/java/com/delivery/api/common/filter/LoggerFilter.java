package com.delivery.api.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = ((HttpServletRequest) request);
        HttpServletResponse res = ((HttpServletResponse) response);

        chain.doFilter(request, response);

        String url = req.getRequestURL().toString();

        Iterator<String> reqHeaderNameList = req.getHeaderNames().asIterator();
        StringBuilder reqHeaderBuilder = new StringBuilder();

        while (reqHeaderNameList.hasNext()) {
            String headerName = reqHeaderNameList.next();
            reqHeaderBuilder.append("[").append(headerName).append(": ").append(req.getHeader(headerName)).append("] ");
        }

        Iterator<String> resHeaderNameList = res.getHeaderNames().iterator();
        StringBuilder resHeaderBuilder = new StringBuilder();

        while (resHeaderNameList.hasNext()) {
            String headerName = resHeaderNameList.next();
            resHeaderBuilder.append("[").append(headerName).append(": ").append(res.getHeader(headerName)).append("] ");
        }

        log.info(">>>>>>>>> Request method: {}, url: {}, header: {}", req.getMethod(), url, reqHeaderBuilder);
        log.info("<<<<<<<<< Response status: {}, url: {}, header: {}", res.getStatus(), url, resHeaderBuilder);




    }
}
