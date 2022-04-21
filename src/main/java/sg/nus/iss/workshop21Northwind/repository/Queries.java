package sg.nus.iss.workshop21Northwind.repository;

public interface Queries {

    public static final String SQL_FIND_ALL_CUSTOMERS =
            "select id,last_name, first_name, address, business_phone from customers limit ? offset ?;";

    public static final String SQL_SELECT_BY_CUST_ID =
            "select id,last_name, first_name, address, business_phone from customers where id=?";


    public static final String SQL_SELECT_ALL_ORDERS_BY_CUST_ID =
            """
               select id, cast(order_date as Date) as order_date, cast(shipped_date as Date) as shipped_date,
                shipper_id, ship_name, ship_address, shipping_fee, payment_type, cast(paid_date as Date) 
                as paid_date from orders where customer_id = ?;
                    """;
}
