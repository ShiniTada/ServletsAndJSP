package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Sign out user from the site
 */
public class SignOutCommand implements Command {

    private static final String USER = "user";

    private static final String PAGE_ATTRIBUTE = "page";
    private static final Logger LOGGER = LogManager.getLogger(SignOutCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(USER);
        LOGGER.debug("User logged out");
        String page = PathManager.getProperty(PathManager.HOME_PAGE);
        session.setAttribute(PAGE_ATTRIBUTE, page);
        return page;
    }
}
