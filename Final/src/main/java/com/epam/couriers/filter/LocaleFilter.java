package com.epam.couriers.filter;

import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Filter used to initialize default locale
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "defaultLocale", value = "ru"),
        @WebInitParam(name = "supportedLocales", value = "en,ru")})
public class LocaleFilter implements Filter {

    private String defaultLocale;
    private List<String> supportedLocales;

    @Override
    public void init(FilterConfig config) throws ServletException {
        defaultLocale = config.getInitParameter("defaultLocale");
        supportedLocales = Arrays.asList(config.getInitParameter("supportedLocales").split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String locale = (String) session.getAttribute(GeneralConstant.LOCALE);
        if (locale == null) {
            String browserLocale = servletRequest.getLocale().getLanguage();
            if (supportedLocales.contains(browserLocale)) {
                session.setAttribute(GeneralConstant.LOCALE, browserLocale);
            } else {
                session.setAttribute(GeneralConstant.LOCALE, defaultLocale);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        defaultLocale = null;
    }
}