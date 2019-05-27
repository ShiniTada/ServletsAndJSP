package com.epam.couriers.command;

import com.epam.couriers.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Main interface of Command pattern which used to delegate
 * every web app action from controller to separated amount of classes,
 * flexible and extendable
 */
public interface Command {

    /**
     * Executes command using {@link HttpServletRequest} specified
     * <p>
     * ue @param reqst request from the user with necessary data to execute
     *
     * @throws CommandException if command cannot be executed or execution failed
     */
    String execute(HttpServletRequest request) throws CommandException;

}