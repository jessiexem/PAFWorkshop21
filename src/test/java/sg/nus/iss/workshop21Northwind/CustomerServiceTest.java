package sg.nus.iss.workshop21Northwind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sg.nus.iss.workshop21Northwind.model.Customer;
import sg.nus.iss.workshop21Northwind.model.Order;
import sg.nus.iss.workshop21Northwind.repository.CustomerRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerSvc;

    @MockBean
    CustomerRepository customerRepo;

    @Test
    public void shouldReturnAllCustomers() {
        List<Customer> customerList = createCustomers();
        Mockito.when(customerRepo.getAllCustomers(any(),any())).thenReturn(customerList);
        List<Customer> actual = customerRepo.getAllCustomers(5,0);
        Assertions.assertEquals(customerList,actual);
        Assertions.assertEquals("Barney",actual.get(0).getFirst_name());
    }

    @Test
    public void shouldReturnOptionalEmptyForGetCustomerById() {
        Integer custId = -1;
        Mockito.when(customerRepo.getCustomerById(any()))
                .thenReturn(Optional.empty());

        Optional<List<Customer>> opt = customerRepo.getCustomerById(custId);
        Assertions.assertFalse(opt.isPresent(),"should not find customerId %d".formatted(custId));
    }

    @Test
    public void shouldReturnAllOrdersForCustomerId1() {
        Integer custId = 1;
        List<Order> orderList = createOrders();
        Mockito.when(customerRepo.getAllOrdersByCustomerId(custId))
                .thenReturn(orderList);

        Optional<List<Order>> actual = customerSvc.getAllOrdersByCustomerId(custId);
        Assertions.assertTrue(actual.isPresent());
        Assertions.assertEquals(actual.get().get(0).getOrder_date(),Date.valueOf("2006-03-24"));
    }

    public List<Customer> createCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setCustomerId(1);
        c1.setFirst_name("Barney");
        c1.setLast_name("Tan");
        c1.setAddress("Bukit Batok");
        c1.setBusiness_phone("(123)456-0100");
        customerList.add(c1);

        Customer c2 = new Customer();
        c2.setCustomerId(2);
        c2.setFirst_name("Wilma");
        c2.setLast_name("Teo");
        c2.setAddress("Bukit Batok");
        c2.setBusiness_phone("(123)456-0100");
        customerList.add(c2);
        return customerList;
    }

    public List<Order> createOrders() {
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1);
        order1.setOrder_date(Date.valueOf("2006-03-24"));
        order1.setShipped_date(Date.valueOf("2006-04-24"));
        order1.setShipper_id(10);
        order1.setShip_name("testShipName1");
        order1.setShipping_fee(10.50);
        order1.setPayment_type("Credit");
        order1.setPaid_date(Date.valueOf("2006-03-24"));
        orderList.add(order1);

        Order order2 = new Order();
        order2.setId(2);
        order2.setOrder_date(Date.valueOf("2007-03-24"));
        order2.setShipped_date(Date.valueOf("2007-04-24"));
        order2.setShipper_id(10);
        order2.setShip_name("testShipName2");
        order2.setShipping_fee(10.50);
        order2.setPayment_type("Credit");
        order2.setPaid_date(Date.valueOf("2007-03-24"));
        orderList.add(order2);
        return orderList;
    }
}
