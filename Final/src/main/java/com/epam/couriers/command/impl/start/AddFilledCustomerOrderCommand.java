package com.epam.couriers.command.impl.start;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class AddFilledCustomerOrderCommand implements Command {


    private static final String PAGE_ATTRIBUTE = "page";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
