package com.epam.couriers.controller;


import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.controller.helper.CommandHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main Controller of web app
 */
@WebServlet(urlPatterns = "/Controller")
public class Controller extends HttpServlet {

    private static final String COMMAND_NAME = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, false);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, true);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response, boolean toRedirect) throws ServletException, IOException {
        String page;
        CommandHelper commandHelper = new CommandHelper();
        try {
            String commandName = request.getParameter(COMMAND_NAME);
            Command command = commandHelper.getCommand(commandName);
            page = command.execute(request);

        } catch (CommandException e) {
            page = PathManager.getProperty(PathManager.ERROR_PAGE_404);
        }
        if (toRedirect) {
            response.sendRedirect(page);
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
