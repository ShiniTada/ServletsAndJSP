package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.entity.User;
import com.epam.couriers.service.UserService;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocaleCommand implements Command {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(SetLocaleCommand.class);

    private static final String LOCALE_PARAMETER = "locale";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String PAGE_ATTRIBUTE = "page";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String newLocale = request.getParameter(LOCALE_PARAMETER);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE_ATTRIBUTE, newLocale);
        String page = (String) session.getAttribute(PAGE_ATTRIBUTE);
        if(page == null){
            page = PathManager.getProperty(PathManager.HOME_PAGE);
        }
        return page;
    }
}