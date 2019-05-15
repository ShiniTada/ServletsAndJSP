package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
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

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<CourierRecord> listCourierRecords = adminService.getAllCouriersRecords();
            request.getSession().setAttribute(GeneralConstant.LIST_COURIER_RECORDS, listCourierRecords);
            page = PathManager.getProperty(PathManager.TABLE_COURIERS_PAGE);
            request.getSession().setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
