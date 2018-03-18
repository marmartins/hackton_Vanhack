package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.AppUser;
import com.marcsystem.std.company.repository.UserRepository;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    public static final String REST_URL = "/v1/users";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    public UserControllerTest() {}

    @Test
    public void getAllOrdersTest() throws Exception {
        List<AppUser> appUsers = new ArrayList<>();
        appUsers.add(createEntity(1));
        appUsers.add(createEntity(2));
        Mockito.when(repository.findAll()).thenReturn(appUsers);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                REST_URL).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"id\":1,\"userName\":\"01\",\"pwd\":\"Name_1\",\"roles\":null},{\"id\":2,\"userName\":\"02\",\"pwd\":\"Name_2\",\"roles\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createNewTest() throws Exception {
        AppUser entity = createEntity(1);
        Mockito.when(repository.save(entity)).thenReturn(entity);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                REST_URL)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"loggedUser\":null,\"enabled\":false,\"createAt\":null,\"updateAt\":null,\"id\":1,\"userName\":\"01\",\"pwd\":\"Name_1\",\"roles\":null}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getStatus());

        assertEquals(201, result.getResponse().getStatus());

    }

    private AppUser createEntity(long identity) {
        AppUser entity = new AppUser();
        entity.setId(identity);
        entity.setUserName("0".concat(String.valueOf(identity)));
        entity.setPwd("Name_" + identity);
        return entity;
    }
}