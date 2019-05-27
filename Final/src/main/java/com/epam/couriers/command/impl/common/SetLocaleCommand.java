package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This command change locale
 */
public class SetLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String newLocale = request.getParameter(GeneralConstant.LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(GeneralConstant.LOCALE, newLocale);
        String page = (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
        if (page == null) {
            page = PathManager.getProperty(PathManager.HOME_PAGE);
        }
        return page;
    }
}