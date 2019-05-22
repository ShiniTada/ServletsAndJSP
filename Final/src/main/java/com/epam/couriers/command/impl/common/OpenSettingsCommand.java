package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;

public class OpenSettingsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        page = PathManager.getProperty(PathManager.SETTINGS_PAGE);
        request.getSession().setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;

    }
}
