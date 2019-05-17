package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.constants.GeneralConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BackAfterDetailsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String typeBack = request.getParameter(GeneralConstant.BACK);
        String page;
        if (typeBack.equals("afterAllCouriers")) {
            page = (String) session.getAttribute(GeneralConstant.PREVIOUS_COURIER_PAGE_ATTRIBUTE);
        } else if (typeBack.equals("afterOrderForm")) {
                page = (String) session.getAttribute(GeneralConstant.PREVIOUS_COURIER_PAGE_ATTRIBUTE);
                } else{
                    session.removeAttribute(GeneralConstant.COURIER_RECORD);
                    session.removeAttribute(GeneralConstant.LIST_TRANSPORT);
                    session.removeAttribute(GeneralConstant.LIST_GOODS);
                    page = (String) session.getAttribute(GeneralConstant.PREVIOUS_PAGE_ATTRIBUTE);
        }
        session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
        return page;

    }
}
