package com.example.customer.demo.service;

/* Copyright 2017 Nikesh Pathak
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

import com.example.customer.demo.model.Customer;
import com.example.customer.demo.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public void add(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public void update(Customer customer) {
        Customer customerUpdate = customerRepo.findOne(customer.getId());
        customerUpdate.setName(customer.getName());
        customerUpdate.setEmail(customer.getEmail());
        customerUpdate.setCity(customer.getCity());
        customerUpdate.setState(customer.getState());
        customerRepo.save(customerUpdate);
    }

    @Override
    public void delete(int customerId) {
        customerRepo.delete(customerId);
    }

    @Override
    public Customer get(int customerId) {
        return customerRepo.findOne(customerId);
    }

    @Override
    public List<Customer> getAll() {
         Iterable<Customer> iterable = customerRepo.findAll();
       return StreamSupport.stream(iterable.spliterator(),false).collect(Collectors.toList());
    }
}
