package com.epam.couriers.command.impl.common;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.exception.CommandException;
import com.epam.couriers.command.resource.PathManager;
import com.epam.couriers.constants.GeneralConstant;
import com.epam.couriers.entity.CourierRecord;
import com.epam.couriers.entity.Goods;
import com.epam.couriers.entity.Transport;
import com.epam.couriers.service.CourierService;
import com.epam.couriers.service.exception.ServiceException;
import com.epam.couriers.service.impl.CourierServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * This command open public courier page
 */
public class MoreDetailsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page;
        try {
            CourierRecord courierRecord = new CourierRecord(Integer.parseInt(request.getParameter(GeneralConstant.COURIER_RECORD_ID)));
            CourierService courierService = new CourierServiceImpl();
            courierRecord = courierService.getCourierRecord(courierRecord);

            List<Transport> transport = courierService.getTransportsOfOneCourier(courierRecord.getId());
            List<String> listTransport = new ArrayList<>();
            for (Transport t : transport) {
                listTransport.add(t.getTypeTransport().getValue());
            }
            List<Goods> goods = courierService.getGoodsOfOneCourier(courierRecord.getId());
            List<String> listGoods = new ArrayList<>();
            for (Goods g : goods) {
                listGoods.add(g.getTypeGoods().getValue());
            }
            session.setAttribute(GeneralConstant.COURIER_RECORD, courierRecord);
            session.setAttribute(GeneralConstant.LIST_TRANSPORT, listTransport);
            session.setAttribute(GeneralConstant.LIST_GOODS, listGoods);
            session.setAttribute(GeneralConstant.PREVIOUS_PAGE_ATTRIBUTE, session.getAttribute(GeneralConstant.PAGE_ATTRIBUTE));
            page = PathManager.getProperty(PathManager.COURIER_PUBLIC_PAGE);
            session.setAttribute(GeneralConstant.PAGE_ATTRIBUTE, page);
            return page;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
