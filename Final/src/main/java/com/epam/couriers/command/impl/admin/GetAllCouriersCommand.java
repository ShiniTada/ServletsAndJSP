package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.entity.User;
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
            HttpSession session = request.getSession();
            AdminService adminService = new AdminServiceImpl();
            List<CourierRecord> listCourierRecords = adminService.getAllCouriersRecords();
            session.setAttribute(GeneralConstant.LIST_COURIER_RECORDS, listCourierRecords);
            User user = (User) session.getAttribute(GeneralConstant.USER);
            if(user.getRole().getValue().equals("admin")) {
                page = PathManager.getProperty(PathManager.TABLE_COURIERS_PAGE);
            } else {
                String previousPage = (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
                session.setAttribute(GeneralConstant.PREVIOUS_COURIER_PAGE_ATTRIBUTE, previousPage);
                page = PathManager.getProperty(PathManager.COMMON_COURIER_LIST_PAGE);
            }
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
