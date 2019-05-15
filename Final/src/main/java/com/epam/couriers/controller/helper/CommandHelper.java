package com.epam.couriers.controller.helper;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.CommandName;
import com.epam.couriers.command.impl.UnknownCommand;
import com.epam.couriers.command.impl.admin.*;
import com.epam.couriers.command.impl.common.*;
import com.epam.couriers.command.impl.start.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents full list of commands, used by users
 */
public class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandHelper(){

        commands.put(CommandName.OPEN_SIGN_IN, new OpenSignInCommand());

        commands.put(CommandName.SIGN_IN, new SignInCommand());

        commands.put(CommandName.SIGN_OUT, new SignOutCommand());

        commands.put(CommandName.SET_LOCALE, new SetLocaleCommand());

        commands.put(CommandName.COURIER_REGISTRATION, new CourierRegistrationCommand());

        commands.put(CommandName.OPEN_COURIER_REGISTRATION, new OpenCourierRegistrationCommand());

        commands.put(CommandName.OPEN_CUSTOMER_REGISTRATION, new OpenCustomerRegistrationCommand());

        commands.put(CommandName.ADD_FILLED_CUSTOMER_ORDER, new AddFilledCustomerOrderCommand());


        commands.put(CommandName.GET_NEW_COURIERS_RECORDS, new GetNewCouriersRecordsCommand());

        commands.put(CommandName.GET_ALL_COURIERS, new GetAllCouriersCommand());

        commands.put(CommandName.GET_ALL_ORDERS, new GetAllOrdersCommand());

        commands.put(CommandName.GET_ALL_GOODS, new GetAllGoodsCommand());

        commands.put(CommandName.GET_ALL_TRANSPORT, new GetAllTransportCommand());

        commands.put(CommandName.TABLE_PAGINATION, new TablePaginationCommand());

        commands.put(CommandName.ACCEPT_COURIER, new AcceptCourierCommand());

        commands.put(CommandName.REJECT_COURIER, new RejectCourierCommand());

        commands.put(CommandName.MORE_DETAILS, new MoreDetailsCommand());

        commands.put(CommandName.BACK_AFTER_DETAILS, new BackAfterDetailsCommand());
    }


    public Command getCommand(String commandName){
        commandName = commandName.replace('-', '_').toUpperCase();
        CommandName key = CommandName.valueOf(commandName);
        Command command = commands.get(key);

        if(command == null){
            command =  new UnknownCommand();
        }
        return command;
    }

}
