package com.epam.couriers.command.resource;

import java.util.ResourceBundle;

/**
 * Class which provides handy access to jsp paths properties
 */
public final class PathManager {

    /**
     * Path of index page (web app entry point)
     */
    public static final String HOME_PAGE = "page.home_page";
    public static final String ERROR_PAGE_404 = "page.error_page_404";

    public static final String ADMIN_PAGE = "page.admin_page";
    public static final String COURIER_PAGE = "page.courier_page";
    public static final String CUSTOMER_PAGE = "page.customer_page";

    public static final String SIGN_IN_PAGE = "page.sign_in_page";

    public static final String TABLE_NEW_COURIERS_PAGE = "page.table_new_couriers_page";
    public static final String TABLE_COURIERS_PAGE = "page.table_couriers_page";
    public static final String TABLE_ORDERS_PAGE = "page.table_orders_page";
    public static final String TABLE_TRANSPORTS_PAGE = "page.table_transports_page";
    public static final String TABLE_GOODS_PAGE = "page.table_goods_page";



    public static final String PLACE_ORDER_PAGE = "page.place_order_page";
    public static final String USER_SETTINGS_PAGE = "page.user_settings_page";
    public static final String FORGOT_PASSWORD_PAGE = "page.forgot_password_page";



    private static final String RESOURCE_NAME = "path";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME);

    private PathManager() {
    }

    /**
     * Returns jsp paths's property value with specified key
     * @param key key of the property.
     * @return value of property with the specified key
     */
    public static String getProperty(String key) {
        return bundle.getString(key);
    }
}