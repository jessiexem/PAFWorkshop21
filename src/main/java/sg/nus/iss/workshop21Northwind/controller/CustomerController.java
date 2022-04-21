package sg.nus.iss.workshop21Northwind.controller;

import jakarta.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sg.nus.iss.workshop21Northwind.CustomerService;
import sg.nus.iss.workshop21Northwind.model.Customer;
import sg.nus.iss.workshop21Northwind.model.Order;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService custSvc;

    @GetMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomers(@RequestParam (name="limit",defaultValue = "5") String limit,
                                               @RequestParam (name="offset",defaultValue = "0") String offset) {

        System.out.println(">>>>> In the controller: getCustomers()");
        Optional<List<Customer>> opt = custSvc.getAllCustomers(Integer.parseInt(limit),Integer.parseInt(offset));
        List<Customer> list = opt.get();


        JsonArray customersJson = Customer.createCustomerJsonArr(list);
        System.out.println(customersJson);

        return ResponseEntity.ok(customersJson.toString());
    }

    @GetMapping(value = "/api/customer/{custId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomerDetails(@PathVariable (name="custId", required = true) String custId) {

        System.out.println(">>>> getCustomerDetailsController custId: "+custId);
        Optional<List<Customer>> opt = custSvc.getCustomerById(Integer.parseInt(custId));


        if(opt.isEmpty()) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            return ResponseEntity.status(404).body(
                    objBuilder.add("error","customerId: %s not found".formatted(custId))
                            .build().toString()
            );
        }
        List<Customer> list = opt.get();


        JsonArray customerJson = Customer.createCustomerJsonArr(list);
        System.out.println(customerJson);
        return ResponseEntity.ok(customerJson.toString());
    }

    @GetMapping (value = "/api/customer/{custId}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllOrdersByCustomer(@PathVariable (name="custId", required = true) String custId) {
        System.out.println(">>>> getAllOrdersByCustomerController custId: "+custId);

        //check if customer exist first
        Optional<List<Customer>> optcust = custSvc.getCustomerById(Integer.parseInt(custId));

        if(optcust.isEmpty()) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            return ResponseEntity.status(404).body(
                    objBuilder.add("error","customerId: %s not found".formatted(custId))
                            .build().toString()
            );
        }

        //if customer exists
        Optional<List<Order>> optOrder = custSvc.getAllOrdersByCustomerId(Integer.parseInt(custId));

        if (optOrder.isEmpty()) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            return ResponseEntity.status(404).body(
                    objBuilder.add("error","orders not found")
                            .build().toString()
            );
        }

        List<Order> orderList = optOrder.get();
        JsonArray ordersJson = Order.createOrdersJsonArr(orderList);
        System.out.println(ordersJson);
        return ResponseEntity.ok(ordersJson.toString());
    }
}
