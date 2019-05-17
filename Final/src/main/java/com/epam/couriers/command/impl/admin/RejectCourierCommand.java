package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RejectCourierCommand  implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            adminService.rejectCourier(Integer.parseInt(request.getParameter(GeneralConstant.REJECT)));
            session.removeAttribute(GeneralConstant.LIST_NEW_COURIER_RECORDS);

            List<CourierRecord> listNewCourierRecords = adminService.getNewCouriersRecords();
            session.setAttribute(GeneralConstant.LIST_NEW_COURIER_RECORDS, listNewCourierRecords);
            page = PathManager.getProperty(PathManager.TABLE_NEW_COURIERS_PAGE);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
