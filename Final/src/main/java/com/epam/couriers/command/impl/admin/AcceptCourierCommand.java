package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.RoleEnum;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AcceptCourierCommand  implements Command {
    private static final int COUNT_OF_COURIERS_ON_ONE_PAGE = 4;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page;
        int pageNumber;
        if(request.getParameter(GeneralConstant.PAGE_NUMBER) != null) {
            pageNumber = Integer.parseInt(request.getParameter(GeneralConstant.PAGE_NUMBER));
        } else {
            pageNumber = (Integer) session.getAttribute(GeneralConstant.PAGE_NUMBER);
        }

        int startIndex = (pageNumber - 1) * COUNT_OF_COURIERS_ON_ONE_PAGE;
        int totalCount = 0;
        try {
            AdminService adminService = new AdminServiceImpl();
            if(request.getParameter(GeneralConstant.ACCEPT) != null) {
                adminService.acceptCourier(Integer.parseInt(request.getParameter(GeneralConstant.ACCEPT)));
                page = PathManager.getProperty(PathManager.TABLE_NEW_COURIERS_PAGE);
                session.removeAttribute(GeneralConstant.LIST_NEW_COURIER_RECORDS);
                List<CourierRecord> listNewCourierRecords = adminService.getNewCouriersRecords();
                session.setAttribute(GeneralConstant.LIST_NEW_COURIER_RECORDS, listNewCourierRecords);

            } else if (request.getParameter(GeneralConstant.REJECT) != null) {
                 adminService.rejectCourier(Integer.parseInt(request.getParameter(GeneralConstant.REJECT)));
                page = PathManager.getProperty(PathManager.TABLE_NEW_COURIERS_PAGE);
                session.removeAttribute(GeneralConstant.LIST_NEW_COURIER_RECORDS);
                List<CourierRecord> listNewCourierRecords = adminService.getNewCouriersRecords();
                session.setAttribute(GeneralConstant.LIST_NEW_COURIER_RECORDS, listNewCourierRecords);

                } else if (request.getParameter(GeneralConstant.BLOCK) != null) {
                    adminService.blockCourier(Integer.parseInt(request.getParameter(GeneralConstant.BLOCK)));
                    page = PathManager.getProperty(PathManager.TABLE_COURIERS_PAGE);
                    session.removeAttribute(GeneralConstant.LIST_COURIER_RECORDS);
                    List<CourierRecord> listNewCourierRecords = adminService.findWithLimitCouriersRecords(startIndex, COUNT_OF_COURIERS_ON_ONE_PAGE);
                    totalCount = adminService.findTotalCountOfCourierRecords();
                    session.setAttribute(GeneralConstant.LIST_COURIER_RECORDS, listNewCourierRecords);
                    } else {
                        adminService.acceptCourier(Integer.parseInt(request.getParameter(GeneralConstant.UNBLOCK)));
                        page = PathManager.getProperty(PathManager.TABLE_COURIERS_PAGE);
                        session.removeAttribute(GeneralConstant.LIST_COURIER_RECORDS);
                        List<CourierRecord> listNewCourierRecords = adminService.findWithLimitCouriersRecords(startIndex, COUNT_OF_COURIERS_ON_ONE_PAGE);
                        totalCount = adminService.findTotalCountOfCourierRecords();
                        session.setAttribute(GeneralConstant.LIST_COURIER_RECORDS, listNewCourierRecords);

            }
            session.setAttribute(GeneralConstant.TOTAL_COUNT, totalCount);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
