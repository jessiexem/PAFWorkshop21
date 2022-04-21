package sg.nus.iss.workshop21Northwind.model;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int customerId;
    private String last_name;
    private String first_name;
    private String address;
    private String business_phone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusiness_phone() {
        return business_phone;
    }

    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }

    public static List<Customer> create(SqlRowSet rs) {
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            Customer c = new Customer();
            c.setCustomerId(rs.getInt("id"));
            c.setLast_name(rs.getString("last_name"));
            c.setFirst_name(rs.getString("first_name"));
            c.setAddress(rs.getString("address"));
            c.setBusiness_phone(rs.getString("business_phone"));
            customerList.add(c);
        }
        return customerList;
    }

    public static JsonArray createCustomerJsonArr(List<Customer> list) {

        JsonArrayBuilder custArrBuilder = Json.createArrayBuilder();

        JsonObjectBuilder custBuilder = Json.createObjectBuilder();
        for (Customer c: list) {
            custBuilder.add("customerId", c.getCustomerId())
                    .add("last_name",c.getLast_name())
                    .add("first_name",c.getFirst_name())
                    .add("address",c.getAddress())
                    .add("business_phone", c.getBusiness_phone());
            custArrBuilder.add(custBuilder);
        }
        JsonArray customersJson = custArrBuilder.build();

        return customersJson;
    }


}
