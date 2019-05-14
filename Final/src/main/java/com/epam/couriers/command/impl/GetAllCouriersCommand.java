package com.epam.couriers.command.impl;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.UserService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;
import com.epam.couriers.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This command shows information about all registered couriers
 */
public class GetAllCouriersCommand implements Command {

    private static final String LIST_COURIER_RECORDS = "listCourierRecords";

    private static final String PAGE_ATTRIBUTE = "page";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<CourierRecord> listCourierRecords = adminService.getAllCouriersRecords();
            request.getSession().setAttribute(LIST_COURIER_RECORDS, listCourierRecords);
            page = PathManager.getProperty(PathManager.TABLE_COURIERS_PAGE);
            request.getSession().setAttribute(PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
