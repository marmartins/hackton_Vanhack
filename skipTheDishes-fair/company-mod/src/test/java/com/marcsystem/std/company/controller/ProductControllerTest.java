package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.Product;
import com.marcsystem.std.company.repository.ProductRepository;
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
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {

    public static final String REST_URL = "/v1/products";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository repository;

    @Test
    public void getAllOrdersTest() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(createEntity(1));
        products.add(createEntity(2));
        Mockito.when(repository.findAll()).thenReturn(products);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                REST_URL).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"id\":1,\"code\":\"01\",\"name\":\"Name_1\",\"description\":null},{\"id\":2,\"code\":\"02\",\"name\":\"Name_2\",\"description\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getOneTest() throws Exception {
        Mockito.when(repository.getOne(1L)).thenReturn(createEntity(1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                REST_URL +"/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"id\":1,\"code\":\"01\",\"name\":\"Name_1\",\"description\":null}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void createNewTest() throws Exception {
        Product entity = createEntity(1);
        Mockito.when(repository.save(entity)).thenReturn(entity);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                REST_URL)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"code\":\"01\",\"name\":\"Name_1\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());

        assertEquals(201, result.getResponse().getStatus());

    }

    private Product createEntity(long identity) {
        Product entity = new Product();
        entity.setId(identity);
        entity.setCode("0".concat(String.valueOf(identity)));
        entity.setName("Name_" + identity);
        return entity;
    }

}