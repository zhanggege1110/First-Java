package com.demo.first.controller;

import com.alibaba.fastjson.JSON;
import com.demo.first.model.Customer;
import com.demo.first.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping({"first"})
    @ResponseBody
    public String first(String name) {
        return this.customerService.name(name);
    }

    @RequestMapping({"/add"})
    @ResponseBody
    public String addCustomer(String name, Integer age, String phone) {
        Customer customer = new Customer();
//        customer.setId(Integer.valueOf(1));
        customer.setCustomerName(name);
        customer.setAge(age);
        customer.setPhone(phone);
        Customer customer1 = (Customer) this.customerService.addCustomer(customer);
        return JSON.toJSONString(customer1);
    }

    @RequestMapping(value = "/get/{name}")
    public ModelAndView get(@PathVariable("name") String name){

        ModelAndView mav = new ModelAndView("customerinfo");
        try {
            Customer byName = customerService.getByName(name);
            System.out.println(JSON.toJSONString(byName));

            mav.addObject("customerInfo", JSON.toJSONString(byName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return mav;
    }
    @RequestMapping(value="/cut")
    @ResponseBody
    public String cutId(Integer id){
        this.customerService.cutById(id);
        return JSON.toJSONString("删除成功");
    }
    @RequestMapping(value="/change")
    @ResponseBody
    public String changeByPrimaryKey(String name, Integer age, String phone){
        Customer customer = new Customer();
        customer.setId((1));
        customer.setCustomerName(name);
        customer.setAge(age);
        customer.setPhone(phone);
        Customer customer1 = (Customer) this.customerService.changeByPrimaryKeySelective(customer);
        return JSON.toJSONString(customer1);
    }

}
