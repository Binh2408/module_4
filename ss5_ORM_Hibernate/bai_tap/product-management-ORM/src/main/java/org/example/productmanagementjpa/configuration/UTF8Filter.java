package org.example.productmanagementjpa.configuration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter(value = "*")
public class UTF8Filter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req,res);
    }
}
