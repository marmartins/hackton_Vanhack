package com.marcsystem.std.company.model;

import org.junit.Test;

import static org.junit.Assert.*;


public class OrdersTest {
    @Test
    public void getId() throws Exception {
        Orders orders = new Orders();
        orders.setId(1L);

        assertEquals(Long.valueOf(orders.getId()), Long.valueOf(1L));

    }

    @Test
    public void getStatus() throws Exception {
        Orders orders = new Orders();
        assertEquals(orders.getStatus(), Status.OPEN);

        orders.setStatus(orders.getStatus().next());
        assertEquals(orders.getStatus(), Status.APPROVED);

        orders.setStatus(Status.CANCELED);
        assertEquals(orders.getStatus(), Status.CANCELED);
    }

}