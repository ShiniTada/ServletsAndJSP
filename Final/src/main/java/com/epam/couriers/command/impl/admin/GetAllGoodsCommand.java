package com.epam.couriers.command.impl.admin;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.Goods;
import com.epam.couriers.service.AdminService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This command shows the types of products transported
 */
public class GetAllGoodsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            AdminService adminService = new AdminServiceImpl();
            List<Goods> goods = adminService.getCourierGoods();
            request.getSession().setAttribute(GeneralConstant.LIST_GOODS, goods);
            page = PathManager.getProperty(PathManager.TABLE_GOODS_PAGE);
            request.getSession().setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
