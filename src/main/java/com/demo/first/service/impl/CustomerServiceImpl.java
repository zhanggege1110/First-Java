package com.demo.first.service.impl;

import com.demo.first.dao.CustomerMapper;
import com.demo.first.model.Customer;
import com.demo.first.model.CustomerExample;
import com.demo.first.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public String name(String name) {
        return "service: "+  name;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        int i = this.customerMapper.insertSelective(customer);
        if (i > 0)
            return customer;
        return null;
    }
    @Override
    public Customer cutById(Integer id) {
       int i = this.customerMapper.deleteByPrimaryKey(id);
           return null;
    }

    @Override
    public Customer changeByPrimaryKeySelective(Customer customer) {
        int i = this.customerMapper.updateByPrimaryKeySelective(customer);
            return customer;
    }

    @Override
    public Customer getByName(String name) {

        CustomerExample customerExample = new CustomerExample();
        customerExample.or().andCustomerNameEqualTo(name);
        List<Customer> customers = customerMapper.selectByExample(customerExample);

        return CollectionUtils.isEmpty(customers) ? null : customers.get(0);
    }
}


