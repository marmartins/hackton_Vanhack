package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.Orders;
import com.marcsystem.std.company.repository.OrderProductRepository;
import com.marcsystem.std.company.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository repository;
    @MockBean
    private OrderProductRepository orderProductRepository;

    @Test
    public void getAllOrdersTest() throws Exception {
        List<Orders> orders = new ArrayList<>();
        orders.add(createOrder(1));
        orders.add(createOrder(2));
        Mockito.when(repository.findAll()).thenReturn(orders);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/v1/orders").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"id\":1,\"code\":\"01\",\"status\":\"OPEN\",\"products\":[]},{\"id\":2,\"code\":\"02\",\"status\":\"OPEN\",\"products\":[]}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getOneTest() throws Exception {
        Mockito.when(repository.getOne(1L)).thenReturn(createOrder(1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/v1/orders/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"id\":1,\"code\":\"01\",\"status\":\"OPEN\",\"products\":[]}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void createNewTest() throws Exception {
        Orders order = createOrder(1);
        Mockito.when(repository.save(order)).thenReturn(order);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/v1/orders")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"code\":\"01\",\"status\":\"OPEN\",\"products\":[]}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());

        assertEquals(201, result.getResponse().getStatus());

    }

    private Orders createOrder(long identity) {
        Orders order = new Orders();
        order.setId(identity);
        order.setCode("0".concat(String.valueOf(identity)));
        return order;
    }

}