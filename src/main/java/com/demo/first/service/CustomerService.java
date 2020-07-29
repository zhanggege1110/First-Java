package com.demo.first.service;

import com.demo.first.model.Customer;

public interface CustomerService {

    String name(String name);

    Object addCustomer(Customer customer);

    Customer cutById(Integer id);

    Customer changeByPrimaryKeySelective(Customer customer);

    Customer getByName(String name);
}
