package sg.nus.iss.workshop21Northwind;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn2OrdersForCustId1() {
        int count = 2;

        //construct request
        RequestBuilder req = MockMvcRequestBuilders.get("/api/customer/1/orders")
                .accept(MediaType.APPLICATION_JSON);

        //Make the request
        MvcResult result = null;
        try {
            result = mockMvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc invocation", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        String payload;
        try {
            // JSON
            payload = resp.getContentAsString();
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray orders = reader.readArray();

        Assertions.assertEquals(count,orders.size(),
                "Expect %s orders. Got %s".formatted(count, orders.size()));
    }

    @Test
    void shouldReturn29Customers() {
        int count = 29;

        RequestBuilder req = MockMvcRequestBuilders.get("/api/customers?limit=100")
                .accept(MediaType.APPLICATION_JSON);

        //Make the request
        MvcResult result = null;
        try {
            result = mockMvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc invocation", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        String payload;
        try {
            // JSON
            payload = resp.getContentAsString();
        } catch (Exception ex) {
            fail("cannot retrieve response payload", ex);
            return;
        }

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonArray customers = reader.readArray();

        Assertions.assertEquals(count,customers.size(),
                "Expect %s customers. Got %s".formatted(count, customers.size()));
    }
}
