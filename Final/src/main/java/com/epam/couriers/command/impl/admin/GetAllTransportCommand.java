package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This command shows the types of transport to which goods are delivered
 */
public class GetAllTransportCommand implements Command {

    private static final int COUNT_OF_COURIERS_ON_ONE_PAGE = 5;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        HttpSession session = request.getSession();
        int pageNumber;
        if(request.getParameter(GeneralConstant.PAGE_NUMBER) != null) {
            pageNumber = Integer.parseInt(request.getParameter(GeneralConstant.PAGE_NUMBER));
        } else {
            pageNumber = (Integer) session.getAttribute(GeneralConstant.PAGE_NUMBER);
        }
        int startIndex = (pageNumber - 1) * COUNT_OF_COURIERS_ON_ONE_PAGE;
        int totalCount;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<Transport> transports = adminService.findWithLimitTransport(startIndex, COUNT_OF_COURIERS_ON_ONE_PAGE);
            totalCount = adminService.findTotalCountOfTransport();

            page = PathManager.getProperty(PathManager.TABLE_TRANSPORTS_PAGE);
            session.setAttribute(GeneralConstant.LIST_TRANSPORT, transports);
            session.setAttribute(GeneralConstant.LIMIT_COUNT, COUNT_OF_COURIERS_ON_ONE_PAGE);
            session.setAttribute(GeneralConstant.TOTAL_COUNT, totalCount);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
