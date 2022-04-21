package sg.nus.iss.workshop21Northwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.workshop21Northwind.model.Customer;
import sg.nus.iss.workshop21Northwind.model.Order;
import sg.nus.iss.workshop21Northwind.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public Optional<List<Customer>> getAllCustomers(Integer limit, Integer offset) {
        System.out.println(">>>In CustomerSvc - getAllCustomers");
        List<Customer> customerList = customerRepo.getAllCustomers(limit,offset);
        return Optional.of(customerList);
    }

    public Optional<List<Customer>> getCustomerById(Integer custId) {
        System.out.println(">>>In CustomerSvc - getCustomerById");
        Optional<List<Customer>> opt = customerRepo.getCustomerById(custId);
        return opt;
    }

    public Optional<List<Order>> getAllOrdersByCustomerId(Integer custId) {
        System.out.println(">>>In CustomerSvc - getAllOrdersByCustomerId");
        List<Order> orderList = customerRepo.getAllOrdersByCustomerId(custId);
        return Optional.of(orderList);
    }
}
