package sg.nus.iss.workshop21Northwind.model;

import jakarta.json.*;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.List;

public class Order {

    private Integer id;
    private Date order_date;
    private Date shipped_date;
    private Integer shipper_id;
    private String ship_name;
    private String ship_address;
    private double shipping_fee;
    private String payment_type;
    private Date paid_date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(Date shipped_date) {
        this.shipped_date = shipped_date;
    }

    public Integer getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(Integer shipper_id) {
        this.shipper_id = shipper_id;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_address() {
        return ship_address;
    }

    public void setShip_address(String ship_address) {
        this.ship_address = ship_address;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public Date getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(Date paid_date) {
        this.paid_date = paid_date;
    }

    public static List<Order> create (SqlRowSet rs) {
        List<Order> orderList = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setOrder_date(rs.getDate("order_date"));

            Date shipped_date = rs.getDate("shipped_date");
            order.setShipped_date(shipped_date);
            order.setShipper_id(rs.getInt("shipper_id"));
            order.setShip_name(rs.getString("ship_name"));
            order.setShip_address(rs.getString("ship_address"));
            order.setShipping_fee(rs.getDouble("shipping_fee"));
            order.setPayment_type(rs.getString("payment_type"));
            order.setPaid_date(rs.getDate("paid_date"));
            orderList.add(order);
        }
        return orderList;
    }

    public static JsonArray createOrdersJsonArr(List<Order> orderList) {
        JsonArrayBuilder orderArrBuilder = Json.createArrayBuilder();
        JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
        for (Order o : orderList) {
            orderBuilder.add("orderId",o.getId())
                    .add("order_date", o.getOrder_date()!=null? o.getOrder_date().toString() : "")
                    .add("shipper_id",o.getShipper_id()!=null? o.getShipper_id().toString() : "")
                    .add("shipped_date", o.getShipped_date()!=null? o.getShipped_date().toString() : "")
                    .add("ship_name", o.getShip_name())
                    .add("ship_address",o.getShip_address())
                    .add("shipping_fee",o.getShipping_fee())
                    .add("payment_type",o.getPayment_type()!=null? o.getPayment_type() : "")
                    .add("paid_date", o.getPaid_date()!=null? o.getPaid_date().toString() : "");
            orderArrBuilder.add(orderBuilder);
        }
        JsonArray ordersJson = orderArrBuilder.build();
        return ordersJson;
    }
}
