package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This command shows the types of transport to which goods are delivered
 */
public class GetAllTransportCommand implements Command {

    private static final String LIST_TRANSPORTS = "listTransports";
    private static final String PAGE_ATTRIBUTE = "page";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<Transport> transports = adminService.getCourierTransports();
            request.getSession().setAttribute(LIST_TRANSPORTS, transports);
            page = PathManager.getProperty(PathManager.TABLE_TRANSPORTS_PAGE);
            request.getSession().setAttribute(PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
