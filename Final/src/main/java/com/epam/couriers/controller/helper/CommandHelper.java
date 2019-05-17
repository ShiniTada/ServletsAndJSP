package com.epam.couriers.controller.helper;

import com.epam.couriers.command.Command;
import com.epam.couriers.command.CommandName;
import com.epam.couriers.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents full list of commands, used by users
 */
public class CommandHelper {
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandHelper(){
        //////////////////Доки в Command

        commands.put(CommandName.SIGN_IN, new SignInCommand());

        commands.put(CommandName.SIGN_OUT, new SignOutCommand());

        commands.put(CommandName.SET_LOCALE, new SetLocaleCommand());

        commands.put(CommandName.COURIER_REGISTRATION, new CourierRegistrationCommand());

        commands.put(CommandName.CUSTOMER_SIGN_UP, new CustomerSignUpCommand());

        /**
         * This command adds the filled order and places it on the courier page
         */
        commands.put(CommandName.ADD_FILLED_CUSTOMER_ORDER, new AddFilledCustomerOrderCommand());



        commands.put(CommandName.GET_NEW_COURIERS_RECORDS, new GetNewCouriersRecordsCommand());

        commands.put(CommandName.GET_ALL_COURIERS, new GetAllCouriersCommand());

        commands.put(CommandName.GET_ALL_ORDERS, new GetAllOrdersCommand());

        commands.put(CommandName.GET_ALL_GOODS, new GetAllGoodsCommand());

        commands.put(CommandName.GET_ALL_TRANSPORT, new GetAllTransportCommand());

        commands.put(CommandName.TABLE_PAGINATION, new TablePaginationCommand());
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
