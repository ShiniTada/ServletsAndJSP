package com.epam.couriers.constants;

/**
 * Class with constant
 */
final public class GeneralConstant {

    public static final String DB_PROPERTIES = "db";


    public static final String MESSAGE_ATRIBUTE = "message";

    public static final String PAGE_ATTRIBUTE = "page";
    public static final String PREVIOUS_PAGE_ATTRIBUTE = "previousPage";
    public static final String PREVIOUS_COURIER_PAGE_ATTRIBUTE = "previousCourierPage";
    public static final String COURIER_RECORD_ID = "courierRecordId";
    public static final String COURIER_RECORD = "courierRecord";
    public static final String COURIER_ID = "courierId";
    public static final String USER_ID = "userId";
    public static final String USER_ROLE = "role";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_REPEATED_PASSWORD = "repeatedPassword";
    public static final String MARK_QUALITY = "markQuality";
    public static final String MARK_POLITENESS = "markPoliteness";
    public static final String MARK_PUNCTUALITY = "markPunctuality";
    public static final String MARK_COMMON = "markCommon";
    public static final String TYPE_TRANSPORT = "typeTransport";
    public static final String TYPE_GOODS = "typeGoods";
    public static final String ORDER_ID = "orderId";
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String CUSTOMER_ID = "customerId";
    public static final String INTRODUCTION_DATE = "introductionDate";
    public static final String STATUS = "status";
    public static final String GOOD_DESCRIPTION = "goodsDescription";
    public static final String BACK = "back";


    public static final String LIST_GOODS = "listGoods";
    public static final String LIST_USERS = "listUsers";
    public static final String LIST_TRANSPORT = "listTransport";
    public static final String LIST_NEW_COURIER_RECORDS = "listNewCourierRecords";
    public static final String LIST_COURIER_RECORDS = "listCourierRecords";
    public static final String LIST_ORDERS = "listOrders";
    public static final String EXISTED_ORDERS = "existedOrders";
    public static final String COMPLETED_ORDERS = "completedOrders";

    public static final String FOOD_ATTRIBUTE = "food";
    public static final String TECH_ATTRIBUTE = "tech";
    public static final String FURNITURE_ATTRIBUTE = "furniture";
    public static final String EASY_TO_BEAT_ATTRIBUTE = "easy-to-beat";

    public static final String CYCLE_ATTRIBUTE = "cycle";
    public static final String MOTORCYCLE_ATTRIBUTE = "motorcycle";
    public static final String CAR_ATTRIBUTE = "car";
    public static final String TRUCK_ATTRIBUTE = "truck";

    public static final String CUSTOMER_ORDERS = "listCustomerOrder";

    public static final String ACCEPT = "accept";
    public static final String REJECT = "reject";


    public static final String USER = "user";
    public static final String LOCALE = "locale";

    public static final String WHAT_DELIVER = "whatDeliver";
    public static final String COUNT_POINTS = "countPoints";
    public static final String WEIGHT = "weight";
    public static final String DELIVERY_DATE = "deliveryDate";


    private GeneralConstant() {
    }

    public static final class DataBase {
        public static final String DRIVER = "driver";
        public static final String URL = "url";
        public static final String USER = "user";
        public static final String PASSWORD = "password";
        public static final String USE_UNICODE = "useUnicode";
        public static final String CHARACTER_ENCODING = "characterEncoding";
        public static final String AUTO_RECONNECT = "autoReconnect";

    }


    public static class ConnectionPool {
        public static final String CAPACITY = "poolCapacity";
    }


}
