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

    private static final int COUNT_OF_COURIERS_ON_ONE_PAGE = 5;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(GeneralConstant.USER);
        String page;
        AdminService adminService = new AdminServiceImpl();
        List<CourierRecord> listCourierRecords;
        try {
            if(user.getRole().getValue().equals(RoleEnum.ADMIN.getValue())) {
                int pageNumber;
                if(request.getParameter(GeneralConstant.PAGE_NUMBER) != null) {
                    pageNumber = Integer.parseInt(request.getParameter(GeneralConstant.PAGE_NUMBER));
                }
                else {
                    pageNumber = (Integer) session.getAttribute(GeneralConstant.PAGE_NUMBER);
                }
                int startIndex = (pageNumber - 1) * COUNT_OF_COURIERS_ON_ONE_PAGE;

                listCourierRecords = adminService.findWithLimitCouriersRecords(startIndex, COUNT_OF_COURIERS_ON_ONE_PAGE);
                int totalCount = adminService.findTotalCountOfCourierRecords();
                page = PathManager.getProperty(PathManager.TABLE_COURIERS_PAGE);
                session.setAttribute(GeneralConstant.LIMIT_COUNT, COUNT_OF_COURIERS_ON_ONE_PAGE);
                session.setAttribute(GeneralConstant.TOTAL_COUNT, totalCount);

            } else {
                String previousPage = (String) session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE);
                session.setAttribute(GeneralConstant.PREVIOUS_COURIER_PAGE_ATTRIBUTE, previousPage);
                listCourierRecords = adminService.getCouriersRecordsForCustomer();
                page = PathManager.getProperty(PathManager.COMMON_COURIER_LIST_PAGE);
            }
            session.setAttribute(GeneralConstant.LIST_COURIER_RECORDS, listCourierRecords);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
