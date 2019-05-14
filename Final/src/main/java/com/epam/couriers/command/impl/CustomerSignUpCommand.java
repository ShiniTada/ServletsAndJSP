package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * This command registers the customer and opens his page
 */
public class CustomerSignUpCommand implements Command {


    private static final String PAGE_ATTRIBUTE = "page";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
