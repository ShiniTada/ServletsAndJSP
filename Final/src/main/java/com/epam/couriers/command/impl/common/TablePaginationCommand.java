package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TablePaginationCommand implements Command {
    private static final String PAGINATION_ATTRIBUTE = "pagination";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String BEGIN_PAG = "begin_bag";
    private static final String END_PAG= "end_pag";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session =  request.getSession();
        String pag=  request.getParameter(PAGINATION_ATTRIBUTE);
        request.setAttribute(PAGINATION_ATTRIBUTE, pag);
        switch (pag){
            case "2":
                request.setAttribute(BEGIN_PAG, 5);
                request.setAttribute(END_PAG, 9);
                break;
            case "3":
                request.setAttribute(BEGIN_PAG, 10);
                request.setAttribute(END_PAG, 14);
                break;
            case "4":
                request.setAttribute(BEGIN_PAG, 15);
                request.setAttribute(END_PAG, 19);
                break;
            case "1":
            default:
                request.setAttribute(BEGIN_PAG, 0);
                request.setAttribute(END_PAG, 4);
        }
        return (String) session.getAttribute(PAGE_ATTRIBUTE);
    }
}
