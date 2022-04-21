package sg.nus.iss.workshop21Northwind.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import sg.nus.iss.workshop21Northwind.model.Customer;
import sg.nus.iss.workshop21Northwind.model.Order;

import java.util.List;
import java.util.Optional;

import static sg.nus.iss.workshop21Northwind.repository.Queries.*;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Customer> getAllCustomers() {
        return getAllCustomers(Integer.MAX_VALUE,0);
    }

    public List<Customer> getAllCustomers(Integer offset) {
        return getAllCustomers(10,offset);
    }

    public List<Customer> getAllCustomers(Integer limit, Integer offset) {
        final SqlRowSet result = template.queryForRowSet(SQL_FIND_ALL_CUSTOMERS,limit,offset);

        return Customer.create(result);
    }

    public Optional<List<Customer>> getCustomerById(Integer custId) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_BY_CUST_ID,custId);
        if (!result.isBeforeFirst()) {
            System.out.println(">>>>>> CustRepo - getCustomerById: no data");
            return Optional.empty();
        }
        else return Optional.of(Customer.create(result));
    }

    public List<Order> getAllOrdersByCustomerId(Integer custId) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_ALL_ORDERS_BY_CUST_ID,custId);
        return Order.create(result);
    }
}
