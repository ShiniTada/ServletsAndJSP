package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BackAfterDetailsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(GeneralConstant.COURIER_RECORD);
        session.removeAttribute(GeneralConstant.LIST_TRANSPORT);
        session.removeAttribute(GeneralConstant.LIST_GOODS);
        session.removeAttribute(GeneralConstant.PAGE_ATTRIBUTE);

        String page = (String) session.getAttribute(GeneralConstant.PREVIOUS_PAGE_ATTRIBUTE);
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);

        session.removeAttribute(GeneralConstant.PREVIOUS_PAGE_ATTRIBUTE);
        return page;
    }
}
